package ar.edu.itba.sia.g4.genetics.engine.problem;

import java.util.List;

public interface EvolutionaryTarget<T extends Species> {
    boolean shouldEvolve(List<T> prev, List<T> curr, long generation);
}
