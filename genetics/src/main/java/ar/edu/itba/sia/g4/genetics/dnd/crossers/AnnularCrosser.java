package ar.edu.itba.sia.g4.genetics.dnd.crossers;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.Couple;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class AnnularCrosser extends
 MixByChance<DNDCharacter> implements Combinator<DNDCharacter> {
    public AnnularCrosser(double chance, Random rnd) {
        super(chance, rnd);
    }

    public AnnularCrosser(double chance) {
        super(chance);
    }

    @Override
    public Couple<DNDCharacter> breed(Couple<DNDCharacter> parents) {
        DNDCharacter papi = parents.getHead();
        DNDCharacter mami = parents.getTail();

        Random random = new Random();
        int pointIndex = random.nextInt(6);
        int length = random.nextInt(3);
        
        Object[] offspring1Chromosome = papi.getChromosome();
        Object[] offspring2Chromosome = mami.getChromosome();

        Object allele;
        for(int i = pointIndex; length >= 0; i++){
            allele = offspring1Chromosome[i];
            offspring1Chromosome[i] = offspring2Chromosome[i];
            offspring2Chromosome[i] = allele;
            if(i==5)
                i=0;
            length--;
        }

        DNDCharacter offspring1  = new DNDCharacter(papi.getProfession(), (Item) offspring1Chromosome[0],
                (Item) offspring1Chromosome[1],(Item) offspring1Chromosome[2], (Item) offspring1Chromosome[3],
                (Item) offspring1Chromosome[4], (double) offspring1Chromosome[5]);
        DNDCharacter offspring2  = new DNDCharacter(mami.getProfession(), (Item) offspring2Chromosome[0],
                (Item) offspring2Chromosome[1],(Item) offspring2Chromosome[2], (Item) offspring2Chromosome[3],
                (Item) offspring2Chromosome[4], (double) offspring2Chromosome[5]);
        return new Couple<>(offspring1, offspring2);
    }

    @Override
    public List<Couple<DNDCharacter>> pickCouples(List<DNDCharacter> p, int couples) {
        Random random = new Random();
        return random.ints(0, p.size()).parallel()
                .mapToObj(i -> new Couple<>(p.get(i), p.get((i + 1) % p.size())))
                .limit(couples)
                .collect(Collectors.toList());
    }
}
