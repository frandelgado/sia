function [weight_matrices, error_time_matrix] = train(weight_matrices, number_of_cases, is_incremental)
  global data;
  global n;
  global a_for_adaptative_eta;
  global b_for_adaptative_eta;
  global steps_for_adaptative_eta;
  global use_adaptative_eta;
  global percentage_error_for_adaptative_eta;
  global max_learning_epochs;
  global min_error_for_use_adaptive_eta;
  global is_test_case;

  epoch = rows(data)-1;
  min_error = 0.00001;
  epoch_error = 1;
  prev_epoch_error = 1;
  gen_epoch_error = 1;
  prev_gen_epoch_error = 1;
  epoch_error_decreasing_steps = 0;
  previous_weights = {};
  weight_matrices_diff = {};
  t = 1;
  error_time_matrix = ones(1, 3);
  while(epoch_error > min_error && t <= max_learning_epochs)
    if use_adaptative_eta
      previous_weights = weight_matrices;
      prev_epoch_error = epoch_error;
      prev_gen_epoch_error = gen_epoch_error;
    endif
    for i = 1:length(weight_matrices)
      weight_matrices_diff{i} = zeros(rows(weight_matrices{i}), columns(weight_matrices{i}));
    endfor
    random_cases_indexes = randperm(number_of_cases+1);
    random_cases_indexes = random_cases_indexes(2:end);
    if is_incremental == 1
      [weight_matrices, weight_matrices_diff] = do_incremental_steps(random_cases_indexes, weight_matrices, weight_matrices_diff, data);
    else
      weight_matrices = do_batch_steps(random_cases_indexes, weight_matrices, weight_matrices_diff, data);
    endif

    [epoch_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t);
    [gen_epoch_error, error_time_matrix] = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch);
    error_time_matrix(t, 1) = t;
    error_time_matrix(t, 4) = n;
    if is_test_case != 1
      printf("Epoch: %d\n", t);
    endif
    if use_adaptative_eta
      if epoch_error < prev_epoch_error
        epoch_error_decreasing_steps = epoch_error_decreasing_steps + 1;
        if steps_for_adaptative_eta <= epoch_error_decreasing_steps
          n = a_for_adaptative_eta * n;
          momentum_alpha = 0.9;
        endif
      elseif epoch_error >= (1 + percentage_error_for_adaptative_eta * 0.01) * prev_epoch_error
        weight_matrices = previous_weights;
        n = b_for_adaptative_eta * n;
        epoch_error = prev_epoch_error;
        gen_epoch_error = prev_gen_epoch_error;
        epoch_error_decreasing_steps = 0;
        momentum_alpha = 0;
      endif
      if is_test_case != 1
        printf("n: %d\n", n);
      endif
    endif
    error_time_matrix(t, 1) = t;
    error_time_matrix(t, 2) = epoch_error;
    error_time_matrix(t, 1) = t;
    error_time_matrix(t, 3) = gen_epoch_error;

    if is_test_case != 1
      plot(error_time_matrix(:,2:3));
      axis([0, 1, 0, 0.3]);
      axis("autox");
      xlabel("epoca");
      ylabel("error");
      title("Error de generalizacion y de aprendizaje en funcion de la epoca");
      legend ("Aprendizaje", "Generalizacion");
      drawnow();
    endif
    t = t + 1;
  endwhile
endfunction

function [weight_matrices, weight_matrices_diff] = do_incremental_steps(random_cases_indexes, weight_matrices, weight_matrices_diff, data)
  for caseIndex = random_cases_indexes
    output_values = forward(weight_matrices, data(caseIndex, 1), data(caseIndex, 2));
    [weight_matrices, weight_matrices_diff] = back(weight_matrices, weight_matrices_diff, output_values, [data(caseIndex, 3)]);
  endfor
endfunction

function weight_matrices = do_batch_steps(random_cases_indexes, weight_matrices, weight_matrices_diff, data)
  weight_matrices_diff_vector = {};
  for caseIndex = random_cases_indexes
    output_values = forward(weight_matrices, data(caseIndex, 1), data(caseIndex, 2));
    [weight_matrices_value, weight_matrices_diff_value] = back(weight_matrices, weight_matrices_diff, output_values, [data(caseIndex, 3)]);
    weight_matrices_diff_vector{caseIndex} = weight_matrices_diff_value;
  endfor
  for i = 1:length(weight_matrices_diff)
    for caseIndex = random_cases_indexes
      weight_matrices_diff{i} = weight_matrices_diff{i} + weight_matrices_diff_vector{caseIndex}{i};
    endfor
    weight_matrices{i} = weight_matrices{i} + weight_matrices_diff{i};
  endfor
endfunction

function [aprox_error, error_time_matrix] = aproximation_error(number_of_cases, weight_matrices, data, error_time_matrix, t)
  global is_test_case;
  aprox_error = 0;
  for i = 2:number_of_cases+1
    output_values = forward(weight_matrices,data(i,1), data(i,2));
    aprox_error = aprox_error + quad_error(data(i,3), output_values(end, 1));
  endfor
  aprox_error = aprox_error/number_of_cases;
  if is_test_case != 1
    printf("L error: %d\n", aprox_error);
  endif
  aprox_error;
endfunction

function [gen_error, error_time_matrix] = generalization_error(number_of_cases, weight_matrices, data, error_time_matrix, t, epoch)
  global is_test_case;
  gen_error = 0;
  for i = number_of_cases+2:epoch+1
    output_values = forward(weight_matrices,data(i,1), data(i,2));
    gen_error = gen_error + quad_error(data(i,3), output_values(end, 1));
  endfor

  gen_error = gen_error/(epoch - number_of_cases);
  if is_test_case != 1
    printf("G error: %d\n", gen_error);
  endif
  gen_error;
  if is_test_case != 1
    printf("-------------------\n");
  endif
endfunction
