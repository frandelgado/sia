package ar.edu.itba.sia.g4.genetics.dnd.mutators;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;

public interface ProbabilityFunction {
    double f(DNDCharacter ind, long generation);
}
