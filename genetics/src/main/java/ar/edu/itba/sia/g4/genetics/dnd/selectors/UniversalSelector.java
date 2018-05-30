package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniversalSelector<T extends Species> extends BaseSelector<T> implements Selector<T> {
    private final Random random;

    public UniversalSelector(long seed, boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random(seed);
    }

    public UniversalSelector(boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random();
    }

    @Override
    protected List<T> doSelection(List<SelectorPair<T>> population, int populationLimit, long generation) {
        double sum = 0;
        double[] accum = new double[population.size()];
        for (int i = 0; i < accum.length; i++) {
            sum += population.get(i).getFitness();
            accum[i] = sum;
        }
        double r = random.nextDouble();
        return IntStream.range(1, populationLimit + 1).parallel()
            .map(j -> {
                double rj = (r + j - 1)/populationLimit;
                int idx = Arrays.binarySearch(accum, rj);
                return idx < 0 ? -(idx + 1) : idx;
            })
            .mapToObj(population::get)
            .map(SelectorPair::getThing)
            .limit(populationLimit)
            .collect(Collectors.toList());
    }

}

