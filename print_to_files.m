function ret = print_to_files(result_weight_matrices, error_time_matrix, run_number)
  plot(error_time_matrix(:,2:3));
  axis([0, 1, 0, 0.4]);
  axis("autox");
  xlabel("epoca");
  ylabel("error");
  title("Error de generalizacion y de aprendizaje en funcion de la epoca");
  legend ("Aprendizaje", "Generalizacion");
  saveas(1, sprintf("./test_results/errors_%d.png", run_number));

  plot(error_time_matrix(:,4));
  axis([0, 1, 0, 0.1]);
  axis("autox");
  xlabel("epoca");
  ylabel("eta");
  title("Eta en funcion de la epoca");
  legend ("Eta");
  saveas(1, sprintf("./test_results/eta_%d.png", run_number));

  result_weight_matrices
  for i = 1:length(result_weight_matrices)
    dlmwrite(sprintf("./test_results/weights_%d.txt", run_number), result_weight_matrices{i}, '-append', 'delimiter', '\t', 'precision', 6, 'roffset', 1)
  endfor
endfunction