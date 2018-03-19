function modified_weight_matrices = back(weight_matrices, output_values, expected)
  layers = length(weight_matrices) + 1;
  external_layer = [output_values(end, 1)];
  init_deltas = g_prime(external_layer) * quad_error(expected, external_layer);
  deltas = [init_deltas];
  for j = layers - 1:-1:1
    [weight_matrices{j}, deltas] = backpropagation(weight_matrices{j}, j, deltas, output_values, layers);
  endfor
  modified_weight_matrices = weight_matrices;
endfunction

function [modified_weights, deltas] = backpropagation(weights_to_modify, index_to_use, deltas, output_values, layers)
  layer_for_g_prime = output_values(index_to_use, 1:rows(weights_to_modify) - 1);
  layer_output = horzcat(layer_for_g_prime, -1);
  modified_weights = weights_to_modify + (layer_output' * deltas);
  weights_for_new_deltas = weights_to_modify(1:end-1,:);
  deltas = deltas * weights_for_new_deltas' .* g_prime(layer_for_g_prime);
endfunction

function ret = g_prime(layer)
  global activation_function;
  if activation_function == 0
    ret = 1 - layer.^2;
  elseif activation_function == 1
    ret = layer - layer.^2;
  endif
endfunction