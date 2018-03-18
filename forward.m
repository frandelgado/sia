#activation_function 0 is tanh, 1 is exp
function output_values = forward(weight_matrices, lat, lg)
    global maxLayerNeurons;
    layers = length(weight_matrices) + 1;
    output_values = zeros(layers, maxLayerNeurons + 1);
    output_values(1, 1) = lat;
    output_values(1, 2) = lg;

    output_values
    for j = 1:layers-1
        output_values = propagate(cell2mat(weight_matrices(j)), output_values, j);
    endfor
    output_values

endfunction

function ret = propagate(weight_matrix, output_values, current_input_index)
    global activation_function;
    global maxLayerNeurons;
    weight_matrix_size = rows(weight_matrix);
    current_inputs = output_values(current_input_index, 1:weight_matrix_size);
    current_inputs(weight_matrix_size) = -1;
    if activation_function == 1
        diff_size =  maxLayerNeurons + 1 - length(sig_exp(current_inputs * weight_matrix));
        horzcat(sig_exp(current_inputs * weight_matrix), zeros(1, diff_size));
        output_values(current_input_index+1, :) = horzcat(sig_exp(current_inputs * weight_matrix), zeros(1, diff_size));
    else
        output_values(current_input_index+1) = tanh(weight_matrix*current_inputs);
    endif
    ret = output_values;
endfunction
