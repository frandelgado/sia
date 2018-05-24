package ar.edu.itba.sia.g4.genetics.dnd;

import ar.edu.itba.sia.g4.genetics.problem.PrimordialSoup;

import java.util.List;

public interface DNDCharacterSoup extends PrimordialSoup<DNDCharacter> {
    double getRandomHeight();

    Item getRandomHelmet();

    Item getRandomWeapon();

    Item getRandomChestplate();

    Item getRandomGauntlets();

    Item getRandomBoots();

    List<Item> getHelmets();

    Warrior1DNDCharacterSoup setHelmets(List<Item> helmets);

    List<Item> getWeapons();

    Warrior1DNDCharacterSoup setWeapons(List<Item> weapons);

    List<Item> getChestplates();

    Warrior1DNDCharacterSoup setChestplates(List<Item> chestplates);

    List<Item> getGauntlets();

    Warrior1DNDCharacterSoup setGauntlets(List<Item> gauntlets);

    List<Item> getBoots();

    Warrior1DNDCharacterSoup setBoots(List<Item> boots);
}
