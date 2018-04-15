function ret = print_to_files(result_weight_matrices, error_time_matrix, run_number)
  unlink(sprintf("./test_results/weights_%d.txt", run_number));
  for i = 1:length(result_weight_matrices)
    dlmwrite(sprintf("./test_results/weights_%d.txt", run_number), result_weight_matrices{i}, '-append', 'delimiter', '\t', 'precision', 6, 'roffset', 1);
  endfor

  plot(error_time_matrix(:,2:3));
  axis([0, 1, 0, 0.3]);
  axis("autox");
  xlabel("epoca");
  ylabel("error");
  title("Error de generalizacion y de aprendizaje en funcion de la epoca");
  legend("Aprendizaje", "Generalizacion");
  text(350, 0.2, sprintf("A(500): %d", error_time_matrix(500, 2)));
  text(350, 0.17, sprintf("G(500): %d", error_time_matrix(500, 3)));
  text(850, 0.2, sprintf("A(1000): %d", error_time_matrix(1000, 2)));
  text(850, 0.17, sprintf("G(1000): %d", error_time_matrix(1000, 3)));
  text(1350, 0.2, sprintf("A(1500): %d", error_time_matrix(1500, 2)));
  text(1350, 0.17, sprintf("G(1500): %d", error_time_matrix(1500, 3)));
  saveas(1, sprintf("./test_results/errors_%d.png", run_number));

  plot(error_time_matrix(:,4));
  axis([0, 1, 0, 0.1]);
  axis("autox");
  xlabel("epoca");
  ylabel("eta");
  title("Eta en funcion de la epoca");
  legend("Eta");
  text(350, 0.04, sprintf("E(500): %d", error_time_matrix(500, 4)));
  text(850, 0.04, sprintf("E(1000): %d", error_time_matrix(1000, 4)));
  text(1350, 0.04, sprintf("E(1500): %d", error_time_matrix(1500, 4)));
  saveas(1, sprintf("./test_results/eta_%d.png", run_number));

endfunction