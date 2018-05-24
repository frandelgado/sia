package ar.edu.itba.sia.g4.genetics.dnd;

import ar.edu.itba.sia.g4.genetics.problem.PrimordialSoup;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior1DNDCharacterSoup implements PrimordialSoup<DNDCharacter>, DNDCharacterSoup {
    private final int population;
    private static final double MIN_HEIGHT = 1.3;
    private static final double MAX_HEIGHT = 2.0;
    private List<Item> helmets;
    private List<Item> weapons;
    private List<Item> chestplates;
    private List<Item> gauntlets;
    private List<Item> boots;
    private final Random rnd;

    public Warrior1DNDCharacterSoup(int population) {
        this.population = population;
        this.rnd = new Random();
    }

    public Warrior1DNDCharacterSoup(int population, long seed) {
        this.population = population;
        this.rnd = new Random(seed);
    }

    @Override
    public List<DNDCharacter> miracleOfLife() {
        return rnd.doubles(population)
         .mapToObj(this::generate)
         .collect(Collectors.toList());
    }

    private Item select(List<Item> items, int hash) {
        return items.get(Math.abs(hash) % items.size());
    }

    private DNDCharacter generate(double randomSeed) {
        Profession warrior = Profession.WARRIOR1;
        Item helmet = select(getHelmets(), (int) Math.round(randomSeed * Integer.MAX_VALUE));
        Item weapon = select(getWeapons(), helmet.hashCode());
        Item chestplate = select(getChestplates(), weapon.hashCode());
        Item gauntlets = select(getGauntlets(), chestplate.hashCode());
        Item boots = select(getBoots(), gauntlets.hashCode());
        double height = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * randomSeed;
        return new DNDCharacter(warrior, helmet, weapon, chestplate, gauntlets, boots, height);
    }

    @Override
    public double getRandomHeight(){
        return MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * this.rnd.nextDouble();
    }
    @Override
    public Item getRandomHelmet(){
        return select(getHelmets(), (int) Math.round(this.rnd.nextDouble() * Integer.MAX_VALUE));
    }
    @Override
    public Item getRandomWeapon(){
        return select(getWeapons(), (int) Math.round(this.rnd.nextDouble() * Integer.MAX_VALUE));
    }
    @Override
    public Item getRandomChestplate(){
        return select(getChestplates(), (int) Math.round(this.rnd.nextDouble() * Integer.MAX_VALUE));
    }
    @Override
    public Item getRandomGauntlets(){
        return select(getGauntlets(), (int) Math.round(this.rnd.nextDouble() * Integer.MAX_VALUE));
    }
    @Override
    public Item getRandomBoots(){
        return select(getBoots(), (int) Math.round(this.rnd.nextDouble() * Integer.MAX_VALUE));
    }

    @Override
    public  List<Item> getHelmets() {
        return helmets;
    }

    @Override
    public Warrior1DNDCharacterSoup setHelmets(List<Item> helmets) {
        this.helmets = helmets;
        return this;
    }

    @Override
    public List<Item> getWeapons() {
        return weapons;
    }

    @Override
    public Warrior1DNDCharacterSoup setWeapons(List<Item> weapons) {
        this.weapons = weapons;
        return this;
    }

    @Override
    public List<Item> getChestplates() {
        return chestplates;
    }

    @Override
    public Warrior1DNDCharacterSoup setChestplates(List<Item> chestplates) {
        this.chestplates = chestplates;
        return this;
    }

    @Override
    public List<Item> getGauntlets() {
        return gauntlets;
    }

    @Override
    public Warrior1DNDCharacterSoup setGauntlets(List<Item> gauntlets) {
        this.gauntlets = gauntlets;
        return this;
    }

    @Override
    public List<Item> getBoots() {
        return boots;
    }

    @Override
    public Warrior1DNDCharacterSoup setBoots(List<Item> boots) {
        this.boots = boots;
        return this;
    }


}
