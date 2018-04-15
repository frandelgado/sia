function draw_meshgrid(weight_matrices, data)
  step = 0.01;
  max_x = 0;
  min_x = 0;
  max_y = 0;
  min_y = 0;
  for idx = 2:rows(data)
    min_x = min(min_x, data(idx, 1));
    max_x = max(max_x, data(idx, 1));
  endfor
  for idx = 2:rows(data)
    min_y = min(min_y, data(idx, 2));
    max_y = max(max_y, data(idx, 2));
  endfor

  x = linspace(min_x, max_x, 100);
  y = linspace(min_y, max_y, 100);
  [xx, yy] = meshgrid(x, y);
  zz = xx * 0;
  for idx_x = 1:length(x)
    for idx_y = 1:length(y)
      x_coord = xx(idx_x, idx_y);
      y_coord = yy(idx_x, idx_y);
      output_values = forward(weight_matrices, x_coord, y_coord);
      zz(idx_x, idx_y) = output_values(end, 1);
    endfor
  endfor
  surf(xx, yy, zz);
endfunction
