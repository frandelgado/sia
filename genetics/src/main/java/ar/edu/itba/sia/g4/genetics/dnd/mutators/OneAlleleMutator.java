package ar.edu.itba.sia.g4.genetics.dnd.mutators;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;

import java.util.Random;

public class OneAlleleMutator implements Mutator<DNDCharacter> {
    private final Random random;
    private ProbabilityFunction probFunc;
    private DNDCharacterSoup soup;

    public OneAlleleMutator(ProbabilityFunction probFunc, DNDCharacterSoup soup){
        this.probFunc = probFunc;
        this.soup = soup;
        this.random = new Random();
    }

    public OneAlleleMutator(ProbabilityFunction probFunc, DNDCharacterSoup soup, long seed){
        this.probFunc = probFunc;
        this.soup = soup;
        this.random = new Random(seed);
    }

    @Override
    public DNDCharacter mutate(DNDCharacter ind) {
        Object[] cromosome = ind.getCromosome();
        int i = ind.hashCode() % (cromosome.length);
        switch (i){
            case 0: cromosome[i] = this.soup.getRandomHelmet();break;
            case 1: cromosome[i] = this.soup.getRandomWeapon();break;
            case 2: cromosome[i] = this.soup.getRandomChestplate();break;
            case 3: cromosome[i] = this.soup.getRandomGauntlets();break;
            case 4: cromosome[i] = this.soup.getRandomBoots();break;
            case 5: cromosome[i] = this.soup.getRandomHeight();break;
        }
        DNDCharacter mutatedInd  = new DNDCharacter(ind.getProfession(), (Item) cromosome[0],
                (Item) cromosome[1],(Item) cromosome[2], (Item) cromosome[3],
                (Item) cromosome[4], (double) cromosome[5]);
        return mutatedInd;
    }

    @Override
    public boolean shouldMutate(DNDCharacter ind, long generation) {
        return random.nextDouble() <= this.probFunc.f(ind, generation);
    }
}