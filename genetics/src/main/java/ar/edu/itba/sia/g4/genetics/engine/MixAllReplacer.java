package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MixAllReplacer<T extends Species> implements Replacer<T> {
    private final Selector<T> selector;
    private final Selector<T> replacer;
    private final double ratio;

    public MixAllReplacer(Selector<T> selector, Selector<T> replacer, double ratio) {
        this.selector = selector;
        this.replacer = replacer;
        this.ratio = ratio;
        if (ratio < 0 || ratio > 1) {
            throw new IllegalArgumentException("Ratio must be 0 <= ratio <= 1");
        }
    }

    @Override
    public List<T> getParents(List<T> population, int populationLimit) {
        return selector.select(population, populationLimit);
    }

    @Override
    public double getGenerationalGapRatio() {
        return ratio;
    }

    @Override
    public List<T> mix(List<T> originalPopulation, List<T> newChildren) {
        List<T> everyone = Stream.concat(newChildren.stream(), originalPopulation.stream())
         .collect(Collectors.toUnmodifiableList());
        return replacer.select(everyone, originalPopulation.size());
    }
}
