package ar.edu.itba.sia.g4.search.solver.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CliOptions {

    private boolean errorFree;

    @Option(name = "-b", aliases = { "--bfs"}, required = false,
     usage = "Perform a BFS strategy", forbids={"-d", "-a", "-g"})
    private boolean bfs;

    @Option(name = "-d", aliases = { "--dfs" }, required = false,
     usage = "Perform a DFS strategy", forbids={"-b", "-a", "-g"})
    private boolean dfs;

    @Option(name = "-a", aliases = { "--astar" }, required = false,
     usage = "Perform an A* strategy", forbids={"-b", "-d", "-g"}, depends = {"-h"})
    private boolean astar;

    @Option(name = "-g", aliases = { "--greedy" }, required = false,
            usage = "Perform an greedy strategy", forbids={"-b, -d, -a"}, depends = {"-h"})
    private boolean greedy;

    @Option(name = "-p", aliases = { "--problem" }, required = true,
     usage = "The problem to solve")
    private String problem;

    @Option(name = "-h", aliases = { "--heuristic" }, required = false,
     usage = "A heuristic to use")
    private String heuristic;

    @Option(name = "-x", aliases = { "--maxExecutionTime" }, required = false,
     usage = "Max execution time in seconds")
    private int maxExecutionTime;

    public CliOptions(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            // default to dfs
            if (!(isBfs() || isDfs() || isAstar() || isGreedy())) {
                setDfs(true);
            }
            errorFree = true;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public boolean isBfs() {
        return bfs;
    }

    public boolean isGreedy() {
        return greedy;
    }

    public CliOptions setBfs(boolean bfs) {
        this.bfs = bfs;
        return this;
    }

    public boolean isDfs() {
        return dfs;
    }

    public CliOptions setDfs(boolean dfs) {
        this.dfs = dfs;
        return this;
    }

    public boolean isAstar() {
        return astar;
    }

    public CliOptions setAstar(boolean astar) {
        this.astar = astar;
        return this;
    }

    public String getProblem() {
        return problem;
    }

    public CliOptions setProblem(String problem) {
        this.problem = problem;
        return this;
    }

    public String getHeuristic() {
        return heuristic;
    }

    public CliOptions setHeuristic(String heuristic) {
        this.heuristic = heuristic;
        return this;
    }

    public int getMaxExecutionTime() {
        return maxExecutionTime;
    }

    public CliOptions setMaxExecutionTime(int maxExecutionTime) {
        this.maxExecutionTime = maxExecutionTime;
        return this;
    }
}
