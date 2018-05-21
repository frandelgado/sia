package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.engine.problem.Crossover;

import java.util.Random;

public class SinglePointCrosser implements Crossover<DNDCharacter> {
    @Override
    public DNDCharacter[] breed(DNDCharacter papi, DNDCharacter mami) {
        Random random = new Random();
        int pointIndex = random.nextInt(6);
        Object[] offspring1Cromosome = papi.getCromosome();
        Object[] offspring2Cromosome = mami.getCromosome();
        Object allele;
        for(int i = pointIndex; i < 6; i++){
            allele = offspring1Cromosome[i];
            offspring1Cromosome[i] = offspring2Cromosome[i];
            offspring2Cromosome[i] = allele;
        }
        DNDCharacter offspring1  = new DNDCharacter(papi.getProfession(), (Item) offspring1Cromosome[0],
            (Item) offspring1Cromosome[1],(Item) offspring1Cromosome[2], (Item) offspring1Cromosome[3],
                (Item) offspring1Cromosome[4], (double) offspring1Cromosome[5]);
        DNDCharacter offspring2  = new DNDCharacter(mami.getProfession(), (Item) offspring2Cromosome[0],
                (Item) offspring2Cromosome[1],(Item) offspring2Cromosome[2], (Item) offspring2Cromosome[3],
                (Item) offspring2Cromosome[4], (double) offspring2Cromosome[5]);
        return new DNDCharacter[] {offspring1, offspring2};
    }
}

