function ret = quad_error(expected, actual)
  ret = 0.5 * sum((expected - actual).^2);
endfunction