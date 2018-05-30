package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EliteSelector<T extends Species> extends BaseSelector<T> implements Selector<T>
{
    private final Random random;

    public EliteSelector(long seed, boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random(seed);
    }

    public EliteSelector(boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random();
    }

    @Override
    protected List<T> doSelection(List<SelectorPair<T>> population, int populationLimit, long generation) {

        List<T> selected = population.stream()
                .sorted(Comparator.comparing((SelectorPair sp) -> sp.getFitness()).reversed())
                .map(SelectorPair::getThing)
                .collect(Collectors.toList());

        return selected.subList(0,populationLimit);
    }


}
