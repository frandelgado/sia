package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.config.AppConfig;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.dnd.Warrior1DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.crossers.SinglePointCrosser;
import ar.edu.itba.sia.g4.genetics.dnd.mutators.ProbabilityFunction;
import ar.edu.itba.sia.g4.genetics.dnd.mutators.OneAlleleMutator;
import ar.edu.itba.sia.g4.genetics.dnd.selectors.RouletteSelector;
import ar.edu.itba.sia.g4.genetics.dnd.targets.NilTarget;
import ar.edu.itba.sia.g4.genetics.engine.Darwin;
import ar.edu.itba.sia.g4.genetics.engine.MixAllReplacer;
import ar.edu.itba.sia.g4.genetics.engine.NewGenerationReplacer;
import ar.edu.itba.sia.g4.genetics.engine.Replacer;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;
import ar.edu.itba.sia.g4.genetics.problem.PrimordialSoup;
import ar.edu.itba.sia.g4.genetics.problem.Selector;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    private static CommandLineOptions parseArguments(String... args) {
        CommandLineOptions options = new CommandLineOptions();
        logger.debug("Parsing arguments");
        try {
            CmdLineParser parser = new CmdLineParser(options);
            parser.parseArgument(args);
            logger.debug("Successfully parsed arguments");
            return options;
        } catch (CmdLineException e) {
            logger.error("Could not parse the cli", e);
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    private static List<Item> loadItemCollection(AppConfig config, String itemType) {
        logger.info("Loading {} item collection", itemType);
        switch (itemType) {
        case "gauntlets": return ItemLoader.loadFromFile(Paths.get(config.getItems().getGauntlets()));
        case "helmets": return ItemLoader.loadFromFile(Paths.get(config.getItems().getHelmets()));
        case "boots": return ItemLoader.loadFromFile(Paths.get(config.getItems().getBoots()));
        case "weapons": return ItemLoader.loadFromFile(Paths.get(config.getItems().getWeapons()));
        case "chestplates": return ItemLoader.loadFromFile(Paths.get(config.getItems().getChestplates()));
        default: throw new IllegalArgumentException("What is that?");
        }
    }


    public static AppConfig loadConfig(File config) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(config, AppConfig.class);
        } catch (IOException e) {
            logger.error("Could not parse the config file", e);
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public static void main(String... args) {
        CommandLineOptions options = parseArguments(args);
        AppConfig config = loadConfig(options.getConfig());

        logger.info("Loading files");
        List<Item> gauntlets = loadItemCollection(config, "gauntlets");
        List<Item> helmets = loadItemCollection(config, "helmets");
        List<Item> boots = loadItemCollection(config, "boots");
        List<Item> weapons = loadItemCollection(config, "weapons");
        List<Item> chestplates = loadItemCollection(config, "chestplates");

        logger.info("Generating warriors");
        DNDCharacterSoup genesisPool = new Warrior1DNDCharacterSoup(config.getPopulationSize())
            .setBoots(boots)
            .setChestplates(chestplates)
            .setGauntlets(gauntlets)
            .setWeapons(weapons)
            .setHelmets(helmets);
        List<DNDCharacter> population = genesisPool.miracleOfLife();

        EvolutionaryTarget<DNDCharacter> nilTarget = new NilTarget();
        Mutator<DNDCharacter> oneAlleleMutator = new OneAlleleMutator((ind, gen) -> 0.02, genesisPool);
        Combinator<DNDCharacter> singlePointCrosser = new SinglePointCrosser();
        Selector<DNDCharacter> selector = new RouletteSelector();
        Replacer<DNDCharacter> replacer = new MixAllReplacer<>(selector, selector, config.getGenerationalGap());

        Darwin<DNDCharacter> charles = new Darwin(nilTarget, singlePointCrosser, oneAlleleMutator, replacer);
        logger.info("Running the engine");
        List<DNDCharacter> evolved = charles.evolve(population);

        evolved.forEach(p -> System.out.println(p));
    }
}
