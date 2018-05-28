package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;

import java.util.List;

public class IterationTarget implements EvolutionaryTarget<DNDCharacter> {
    private final long iterations;

    public IterationTarget(long iterations) {
        this.iterations = iterations;
        if (iterations <= 0) {
            throw new IllegalArgumentException("Iterations must be > 0");
        }
    }

    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {
        return generation < iterations;
    }
}
