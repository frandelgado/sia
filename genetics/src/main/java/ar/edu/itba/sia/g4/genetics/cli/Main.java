package ar.edu.itba.sia.g4.genetics.cli;

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
import ar.edu.itba.sia.g4.genetics.engine.NewGenerationReplacer;
import ar.edu.itba.sia.g4.genetics.engine.Replacer;
import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;
import ar.edu.itba.sia.g4.genetics.problem.PrimordialSoup;
import ar.edu.itba.sia.g4.genetics.problem.Selector;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public static void main(String... args) {
        CommandLineOptions options = parseArguments(args);

        logger.info("Loading files");
        List<Item> gauntlets = ItemLoader.loadFromFile(Paths.get("items/guantes.tsv"));
        List<Item> helmets = ItemLoader.loadFromFile(Paths.get("items/cascos.tsv"));
        List<Item> boots = ItemLoader.loadFromFile(Paths.get("items/botas.tsv"));
        List<Item> weapons = ItemLoader.loadFromFile(Paths.get("items/armas.tsv"));
        List<Item> chestplates = ItemLoader.loadFromFile(Paths.get("items/pecheras.tsv"));

        logger.info("Generating warriors");
        DNDCharacterSoup genesisPool = new Warrior1DNDCharacterSoup(1000)
            .setBoots(boots)
            .setChestplates(chestplates)
            .setGauntlets(gauntlets)
            .setWeapons(weapons)
            .setHelmets(helmets);
        List<DNDCharacter> population = genesisPool.miracleOfLife();

        //        Mutator<DNDCharacter> nilMutator = new NilMutator();
//        Crossover<DNDCharacter> nilCrosser = new NilCrosser();
        Selector<DNDCharacter> nilSelector = new RouletteSelector();
        EvolutionaryTarget<DNDCharacter> nilTarget = new NilTarget();
        Mutator<DNDCharacter> oneAlleleMutator = new OneAlleleMutator((ind, gen) -> 0, genesisPool);
        Combinator<DNDCharacter> singlePointCrosser = new SinglePointCrosser();
        Replacer<DNDCharacter> replacer = new NewGenerationReplacer<>();

        Darwin<DNDCharacter> charles = new Darwin(nilTarget, singlePointCrosser, oneAlleleMutator, replacer);
        logger.info("Running the engine");
        List<DNDCharacter> evolved = charles.evolve(population);

        evolved.forEach(p -> System.out.println(p));
    }
}
