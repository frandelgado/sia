package ar.edu.itba.sia.g4.genetics.engine.problem;

public interface Crossover<T extends Species> {
    /**
     * Lenguaje inclusive
     * @param mem
     * @param ded
     * @return
     */
    T[] breed(T mem, T ded);
}
