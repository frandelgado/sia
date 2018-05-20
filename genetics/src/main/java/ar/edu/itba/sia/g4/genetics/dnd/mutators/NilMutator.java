package ar.edu.itba.sia.g4.genetics.dnd.mutators;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.engine.problem.Mutator;

public class NilMutator implements Mutator<DNDCharacter> {

    @Override
    public DNDCharacter mutate(DNDCharacter ind) {
        return ind;
    }
}
