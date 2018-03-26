function weight_matrices = read_weight_file(file_num)
  fid = fopen(sprintf("./test_results/weights_%d.txt", file_num));
  weight_matrices = {};
  weight_matrices{1} = [];
  weight_matrix_index = 1;
  row = fgets(fid);
  while row != -1
    row = fgets(fid);
    if row != -1
      r = deblank(row);
      if length(r) > 0
        aux = strsplit(row, "\t");
        for i = 1:length(strsplit(row, "\t"))
          aux{i} = str2num(aux{i});
        endfor
        weight_matrices{weight_matrix_index} = [weight_matrices{weight_matrix_index}; cell2mat(aux)];
      else
        weight_matrix_index = weight_matrix_index + 1;
        weight_matrices{weight_matrix_index} = [];
      endif
    endif
  endwhile
  fclose (fid);
  weight_matrices
endfunction