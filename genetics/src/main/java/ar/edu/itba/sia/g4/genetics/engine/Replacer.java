package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

public interface Replacer<T extends Species> {
    List<T> getParents(List<T> population, int populationLimit, long generation);
    double getGenerationalGapRatio();
    List<T> mix(List<T> originalPopulation, List<T> newChildren, long generation);
}
