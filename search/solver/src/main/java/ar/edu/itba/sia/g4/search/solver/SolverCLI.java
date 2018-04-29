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
            String heuristic = "ar.edu.itba.sia.g4.search.rollingcube.heuristic.NilHeuristic";
            Class<Heuristic> Heuristic = (Class<Heuristic>) loader.loadClass(heuristic);
            return Heuristic.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            String game = "ar.edu.itba.sia.g4.search.rollingcube.game.RollingCubeGame";
            Class<Problem> Problem = (Class<Problem>) loader.loadClass(game);
            return Problem.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return new Solver(getProblem(options), getHeuristic(options));
    }

    private static int getSolverBackend(@NotNull final CliOptions options) {
        int type = 0;
        type = options.isDfs() ? 0 : type;
        type = options.isBfs() ? 1 : type;
        type = options.isAstar() ? 2 : type;
        return type;
    }


    public static void main(final String... args) {
        CliOptions options = parseOptions(args);

        Solver solver = getSolver(options);
        double startTimestamp = System.currentTimeMillis();
        Node node = solver.solve(getSolverBackend(options));
        double stopTimestamp = System.currentTimeMillis();

        if (node == null) {
            System.err.println("The game died.");
            System.exit(-1);
        }

        double cost = node.getCost();
        int expanded = node.getVisitedNodes();
        double deltaT = stopTimestamp - startTimestamp;

        System.out.printf("Solved problem in %ems", deltaT);
        System.out.printf("Expanded %d nodes", expanded);

        System.out.println("The costs were:");
        System.out.printf("- Total raw cost: %e", cost);
    }


}
