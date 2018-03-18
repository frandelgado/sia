function ret = batch(weightMatrices, number_of_cases)
  global data;
  partial_weights = forward(weightMatrices, data(6, 1), data(6, 2));
endfunction