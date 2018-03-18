#activation_function 0 is tanh, 1 is exp
function output_values = forward(weight_matrices, lat, lg)
    layers = size(weight_matrices) + 1;
    output_values = [{lat, long}];
    
    for i = 1:layers
    output_values = vertcat(output_values,[{}]);
    endfor

    for j = 1:layers-1
        propagate(weight_matrices(j),output_values,j);
    endfor

endfunction

function propagate(weight_matrix, output_values, current_input_index)
    current_inputs = output_values[current_input_index,:];
    current_inputs = vertcat([-1], current_inputs);
    if activation_function == 0
        output_values[current_input_index+1,:] = (1 + exp(-1 * (weight_matrix*current_inputs))).^-1;
    else 
        output_values[current_input_index+1,:] = tanh(weight_matrix*current_inputs);
endfunction
