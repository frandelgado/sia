function ret = batch(weightMatrices, number_of_cases)
  global data;
  for i = 1:number_of_cases
      partial_weights = forward(weightMatrices, data(i, 1), data(i, 2));
  endfor
endfunction