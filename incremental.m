function ret = incremental(weight_matrices, number_of_cases)
  global data;
  global n;
  global a_for_adaptative_eta;
  global b_for_adaptative_eta;
  global steps_for_adaptative_eta;
  global use_adaptative_eta;
  global percentage_error_for_adaptative_eta;
  global max_learning_epochs;

  epoch = rows(data)-1;
  min_error = 0.00001;
  epoch_error = 1;
  prev_epoch_error = 1;
  epoch_error_decreasing_steps = 0;
  previous_weights = {};
  weight_matrices_diff = {};
  t = 1;
  error_time_matrix = ones(1, 3);
  while(epoch_error > min_error  && t <= max_learning_epochs)
    if use_adaptative_eta
      previous_weights = weight_matrices;
      prev_epoch_error = epoch_error;
    endif
    for i = 1:length(weight_matrices)
      weight_matrices_diff{i} = zeros(rows(weight_matrices{i}), columns(weight_matrices{i}));
    endfor
    random_cases_indexes = randperm(number_of_cases+1);
    random_cases_indexes = random_cases_indexes(2:end);
    for caseIndex = random_cases_indexes
      output_values = forward(weight_matrices, data(caseIndex, 1), data(caseIndex, 2));
      [weight_matrices, weight_matrices_diff] = back(weight_matrices, weight_matrices_diff, output_values, [data(caseIndex, 3)]);
    endfor

    [epoch_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t);
    error_time_matrix = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch);

    if use_adaptative_eta
      if epoch_error < prev_epoch_error
        epoch_error_decreasing_steps = epoch_error_decreasing_steps + 1;
        if steps_for_adaptative_eta <= epoch_error_decreasing_steps
          n = a_for_adaptative_eta * n
          momentum_alpha = 0.9;
        endif
      elseif epoch_error >= (1 + percentage_error_for_adaptative_eta * 0.01) * prev_epoch_error
        weight_matrices = previous_weights;
        n = b_for_adaptative_eta * n
        epoch_error = prev_epoch_error;
        epoch_error_decreasing_steps = 0;
        momentum_alpha = 0;
      endif
    endif

    plot(error_time_matrix(:,2:3));
    axis([0, 1, 0, 0.5]);
    axis("autox");
    xlabel ("epoch");
    ylabel ("error");
    title ("Generalization and learning errors");
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
  gen_error = gen_error/(epoch - number_of_cases);
  error_time_matrix(t, 1) = t;
  error_time_matrix(t, 3) = gen_error;
  gen_error
  printf("-------------------\n");
endfunction
