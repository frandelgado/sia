package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;

import java.util.List;

public class NilTarget implements EvolutionaryTarget<DNDCharacter> {
    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {
        return generation < 100000;
    }
}
