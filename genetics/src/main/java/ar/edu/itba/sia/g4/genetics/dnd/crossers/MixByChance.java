package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.Couple;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Random;

public abstract class MixByChance<T extends Species> implements Combinator<T> {
    private final double chance;
    private final Random rnd;

    public MixByChance(double chance, Random rnd) {
        this.chance = chance;
        this.rnd = rnd;
    }

    public MixByChance(double chance) {
        this.chance = chance;
        this.rnd = new Random();
    }

    @Override
    public final boolean shouldBreed(Couple<T> parents, long generation) {
        return rnd.nextDouble() < chance;
    }
}
