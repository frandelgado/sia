package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseSelector<T extends Species> implements Selector<T> {
    private final boolean useBoltzmann;

    BaseSelector(boolean useBoltzmann) {
        this.useBoltzmann = useBoltzmann;
    }

    @Override
    public List<T> select(List<T> population, int populationLimit, long generation) {
        double[] fitnesses = getFitness(population, populationLimit, generation);
        List<SelectorPair<T>> populationMap = IntStream.range(0, population.size())
         .parallel()
         .mapToObj(i -> new SelectorPair<>(population.get(i), fitnesses[i]))
         .collect(Collectors.toList());
        return doSelection(populationMap, populationLimit, generation);
    }

    private double[] getFitness(List<T> population, int populationLimit, long generation) {
        if (useBoltzmann) {
            return getFitnessWithBoltzmann(population, populationLimit, generation);
        }
        return population.stream().parallel().mapToDouble(Species::getFitness).toArray();
    }

    private double[] getFitnessWithBoltzmann(List<T> population, int populationLimit, long generation) {
        double t = getTemperature(generation);
        double[] fitnesses = population.stream().parallel()
         .mapToDouble(Species::getFitness)
         .map(f -> Math.exp(f / t))
         .toArray();
        double avg = Arrays.stream(fitnesses).average().getAsDouble();
        return Arrays.stream(fitnesses).map(d -> d / avg).toArray();
    }

    double getTemperature(long generation){
        return 1.0 / 0.001*(generation + 1) + 1;
    }
    protected abstract List<T> doSelection(List<SelectorPair<T>> population, int populationLimit, long generation);
}
