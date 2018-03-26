# Prevent Octave from thinking that this is a function file:
1;
global n = 0.01; # Eta
global use_adaptative_eta = true; # Flag to use eta
global use_momentum = true; # Flag to use momentum
global percentage_error_for_adaptative_eta = 3; # Percentage diff between previous and current error in order to change eta
global a_for_adaptative_eta = 1.4; # Constant to increase eta
global b_for_adaptative_eta = 0.95; # Constant to decrease eta
global steps_for_adaptative_eta = 3; # Min continous steps to modify eta
global momentum_alpha = 0.9; # Momentum constant
global activation_function = 0; # 0 for hyperbolic tangent or 1 for exponencial.
global data = dlmread("terrain04.data"); # Reads data and stores it in a matrix.
global number_of_cases = floor(0.3*(rows(data)-1)); # Number of cases to learn from terrain.
global architecture = [2, 30, 20, 1]; # Each column specifies the amount of neurons in a layer.
global max_layer_neurons = max(architecture);
global is_incremental = 1; # Choose from incremental or batch
global enable_linear_output = 1;
global max_learning_epochs = 50000; # Max learning epochs to run
global is_test_case = 0; # 0 to test using above config, 1 to begin custom tests and print results to output files
# For use case
global is_use_case = 0; # 0 to train de neural network, 1 to use one specific neural network
global use_case_file_num = 5; # Weights file number
global use_case_x = 0.3119; # X input
global use_case_y = -0.8417; # Y input
global use_case_expected_z = -0.7180; # Z result for comparison
#------------

if is_use_case == 0
  # Starting.
  if is_test_case != 1
    weight_matrices = init(architecture);
    [weight_matrices, error_time_matrix] = train(weight_matrices, number_of_cases, is_incremental);
    print_to_files(weight_matrices, error_time_matrix, 0);
  else
    test_cases = {
      # Arq, act_func, n, use_adaptive, use_momentum, momentum_alpha
      {[2, 50, 1], 0, 0.02, false, false, 0.9}, #1
      {[2, 30, 20, 1], 0, 0.02, false, false, 0.9},
      {[2, 10, 1], 0, 0.02, false, false, 0.9},
      {[2, 50, 1], 0, 0.04, false, false, 0.9},
      {[2, 30, 20, 1], 0, 0.04, false, false, 0.9}, #5
      {[2, 10, 1], 0, 0.04, false, false, 0.9},
      {[2, 50, 1], 0, 0.02, true, false, 0.9},
      {[2, 30, 20, 1], 0, 0.02, true, false, 0.9},
      {[2, 10, 1], 0, 0.02, true, false, 0.9},
      {[2, 50, 1], 0, 0.04, true, false, 0.9}, #10
      {[2, 30, 20, 1], 0, 0.04, true, false, 0.9},
      {[2, 10, 1], 0, 0.04, true, false, 0.9},
      {[2, 50, 1], 0, 0.02, false, true, 0.9},
      {[2, 50, 1], 0, 0.02, true, true, 0.9},
      {[2, 30, 20, 1], 0, 0.02, false, true, 0.9}, #15
      {[2, 30, 20, 1], 0, 0.02, true, true, 0.9},
      {[2, 50, 1], 0, 0.02, false, true, 0.5},
      {[2, 50, 1], 0, 0.02, true, true, 0.5},
      {[2, 30, 20, 1], 0, 0.02, false, true, 0.5},
      {[2, 30, 20, 1], 0, 0.02, true, true, 0.5}, #20
      {[2, 50, 1], 1, 0.02, true, true, 0.9},
      {[2, 30, 20, 1], 1, 0.02, true, true, 0.9},
      {[2, 50, 1], 1, 0.02, false, false, 0.9},
      {[2, 30, 20, 1], 1, 0.02, false, false, 0.9},
    };
    for test_case_index = 1:length(test_cases)
      # Set all test config
      architecture = test_cases{test_case_index}{1};
      max_layer_neurons = max(architecture);
      activation_function = test_cases{test_case_index}{2};
      n = test_cases{test_case_index}{3};
      use_adaptative_eta = test_cases{test_case_index}{4};
      use_momentum = test_cases{test_case_index}{5};
      momentum_alpha = test_cases{test_case_index}{6};
      # Begin learning case test
      weight_matrices = init(architecture);
      [weight_matrices, error_time_matrix] = train(weight_matrices, number_of_cases, is_incremental);
      print_to_files(weight_matrices, error_time_matrix, test_case_index);
    endfor
  endif
else
  weight_matrices = read_weight_file(use_case_file_num);
  output_values = forward(weight_matrices, use_case_x, use_case_y);
  result_z = output_values(end, 1)
  error_z = ((output_values(end, 1)/use_case_expected_z) - 1)*100
endif
