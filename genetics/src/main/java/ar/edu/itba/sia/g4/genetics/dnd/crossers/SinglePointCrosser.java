package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.Couple;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SinglePointCrosser implements Combinator<DNDCharacter> {
    @Override
    public Couple<DNDCharacter> breed(Couple<DNDCharacter> couple) {
        DNDCharacter papi = couple.getHead();
        DNDCharacter mami = couple.getTail();

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
        return new Couple<>(offspring1, offspring2);
    }

    @Override
    public boolean shouldBreed(Couple<DNDCharacter> parents, long generation) {
        return true;
    }

    @Override
    public List<Couple<DNDCharacter>> pickCouples(List<DNDCharacter> p, int couples) {
        Random random = new Random();
        return random.ints(0, p.size()).parallel()
         .mapToObj(i -> new Couple<DNDCharacter>(p.get(i), p.get((i + 1) % p.size())))
         .limit(couples)
         .collect(Collectors.toList());
    }
}

