package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.engine.problem.Selector;

import java.util.List;

public class NilSelector implements Selector<DNDCharacter> {
    @Override
    public List<DNDCharacter> selectAndReplace(List<DNDCharacter> oldPopulation, List<DNDCharacter> offspring) {
        return oldPopulation;
    }
}
