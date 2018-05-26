package ar.edu.itba.sia.g4.genetics.cli;

import org.kohsuke.args4j.Option;

import java.io.File;

public class CommandLineOptions {
    @Option(name = "-f", required = true)
    private File config;

    public File getConfig() {
        return config;
    }

    public CommandLineOptions setConfig(File config) {
        this.config = config;
        return this;
    }
}
