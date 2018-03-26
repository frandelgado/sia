function output_values = forward(weight_matrices, lat, lg)
    global max_layer_neurons;
    layers = length(weight_matrices) + 1;
    output_values = zeros(layers, max_layer_neurons + 1);
    output_values(1, 1) = lat;
    output_values(1, 2) = lg;

    last_layer = layers - 1;
    for j = 1:layers - 1
        output_values = propagate(weight_matrices{j}, output_values, j, last_layer);
    endfor

endfunction

function ret = propagate(weight_matrix, output_values, current_input_index, last_layer)
    global activation_function;
    global max_layer_neurons;
    global enable_linear_output;
    weight_matrix_size = rows(weight_matrix);
    current_inputs = output_values(current_input_index, 1:weight_matrix_size);
    current_inputs(weight_matrix_size) = -1;
    if activation_function == 0
        with_applied_function = tanh(current_inputs * weight_matrix);
    elseif activation_function == 1
        with_applied_function = sig_exp(current_inputs * weight_matrix);
    endif
    if enable_linear_output == 1 && last_layer == current_input_index
        with_applied_function = current_inputs * weight_matrix;
    endif

    diff_size = max_layer_neurons + 1 - length(with_applied_function);
    output_values(current_input_index + 1, :) = horzcat(with_applied_function, zeros(1, diff_size));
    ret = output_values;
endfunction

# Activation function.
function ret = sig_exp(z)
  ret = 1 ./ (1 + e.^-z);
endfunction
