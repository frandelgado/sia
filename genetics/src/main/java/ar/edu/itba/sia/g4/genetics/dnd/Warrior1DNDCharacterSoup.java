package ar.edu.itba.sia.g4.genetics.dnd;

import ar.edu.itba.sia.g4.genetics.engine.problem.PrimordialSoup;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior1DNDCharacterSoup implements PrimordialSoup<DNDCharacter> {
    private final int population;
    private static final double MIN_HEIGHT = 1.3;
    private static final double MAX_HEIGHT = 2.0;

    public Warrior1DNDCharacterSoup(int population) {
        this.population = population;
    }

    @Override
    public List<DNDCharacter> miracleOfLife() {
        Random rnd = new Random();
        return rnd.doubles(population)
         .mapToObj(this::generate)
         .collect(Collectors.toList());
    }

    private DNDCharacter generate(double randomSeed) {
        Profession warrior = Profession.WARRIOR1;
        Item helmet = new Item(0, 0, 0, 0, 0);
        Item weapon = new Item(0, 0, 0, 0, 0);
        Item chestplate = new Item(0, 0, 0, 0, 0);
        Item gauntlets = new Item(0, 0, 0, 0, 0);
        Item boots = new Item(0, 0, 0, 0, 0);
        double height = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * randomSeed;
        return new DNDCharacter(warrior, helmet, weapon, chestplate, gauntlets, boots, height);
    }
}
