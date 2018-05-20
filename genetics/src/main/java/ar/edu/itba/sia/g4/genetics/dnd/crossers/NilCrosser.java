package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.engine.problem.Crossover;

public class NilCrosser implements Crossover<DNDCharacter> {
    @Override
    public DNDCharacter[] breed(DNDCharacter papi, DNDCharacter mami) {
        return new DNDCharacter[] {mami, papi};
    }
}
