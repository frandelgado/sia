function ret = batch(weight_matrices, number_of_cases)
  global data;
  epoch = rows(data)-1;
  current_epoch_pattern = 1;
  min_error = 0.0001;
  epoch_error = 1;
  output_values_vector = {};
  t = 1;
  error_time_matrix = ones(1, 3);
  accumulated_expected = 0;
  batch_size = number_of_cases/5;
  batches_per_plot = 5;

  while(epoch_error > min_error)
    number_of_batches_ran = 0;
    while(number_of_batches_ran < batches_per_plot)
      while(current_epoch_pattern <= batch_size)
        pattern = randi([2, number_of_cases + 1]);
        output_values_vector{current_epoch_pattern} = forward(weight_matrices, data(pattern, 1), data(pattern, 2));
        expected_vector{current_epoch_pattern} = data(pattern,3);
        current_epoch_pattern = current_epoch_pattern + 1;
      endwhile

      weight_matrices = batch_back(weight_matrices, output_values_vector, expected_vector, batch_size);
      number_of_batches_ran = number_of_batches_ran + 1;
    endwhile
    [epoch_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t);
    error_time_matrix = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch);
    current_epoch_pattern = 1;
    accumulated_expected = 0;

    plot(error_time_matrix(:,2:3));
    axis([0, 1, 0, 0.6]);
    axis("autox");
    drawnow();
    t = t + 1;
  endwhile

endfunction

function [aprox_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t)
  aprox_error = 0;

  for i = 2:number_of_cases+1
    output_values = forward(weight_matrices,data(i,1), data(i,2));
    aprox_error = aprox_error + quad_error(data(i,3), output_values(end, 1));
  endfor
  
  aprox_error = aprox_error/number_of_cases;
  error_time_matrix(t, 1) = t;
  error_time_matrix(t, 2) = aprox_error;
  aprox_error

endfunction

function error_time_matrix = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch)
  gen_error = 0;
  
  for i = number_of_cases+2:epoch+1
    output_values = forward(weight_matrices,data(i,1), data(i,2));
    gen_error = gen_error + quad_error(data(i,3), output_values(end, 1));
  endfor
  
  gen_error = gen_error/number_of_cases;
  error_time_matrix(t, 1) = t;
  error_time_matrix(t, 3) = gen_error;
  gen_error
  printf("-------------------\n");

endfunction