package ar.edu.itba.sia.g4.genetics.problem;

public interface Mutator<T extends Species> {
    T mutate(T ind);

    boolean shouldMutate(T ind, long generation);
}
