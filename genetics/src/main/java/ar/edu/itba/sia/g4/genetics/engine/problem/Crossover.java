package ar.edu.itba.sia.g4.genetics.engine.problem;

public interface Crossover<T extends Species> {
    T[] breed(T mem, T ded);
}
