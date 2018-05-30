package ar.edu.itba.sia.g4.genetics.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TSVWriter {
    private final BufferedWriter writer;
    private boolean headerWritten;

    private TSVWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public static TSVWriter toFile(File outputFile) throws IOException {
        return toFile(outputFile.toPath());
    }

    public static TSVWriter toFile(Path path) throws IOException {
        return new TSVWriter(Files.newBufferedWriter(path));
    }

    public TSVWriter writeHeader(String... items) throws IOException {
        if (headerWritten) {
            throw new IllegalArgumentException("Can't write header twice...");
        }
        writer.write(String.join("\t", items));
        headerWritten = true;
        return this;
    }
    public TSVWriter writeLine(String... items) throws IOException {
        writer.write(String.join("\t", items));
        return this;
    }
}
