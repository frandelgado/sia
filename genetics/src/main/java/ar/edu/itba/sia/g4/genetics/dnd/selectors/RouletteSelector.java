package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.Selector;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteSelector implements Selector<DNDCharacter> {
    private final Random random;

    public RouletteSelector(long seed) {
        this.random = new Random(seed);
    }

    public RouletteSelector() {
        this.random = new Random();
    }

    @Override
    public List<DNDCharacter> select(List<DNDCharacter> population, int populationLimit) {
        double sum = 0;
        double[] accum = new double[population.size()];
        for (int i = 0; i < accum.length; i++) {
            sum += population.get(i).getFitness();
            accum[i] = sum;
        }
        return random.doubles(0, sum).parallel()
         .limit(populationLimit)
         .mapToInt(r -> {
             int idx = Arrays.binarySearch(accum, r);
             return idx < 0 ? -(idx + 1) : idx;
         })
         .mapToObj(population::get)
         .collect(Collectors.toList());
    }
}
