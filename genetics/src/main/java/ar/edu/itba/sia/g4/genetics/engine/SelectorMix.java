package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectorMix<T extends Species> implements Selector<T> {
    private final Selector<T> mix1;
    private final Selector<T> mix2;
    private final double mix;

    public SelectorMix(Selector<T> mix1, Selector<T> mix2, double mix) {
        this.mix1 = mix1;
        this.mix2 = mix2;
        this.mix = mix;

        if (mix < 0 || mix > 1) {
            throw new IllegalArgumentException("Mix is not between 0 and 1");
        }
    }

    @Override
    public List<T> select(List<T> population, int populationLimit) {
        int mixX = (int)(mix * populationLimit);
        List<T> mix1list = mix1.select(population, mixX);
        List<T> mix2list = mix2.select(population, populationLimit - mixX);
        return Stream.concat(mix1list.stream(), mix2list.stream()).collect(Collectors.toUnmodifiableList());
    }
}
