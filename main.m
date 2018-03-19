# Prevent Octave from thinking that this is a function file:
1;
global n = 0.02;
global activation_function = 1; # 0 for hyperbolic tangent or 1 for exponencial.
global data = dlmread("terrain04.data"); # Reads data and stores it in a matrix.
number_of_cases = 50;
architecture = [2, 3, 1]; # Each column specifies the amount of neurons in a layer.
global max_layer_neurons = max(architecture);

# Activation function
function ret = sig_exp(z)
  ret = 1 ./ (1 + e.^-z);
endfunction

# Starting
weight_matrices = init(architecture);

incremental(weight_matrices, number_of_cases);
