package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;

import java.util.List;
import java.util.stream.IntStream;

public class StructureTarget<T> implements EvolutionaryTarget<DNDCharacter> {

    List<DNDCharacter> previousPopulation;
    List<DNDCharacter> currentPopulation;

    public StructureTarget(List<DNDCharacter> previousPopulation, List<DNDCharacter> currentPopulation) {
        this.previousPopulation = previousPopulation;
        this.currentPopulation = currentPopulation;
    }

    private boolean equal(DNDCharacter c1, DNDCharacter c2){
        return c1.getFitness() - c2.getFitness() < 0.00001;


    }

    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {

//        int n = IntStream.range(0,prev.size()).parallel()
//                .findFirst(i -> {
//                    return equal(curr.get(i), prev.get(i));
//                });
        return true;
    }
}
