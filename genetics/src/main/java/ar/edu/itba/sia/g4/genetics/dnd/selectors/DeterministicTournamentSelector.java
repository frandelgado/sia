package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Selector;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeterministicTournamentSelector <T extends Species> extends BaseSelector<T> implements Selector<T> {

    private final Random random;

    public DeterministicTournamentSelector(long seed, boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random(seed);
    }

    public DeterministicTournamentSelector(boolean useBoltzmann) {
        super(useBoltzmann);
        this.random = new Random();
    }

    private T playTournament(List<SelectorPair<T>> p) {
        Random random = new Random();
        int headIndex = random.nextInt(p.size());
        SelectorPair<T> head = p.get(headIndex);
        int tailIndex = random.nextInt(p.size());
        while(headIndex == tailIndex){
            tailIndex = random.nextInt(p.size());
        }
        SelectorPair<T> tail = p.get(tailIndex);

        return head.getFitness() > tail.getFitness() ? head.getThing() : tail.getThing();

    }



    @Override
    protected List<T> doSelection(List<SelectorPair<T>> population, int populationLimit, long generation) {
        List<T> selected =  new ArrayList<>();
        while(selected.size() < populationLimit){
            selected.add(playTournament(population));
        }
        return selected;
    }
}
