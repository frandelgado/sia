# Prevent Octave from thinking that this is a function file:
1;
global n = 0.02;
global activation_function = 1; # 0 for hyperbolic tangent or 1 for exponencial.
global data = dlmread("terrain04.data"); # Reads data and stores it in a matrix.
function ret = sig_exp(z)
  ret = 1 ./ (1 + e.^-z);
endfunction
process_type = 0; # 0 for batch or 1 for incremental.
number_of_cases = 50;
architecture = [2, 3, 1]; # Each column specifies the amount of neurons in a layer.
global maxLayerNeurons = max(architecture);

# Starting
weightMatrices = init(architecture);

if process_type == 0
  batch(weightMatrices, number_of_cases);
else
  incremental(weightMatrices, number_of_cases);
endif
