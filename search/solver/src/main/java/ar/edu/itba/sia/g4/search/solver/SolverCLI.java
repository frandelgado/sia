package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.g4.search.solver.cli.CliOptions;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.lang.reflect.InvocationTargetException;

public class SolverCLI {

    private static CliOptions parseOptions(final String... args) {
        CliOptions values = new CliOptions(args);
        CmdLineParser parser = new CmdLineParser(values);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("Couldn't parse the command line");
            System.exit(1);
        }
        return values;
    }

    private static Heuristic getHeuristic(@NotNull final CliOptions options) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            String heuristic = options.getHeuristic();
            Class<Heuristic> Heuristic = (Class<Heuristic>) loader.loadClass(heuristic);
            return Heuristic.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            System.err.println("Could not load the class for the heuristic.");
            System.exit(4);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Problem getProblem(@NotNull final CliOptions options) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            String game = options.getProblem();
            Class<Problem> Problem = (Class<Problem>) loader.loadClass(game);
            return Problem.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            System.err.println("Could not load the class for the problem.");
            System.exit(2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Solver getSolver(@NotNull final CliOptions options) {
        return new Solver(getProblem(options), getSolverBackend(options));
    }

    private static SearchStrategy getSolverBackend(@NotNull final CliOptions options) {
        if (options.isDfs()) {
            return new DFSStrategy();
        }

        if (options.isBfs()) {
            return new BFSStrategy();
        }

        if (options.isAstar()) {
            return new AStarStrategy(getHeuristic(options));
        }

        if (options.isGreedy()) {
            return new GreedyStrategy(getHeuristic(options));
        }

        if (options.isIddfs()) {
            return new IDDFSStrategy();
        }

        throw new IllegalArgumentException("No such solver");
    }


    public static void main(final String... args) {
        CliOptions options = parseOptions(args);

        Solver solver = getSolver(options);

        double startTimestamp = System.currentTimeMillis();
        Node node = solver.solve();
        double stopTimestamp = System.currentTimeMillis();

        if (node == null) {
            System.err.println("No solution found.");
            System.exit(-1);
        }

        double cost = node.getCost();
        int expanded = node.getVisitedNodes();
        double deltaT = stopTimestamp - startTimestamp;

        System.out.printf("Solved problem in %f s\n", deltaT/1000);
        System.out.printf("Expanded %d nodes\n", expanded);

        System.out.println("The costs were:");
        System.out.printf("- Total raw cost: %e", cost);
        System.out.println();
    }


}
