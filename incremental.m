function ret = incremental(weight_matrices, number_of_cases)
  global data;
  output_values = forward(weight_matrices, data(2, 1), data(2, 2));
  modified_weights = back(weight_matrices, output_values, [data(2, 3)]);
endfunction