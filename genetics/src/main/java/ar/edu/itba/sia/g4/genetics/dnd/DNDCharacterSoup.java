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

    DNDCharacterSoup setHelmets(List<Item> helmets);

    List<Item> getWeapons();

    DNDCharacterSoup setWeapons(List<Item> weapons);

    List<Item> getChestplates();

    DNDCharacterSoup setChestplates(List<Item> chestplates);

    List<Item> getGauntlets();

    DNDCharacterSoup setGauntlets(List<Item> gauntlets);

    List<Item> getBoots();

    DNDCharacterSoup setBoots(List<Item> boots);
}
