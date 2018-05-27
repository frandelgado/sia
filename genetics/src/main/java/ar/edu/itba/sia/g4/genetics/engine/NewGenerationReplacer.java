package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

public class NewGenerationReplacer<T extends Species> implements Replacer<T> {
    @Override
    public List<T> getParents(List<T> population, int populationLimit, long generation) {
        if (population.size() > populationLimit) {
            return population.subList(0, populationLimit);
        }
        return population;
    }

    @Override
    public double getGenerationalGapRatio() {
        return 1;
    }

    @Override
    public List<T> mix(List<T> parents, List<T> children, long generation) {
        return children;
    }
}
