package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.engine.Darwin;
import javafx.scene.web.HTMLEditorSkin;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

    private static CommandLineOptions parseArguments(String... args) {
        CommandLineOptions options = new CommandLineOptions();

        try {
            CmdLineParser parser = new CmdLineParser(options);
            parser.parseArgument(args);
            return options;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }



    public static void main(String... args) {
        CommandLineOptions options = parseArguments(args);
        // Darwin charles = new Darwin();
    }
}
