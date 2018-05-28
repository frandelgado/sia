package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StructureTarget<T extends Species> implements EvolutionaryTarget<T> {

    List<T> previousPopulation;
    List<T> currentPopulation;
    Comparator<T> comparator;

    public StructureTarget(List<T> previousPopulation, List<T> currentPopulation) {
        this.previousPopulation = previousPopulation;
        this.currentPopulation = currentPopulation;
        this.comparator = new SortComparator();
    }

    private boolean equal(T c1, T c2){
        return Math.abs(c1.getFitness() - c2.getFitness()) < 0.00001;
    }

    @Override
    public boolean shouldEvolve(List<T> prev, List<T> curr, long generation) {
        double equalAmount = curr.size() * 0.0002;
        List<T> sortedPrev = prev.parallelStream().sorted(this.comparator.reversed()).collect(Collectors.toList());
        List<T> sortedCurr = curr.parallelStream().sorted(this.comparator.reversed()).collect(Collectors.toList());
        int n = IntStream.range(0,prev.size()).parallel()
                .filter(i -> !equal(sortedCurr.get(i), sortedPrev.get(i)))
                .findFirst().getAsInt();
        return n <= 0 && (n - 1) <= equalAmount;
    }

    private class SortComparator<T extends Species> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            double res = o1.getFitness() - o2.getFitness();
            if(res == 0.0){
                return 0;
            }
            return ((o1.getFitness() - o2.getFitness()) > 0.0)?1:0;
        }
    }
}
