package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

public class TimeTarget<T extends Species> implements EvolutionaryTarget<T> {
    private final int seconds;
    private double started;

    public TimeTarget(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean shouldEvolve(List<T> prev, List<T> curr, long generation) {
        if (generation == 0) {
            started = System.currentTimeMillis();
        }
        return System.currentTimeMillis() - started < seconds * 1000;
    }
}
