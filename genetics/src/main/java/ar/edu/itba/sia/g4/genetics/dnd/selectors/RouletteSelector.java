package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.engine.problem.Selector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RouletteSelector implements Selector<DNDCharacter>{
    private int amountToChoose;

    public RouletteSelector(int amountToChoose){
        this.amountToChoose = amountToChoose;
    }
    @Override
    public List<DNDCharacter> select(List<DNDCharacter> population) {
        Random random = new Random();
        List<DNDCharacter> selected = new LinkedList<>();
        double r;
        for(int k = 0; k < this.amountToChoose; k++){
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
