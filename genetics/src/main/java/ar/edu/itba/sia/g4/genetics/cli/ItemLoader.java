package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.dnd.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ItemLoader {
    public static List<Item> loadFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines().skip(1).parallel()
             .map(ItemLoader::itemFromLine)
             .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("File can't be parsed. :(");
        }
    }
    
    private static Item itemFromLine(String s) {
        String[] fields = s.split("\t");
        String id = fields[0].trim();
        double str = Double.parseDouble(fields[1]);
        double agi = Double.parseDouble(fields[2]);
        double exp = Double.parseDouble(fields[3]);
        double res = Double.parseDouble(fields[4]);
        double hp = Double.parseDouble(fields[5]);
        return new Item(id, str, agi, exp, res, hp);
    }
}
