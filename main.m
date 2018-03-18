# Prevent Octave from thinking that this is a function file:
1;

global n = 0.02;
arquitecture = [2, 3, 1]; # Each column specifies the amount of neurons in a layer.

# Starting
weightMatrixes = init(arquitecture);

