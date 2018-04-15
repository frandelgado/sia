function [modified_weight_matrices, weight_matrices_diff] = back(weight_matrices, weight_matrices_diff, output_values, expected)
  layers = length(weight_matrices);
  external_layer = [output_values(end, 1)];
  init_deltas = g_prime(external_layer) * (expected - external_layer);
  deltas = [init_deltas];
  for j = layers:-1:1
    [weight_matrices{j}, deltas, weight_matrices_diff{j}] = backpropagation(weight_matrices{j}, weight_matrices_diff{j}, j, deltas, output_values, layers);
  endfor
  modified_weight_matrices = weight_matrices;
endfunction

function [modified_weights, deltas, new_weight_matrix_diff] = backpropagation(weights_to_modify, weight_matrix_diff, index_to_use, deltas, output_values, layers)
  global n;
  global momentum_alpha;
  global use_momentum;
  global enable_linear_output
  layer_for_g_prime = output_values(index_to_use, 1:rows(weights_to_modify) - 1);
  layer_output = horzcat(layer_for_g_prime, -1);
  momentum = 0;
  if use_momentum
    momentum = momentum_alpha * weight_matrix_diff;
  endif
  new_weight_matrix_diff = n*(layer_output' * deltas) + momentum;
  modified_weights = weights_to_modify + new_weight_matrix_diff;
  weights_for_new_deltas = modified_weights(1:end-1,:);
  if enable_linear_output == 1 && j == layers
    deltas = deltas * weights_for_new_deltas';
  else
    deltas = deltas * weights_for_new_deltas' .* g_prime(layer_for_g_prime);
  endif
endfunction

function ret = g_prime(layer)
  global activation_function;
  if activation_function == 0
    ret = 1 - layer.^2;
  elseif activation_function == 1
    ret = layer.*(1 - layer);
  endif
endfunction
