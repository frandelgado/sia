# Prevent Octave from thinking that this is a function file:
1;
global n = 0.02;
global activation_function = 1; # 0 for hyperbolic tangent or 1 for exponencial.
global data = dlmread("terrain04.data"); # Reads data and stores it in a matrix.
process_type = 0; # 0 for batch or 1 for incremental.
number_of_cases = 50;
architecture = [2, 3, 1]; # Each column specifies the amount of neurons in a layer.

# Starting
weightMatrices = init(architecture);

x = -10:0.1:10
plot (x, sin (x))

if process_type == 0
  batch(weightMatrices, number_of_cases);
else
  incremental(weightMatrices, number_of_cases);
endif
