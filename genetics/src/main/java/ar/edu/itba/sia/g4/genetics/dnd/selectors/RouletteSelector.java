package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.Selector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RouletteSelector implements Selector<DNDCharacter> {
    @Override
    public List<DNDCharacter> select(List<DNDCharacter> population, int populationLimit) {
        Random random = new Random();
        List<DNDCharacter> selected = new LinkedList<>();
        double r;
        for(int k = 0; k < populationLimit; k++){
            r = random.nextDouble();
            for (DNDCharacter d: population) {
                if(d.getFitness() > r){
                    selected.add(d);
                    break;
                }
            }
        }
        return selected;
    }
}
