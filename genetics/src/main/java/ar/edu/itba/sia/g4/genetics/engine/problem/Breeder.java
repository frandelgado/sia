package ar.edu.itba.sia.g4.genetics.engine.problem;

public interface Breeder<T extends Species> {
    T[] breed(T mem, T ded);
}
