package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteSelector<T extends Species> extends BaseSelector<T> implements Selector<T> {
    private final Random random;

    public RouletteSelector(long seed, boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random(seed);
    }

    public RouletteSelector(boolean useBoltzmann) {
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
