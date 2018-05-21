package ar.edu.itba.sia.g4.genetics.engine.problem;

public interface Mutator<T extends Species> {
    T mutate(T ind, long generation);
}
