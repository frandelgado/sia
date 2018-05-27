package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.config.AppConfig;
import ar.edu.itba.sia.g4.genetics.config.MutatorConfig;
import ar.edu.itba.sia.g4.genetics.config.SelectorConfig;
import ar.edu.itba.sia.g4.genetics.config.SelectorsConfig;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.Item;
import ar.edu.itba.sia.g4.genetics.dnd.Profession;
import ar.edu.itba.sia.g4.genetics.dnd.SingleClassDNDCharacterSoup;
import ar.edu.itba.sia.g4.genetics.dnd.crossers.SinglePointCrosser;
import ar.edu.itba.sia.g4.genetics.dnd.mutators.OneAlleleMutator;
import ar.edu.itba.sia.g4.genetics.dnd.selectors.RouletteSelector;
import ar.edu.itba.sia.g4.genetics.dnd.targets.IterationTarget;
import ar.edu.itba.sia.g4.genetics.engine.Darwin;
import ar.edu.itba.sia.g4.genetics.engine.GeneticEngine;
import ar.edu.itba.sia.g4.genetics.engine.MixAllReplacer;
import ar.edu.itba.sia.g4.genetics.engine.Replacer;
import ar.edu.itba.sia.g4.genetics.engine.SelectorMix;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;
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

    private static AppConfig loadConfig(File config) {
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

    private static Profession getProfession(AppConfig config) {
        return Profession.valueOf(config.getProfession().trim().toUpperCase());
    }

    private static Selector<DNDCharacter> getSelector(SelectorConfig selectorConfig) {
        switch (selectorConfig.getType()) {
        case "roulette":
            return new RouletteSelector<>(selectorConfig.isUseBoltzmann());
        default:
            throw new IllegalArgumentException("No such selector");
        }
    }

    private static Selector<DNDCharacter> getSelector(AppConfig config) {
        SelectorsConfig selectors = config.getSelector();
        Selector<DNDCharacter> first = getSelector(selectors.getFirst());
        Selector<DNDCharacter> last = getSelector(selectors.getLast());
        return new SelectorMix<>(first, last, selectors.getMix());
    }

    private static Selector<DNDCharacter> getReplacer(AppConfig config) {
        SelectorsConfig selectors = config.getReplacer();
        Selector<DNDCharacter> first = getSelector(selectors.getFirst());
        Selector<DNDCharacter> last = getSelector(selectors.getLast());
        return new SelectorMix<>(first, last, selectors.getMix());
    }

    private static Replacer<DNDCharacter> getReplacerAlgo(AppConfig config) {
        Selector<DNDCharacter> selector = getSelector(config);
        Selector<DNDCharacter> replacer = getReplacer(config);
        double generationalGap = config.getGenerationalGap();

        switch (config.getReplacementAlgorithm().trim().toLowerCase()) {
        case "mix-all":
            return new MixAllReplacer<>(selector, replacer, generationalGap);
        default:
            throw new IllegalArgumentException("No such replacement algorithm");
        }
    }

    private static Combinator<DNDCharacter> getCombinator(AppConfig config) {
        return new SinglePointCrosser();
    }

    private static Mutator<DNDCharacter> getMutator(AppConfig config, DNDCharacterSoup genesisPool) {
        MutatorConfig mutatorConfig = config.getMutator();
        switch (mutatorConfig.getType().trim().toLowerCase()) {
        case "one-allele":
            return new OneAlleleMutator((ind, gen) -> mutatorConfig.getChance(), genesisPool);

        default:
            throw new IllegalArgumentException("No such mutator");
        }
    }

    private static EvolutionaryTarget<DNDCharacter> getTarget(AppConfig config) {
        return new IterationTarget(config.getTarget().getIterations());
    }

    private static DNDCharacterSoup loadPrimordialSoup(AppConfig config) {
        List<Item> gauntlets = loadItemCollection(config, "gauntlets");
        List<Item> helmets = loadItemCollection(config, "helmets");
        List<Item> boots = loadItemCollection(config, "boots");
        List<Item> weapons = loadItemCollection(config, "weapons");
        List<Item> chestplates = loadItemCollection(config, "chestplates");

        Profession profession = getProfession(config);
        int populationSize = config.getPopulationSize();

        logger.info("Generating warriors");
        DNDCharacterSoup genesisPool = new SingleClassDNDCharacterSoup(populationSize, profession)
         .setBoots(boots)
         .setChestplates(chestplates)
         .setGauntlets(gauntlets)
         .setWeapons(weapons)
         .setHelmets(helmets);
        return genesisPool;
    }

    public static void main(String... args) {
        CommandLineOptions options = parseArguments(args);
        AppConfig config = loadConfig(options.getConfig());

        DNDCharacterSoup genesisPool = loadPrimordialSoup(config);
        List<DNDCharacter> population = genesisPool.miracleOfLife();

        EvolutionaryTarget<DNDCharacter> target = getTarget(config);
        Mutator<DNDCharacter> mutator = getMutator(config, genesisPool);
        Combinator<DNDCharacter> crosser = getCombinator(config);
        Replacer<DNDCharacter> replacer = getReplacerAlgo(config);

        GeneticEngine<DNDCharacter> charles = new Darwin(target, crosser, mutator, replacer);

        logger.info("Running the engine");
        long startTime = System.currentTimeMillis();
        List<DNDCharacter> evolved = charles.evolve(population);
        long stopTime = System.currentTimeMillis();

        evolved.forEach(p -> System.out.println(p));
        logger.info("Elapsed {}ms", stopTime - startTime);
    }
}
