function weight_matrices = init (architecture)
    arch_size = length(architecture);
    for i = 1:(arch_size-1)
        adj = sqrt(6/(architecture(i) + 1 + architecture(i + 1)));
        weight_matrices{i} = -adj + (2 * adj * rand(architecture(i) + 1, architecture(i+1))); # rows = from ; cols = to
    endfor
endfunction