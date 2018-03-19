function ret = incremental(weight_matrices, number_of_cases)
  global data;
  epoch = rows(data)-1;
  current_epoch_pattern = 1;
  min_error = 0.00001;
  epoch_error = 1;
  while(epoch_error > min_error)
    while(current_epoch_pattern <= epoch)
      pattern = randi([2,epoch]);
      output_values = forward(weight_matrices, data(pattern, 1), data(pattern, 2));
      weight_matrices = back(weight_matrices, output_values, [data(pattern, 3)]);
      current_epoch_pattern = current_epoch_pattern + 1;
    endwhile  
    epoch_error = aproximation_eror(epoch, weight_matrices, data);
    current_epoch_pattern = 0;
    epoch_error
  endwhile
endfunction

function aprox_error = aproximation_eror(epoch, weight_matrices, data)
  aprox_error = 0;
  for i = 2:epoch+1
    output_values = forward(weight_matrices,data(i,1),data(1,2));
     aprox_error = aprox_error + quad_error(data(i,3), output_values(end, 1));
  endfor
endfunction
