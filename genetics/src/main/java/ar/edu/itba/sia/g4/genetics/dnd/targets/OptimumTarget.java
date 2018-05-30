package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.LinkedList;
import java.util.List;

public class OptimumTarget<T extends Species> implements EvolutionaryTarget<DNDCharacter> {
    private final double delta;
    private final int iterations;
    private List<Double> deltas;

    public OptimumTarget(double delta, int iterations) {
        this.delta = delta;
        this.iterations = iterations;
        deltas = new LinkedList<>();
        if (delta <= 0) {
            throw new IllegalArgumentException("Delta must be > 0");
        }
        if (iterations <= 0) {
            throw new IllegalArgumentException("Iterations must be > 0");
        }
    }

    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {
        if (generation == 0) {
            deltas.add(prev.stream()
             .mapToDouble(Species::getFitness)
             .average()
             .orElse(0));
        }
        deltas.add(curr.stream()
         .mapToDouble(Species::getFitness)
         .average()
         .orElse(0));

        if (deltas.size() > iterations) {
            deltas = deltas.subList(deltas.size() - iterations, deltas.size());
        } else {
            return true;
        }

        double max = deltas.stream().max(Double::compare).orElse(0.0);
        double min = deltas.stream().min(Double::compare).orElse(0.0);

        return min / max < delta;
    }
}
