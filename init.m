function weight_matrices = init(architecture)

    for i = 1:(size(architecture)-1)
        weight_matrices[i] = rand(architecture[i],architecture[i+1]); # rows = from ; cols = to
        weight_matrices[i](end+1,:) = -1*ones(architecture[i+1]);
    endfor 
    
endfunction