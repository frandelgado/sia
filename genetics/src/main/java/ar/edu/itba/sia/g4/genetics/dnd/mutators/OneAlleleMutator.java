package ar.edu.itba.sia.g4.genetics.dnd.mutators;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.SingleClassDNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;

import java.util.Random;

public class OneAlleleMutator implements Mutator<DNDCharacter> {
    private final Random random;
    private final ProbabilityFunction probFunc;
    private final DNDCharacterSoup soup;

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
        Object[] chromosome = ind.getChromosome();
        int i = ind.hashCode() % (chromosome.length);
        switch (i){
            case 0: chromosome[i] = this.soup.getRandomHelmet();break;
            case 1: chromosome[i] = this.soup.getRandomWeapon();break;
            case 2: chromosome[i] = this.soup.getRandomChestplate();break;
            case 3: chromosome[i] = this.soup.getRandomGauntlets();break;
            case 4: chromosome[i] = this.soup.getRandomBoots();break;
            case 5: chromosome[i] = this.soup.getRandomHeight();break;
        }
        DNDCharacter mutatedInd  = new DNDCharacter(ind.getProfession(), (Item) chromosome[0],
                (Item) chromosome[1],(Item) chromosome[2], (Item) chromosome[3],
                (Item) chromosome[4], (double) chromosome[5]);
        return mutatedInd;
    }

    @Override
    public boolean shouldMutate(DNDCharacter ind, long generation) {
        return random.nextDouble() <= this.probFunc.f(ind, generation);
    }
}