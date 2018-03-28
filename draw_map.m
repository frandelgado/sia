function draw_map(data)

  for idx = 2:rows(data)
    x(idx) = data(idx, 1);
    y(idx) = data(idx, 2);
    z(idx) = data(idx, 3);
  endfor

  scatter3(x, y, z, 5, [1, 0, 0]);
endfunction
