package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.*;
import java.util.stream.Collectors;

public class RankingSelector<T extends Species> extends BaseSelector<T> implements Selector<T> {
    private final Random random;

    public RankingSelector(long seed, boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random(seed);
    }

    public RankingSelector(boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random();
    }

    @Override
    protected List<T> doSelection(List<SelectorPair<T>> population, int populationLimit, long generation) {

        List<T> sortedPopulation = population.stream()
                .sorted(Comparator.comparing((SelectorPair sp) -> sp.getFitness()).reversed())
                .map(SelectorPair::getThing)
                .collect(Collectors.toList());

        double sum = 0;
        double[] accum = new double[sortedPopulation.size()];
        double ranking;
        for (int i = 0; i < accum.length; i++) {
            ranking = 1 + i;
            sum += ranking;
            accum[i] = sum;
        }
        return random.doubles(0, sum).parallel()
                .mapToInt(r -> {
                    int idx = Arrays.binarySearch(accum, r);
                    return idx < 0 ? -(idx + 1) : idx;
                })
                .mapToObj(population::get)
                .map(SelectorPair::getThing)
                .limit(populationLimit)
                .collect(Collectors.toList());
    }
}
