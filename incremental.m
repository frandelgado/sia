function ret = incremental(weight_matrices, number_of_cases)
  global data;
  global n;
  global a_for_adaptative_eta;
  global b_for_adaptative_eta;
  global steps_for_adaptative_eta;
  global use_adaptative_eta;

  epoch = rows(data)-1;
  current_epoch_pattern = 1;
  min_error = 0.00001;
  epoch_error = 1;
  prev_epoch_error = 1;
  epoch_error_decreasing_steps = 0;
  previous_weights = {};
  t = 1;
  error_time_matrix = ones(1, 3);
  while(epoch_error > min_error)
    if use_adaptative_eta  
      previous_weights = weight_matrices;
    endif
    
    while(current_epoch_pattern <= number_of_cases)
      pattern = randi([2, number_of_cases + 1]);
      output_values = forward(weight_matrices, data(pattern, 1), data(pattern, 2));
      weight_matrices = back(weight_matrices, output_values, [data(pattern, 3)]);
      current_epoch_pattern = current_epoch_pattern + 1;
    endwhile
    prev_epoch_error = epoch_error;
  
    if use_adaptative_eta
      if epoch_error < prev_epoch_error
        epoch_error_decreasing_steps = epoch_error_decreasing_steps + 1;
        if steps_for_adaptative_eta <= epoch_error_decreasing_steps
          n = a_for_adaptative_eta + n;
        endif
      else
        weight_matrices = previous_weights;
        n = n - b_for_adaptative_eta*n;
        epoch_error = prev_epoch_error;
      endif
    endif

    [epoch_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t);
    error_time_matrix = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch);
    current_epoch_pattern = 1;

    plot(error_time_matrix(:,2:3));
    axis([0, 1, 0, 0.5]);
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
