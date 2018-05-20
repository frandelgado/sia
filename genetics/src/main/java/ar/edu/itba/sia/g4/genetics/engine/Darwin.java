package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.engine.problem.Breeder;
import ar.edu.itba.sia.g4.genetics.engine.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.engine.problem.Mutator;
import ar.edu.itba.sia.g4.genetics.engine.problem.Selector;
import ar.edu.itba.sia.g4.genetics.engine.problem.Species;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Darwin<T extends Species> {
    private final Mutator<T> mutator;
    private final Breeder<T> breeder;
    private final Selector<T> selector;
    private final EvolutionaryTarget<T> target;

    public Darwin(Mutator<T> mutator, Breeder<T> breeder, Selector<T> selector, EvolutionaryTarget<T> target) {
        this.mutator = mutator;
        this.breeder = breeder;
        this.selector = selector;
        this.target = target;
    }

    public List<T> evolve(List<T> population) {
        List<T> previousPopulation;
        List<T> currentPopulation = population;
        long generation = 0;
        do {
            previousPopulation = currentPopulation;
            currentPopulation = getNextGeneration(currentPopulation);
        } while (target.shouldEvolve(previousPopulation, currentPopulation, ++generation));
        return currentPopulation;
    }

    private List<T> getNextGeneration(List<T> population) {
        List<T> offspring = breedAll(population)
         .map(mutator::mutate)
         .collect(Collectors.toList());

        return selector.selectAndReplace(population, offspring);
    }

    private Stream<T> breedAll(List<T> population) {
        return IntStream.range(0, population.size())
         .mapToObj(i -> breedOne(i, population))
         .flatMap(stream -> stream);
    }

    private Stream<T> breedOne(int i, List<T> population) {
        T parent = population.get(i);
        return population.parallelStream()
            .skip(i + 1)
            .flatMap(p -> Arrays.stream(breeder.breed(parent, p)));
    }

}
