package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.LinkedList;
import java.util.List;

public class StructureTarget<T extends Species> implements EvolutionaryTarget<T> {
    private final int iterations;
    private final double delta;
    private List<List<T>> generations;

    public StructureTarget(int iterations, double delta) {
        this.iterations = iterations;
        this.generations = new LinkedList<>();
        this.delta = delta;
        if (iterations <= 0) {
            throw new IllegalArgumentException("Iterations must be > 0");
        }
    }

    @Override
    public boolean shouldEvolve(List<T> prev, List<T> curr, long generation) {
        if (generation == 0) {
            generations.add(prev);
        }
        long survivors = curr.stream().parallel()
         .filter(p -> generations.stream().allMatch(l -> l.contains(p)))
         .count();

        generations.add(curr);
        if (generations.size() >= iterations) {
            generations.remove(0);
        }

        return survivors < delta * curr.size();
    }
}
