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
        if (iterations <= 1) {
            throw new IllegalArgumentException("Iterations must be > 1");
        }
    }

    @Override
    public boolean shouldEvolve(List<T> prev, List<T> curr, long generation) {
        if (generation == 0) {
            generations.add(prev);
        }
        generations.add(curr);
        if (generations.size() <= iterations) {
            return true;
        } else {
            generations.remove(0);
        }

        int survivors = curr.stream()
                .distinct()
                .parallel()
                .mapToInt(thing -> generations.stream()
                        .parallel()
                        .mapToInt(g -> (int) g.stream().filter(thing::equals).count())
                        .min()
                        .orElse(0))
                .sum();

        return survivors < delta * curr.size();
    }
}
