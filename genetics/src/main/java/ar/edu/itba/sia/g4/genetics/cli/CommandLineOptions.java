package ar.edu.itba.sia.g4.genetics.cli;

import org.kohsuke.args4j.Option;

import java.io.File;

public class CommandLineOptions {
    @Option(name = "-f", required = true)
    private File config;

    @Option(name = "-v")
    private boolean verbose;

    @Option(name = "-x")
    private boolean x11;

    @Option(name = "-s")
    private boolean stats;

    public boolean isStats() {
        return stats;
    }

    public CommandLineOptions setStats(boolean stats) {
        this.stats = stats;
        return this;
    }

    public File getConfig() {
        return config;
    }

    public CommandLineOptions setConfig(File config) {
        this.config = config;
        return this;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public CommandLineOptions setVerbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public boolean isX11() {
        return x11;
    }

    public CommandLineOptions setX11(boolean x11) {
        this.x11 = x11;
        return this;
    }
}
