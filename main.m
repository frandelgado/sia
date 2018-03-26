# Prevent Octave from thinking that this is a function file:
1;
global n = 0.02;
global use_adaptative_eta = true;
global use_momentum = true;
global percentage_error_for_adaptative_eta = 3;
global a_for_adaptative_eta = 1.4;
global b_for_adaptative_eta = 0.95;
global steps_for_adaptative_eta = 3;
global momentum_alpha = 0.9;
global activation_function = 0; # 0 for hyperbolic tangent or 1 for exponencial.
global data = dlmread("terrain04.data"); # Reads data and stores it in a matrix.
global number_of_cases = floor(0.7*(rows(data)-1));
global architecture = [2, 50, 1]; # Each column specifies the amount of neurons in a layer.
global max_layer_neurons = max(architecture);
global is_incremental = 1;
global enable_linear_output = 1;
global max_learning_epochs = 1500;
global is_test_case = 0;

# Starting.
if is_test_case != 1
  weight_matrices = init(architecture);
  [weight_matrices, error_time_matrix] = train(weight_matrices, number_of_cases, is_incremental);
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
