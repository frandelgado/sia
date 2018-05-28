package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

public class OptimumTarget<T extends Species> implements EvolutionaryTarget<DNDCharacter> {
    private final double delta;
    private final int iterations;
    private int plateauCount;

    public OptimumTarget(double delta, int iterations) {
        this.delta = delta;
        this.iterations = iterations;
        this.plateauCount = 0;
        if (delta <= 0) {
            throw new IllegalArgumentException("Delta must be > 0");
        }
        if (iterations <= 0) {
            throw new IllegalArgumentException("Iterations must be > 0");
        }
    }

    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {
        double prevFitness = prev.stream().mapToDouble(Species::getFitness).average().orElse(0);
        double currFitness = curr.stream().mapToDouble(Species::getFitness).average().orElse(0);
        plateauCount = Math.abs(currFitness - prevFitness) <= delta ? plateauCount + 1 : 0;
        return plateauCount < iterations;
    }
}
