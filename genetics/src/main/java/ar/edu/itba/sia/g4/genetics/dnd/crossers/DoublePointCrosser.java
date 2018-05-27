package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.Couple;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DoublePointCrosser implements Combinator<DNDCharacter> {
    @Override
    public Couple<DNDCharacter> breed(Couple<DNDCharacter> couple) {
        DNDCharacter papi = couple.getHead();
        DNDCharacter mami = couple.getTail();
        int firstPoint = new Random().nextInt(6);
        int secondPoint = new Random().nextInt(6);;
        while( secondPoint == firstPoint){
            secondPoint = new Random().nextInt(6);
        }
        Object[] offspring1chromosome = papi.getChromosome();
        Object[] offspring2chromosome = mami.getChromosome();
        Object allele;
        for(int i = firstPoint; i != secondPoint; i++){
            allele = offspring1chromosome[i];
            offspring1chromosome[i] = offspring2chromosome[i];
            offspring2chromosome[i] = allele;
            // The chromosomes are thought of as rings
            // with the first and last gene connected.
            if(i == 5){
                i = 0;
            }
        }
        DNDCharacter offspring1  = new DNDCharacter(papi.getProfession(), (Item) offspring1chromosome[0],
                (Item) offspring1chromosome[1],(Item) offspring1chromosome[2], (Item) offspring1chromosome[3],
                (Item) offspring1chromosome[4], (double) offspring1chromosome[5]);
        DNDCharacter offspring2  = new DNDCharacter(mami.getProfession(), (Item) offspring2chromosome[0],
                (Item) offspring2chromosome[1],(Item) offspring2chromosome[2], (Item) offspring2chromosome[3],
                (Item) offspring2chromosome[4], (double) offspring2chromosome[5]);
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
