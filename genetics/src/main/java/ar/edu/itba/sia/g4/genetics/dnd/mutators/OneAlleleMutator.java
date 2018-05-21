package ar.edu.itba.sia.g4.genetics.dnd.mutators;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.dnd.Warrior1DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.engine.problem.Mutator;

import java.util.Random;

public class OneAlleleMutator implements Mutator<DNDCharacter> {
    private OneAlleleChoice probFunc;
    private Warrior1DNDCharacterSoup soup;

    public OneAlleleMutator(OneAlleleChoice probFunc, Warrior1DNDCharacterSoup soup){
        this.probFunc = probFunc;
        this.soup = soup;
    }

    @Override
    public DNDCharacter mutate(DNDCharacter ind, long generation) {
        Object[] cromosome = ind.getCromosome();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            if(random.nextDouble() <= this.probFunc.mutatationProb(generation, i)){
                switch (i){
                    case 0: cromosome[i] = this.soup.getRandomHelmet();break;
                    case 1: cromosome[i] = this.soup.getRandomWeapon();break;
                    case 2: cromosome[i] = this.soup.getRandomChestplate();break;
                    case 3: cromosome[i] = this.soup.getRandomGauntlets();break;
                    case 4: cromosome[i] = this.soup.getRandomBoots();break;
                    case 5: cromosome[i] = this.soup.getRandomHeight();break;
                }
            }
        }
        DNDCharacter mutatedInd  = new DNDCharacter(ind.getProfession(), (Item) cromosome[0],
                (Item) cromosome[1],(Item) cromosome[2], (Item) cromosome[3],
                (Item) cromosome[4], (double) cromosome[5]);
        return mutatedInd;
    }
}