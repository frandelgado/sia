package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.engine.Inspector;
import ar.edu.itba.sia.g4.genetics.problem.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StatsInspector<T extends Species> implements Inspector<T>, AutoCloseable {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    private final TSVWriter writer;

    public StatsInspector(Path path) throws IOException {
        this.writer = TSVWriter.toFile(path);
        writer.writeHeader("avgFitness", "fittest", "variance");
    }

    @Override
    public void onGeneration(List<T> prev, List<T> cur, long generation) {
        double oldAvgFitness = prev.parallelStream().mapToDouble(Species::getFitness).average().orElse(0);
        double avgFitness = cur.parallelStream().mapToDouble(Species::getFitness).average().orElse(0);
        double fittest = cur.parallelStream().mapToDouble(Species::getFitness).max().orElse(0);
        double leastFit = cur.parallelStream().mapToDouble(Species::getFitness).min().orElse(0);
        logger.info("Generation {}", generation);
        logger.info("Avg fitness {}", avgFitness);
        logger.info("Delta fitness {}", -oldAvgFitness + avgFitness);
        logger.info("Least fit {}", leastFit);
        logger.info("Fitness Breach {}", fittest - leastFit);
        logger.info("Fittest {}", fittest);
        // me gustaria precalcular este sum en uno de los streams pasados.
        double sum = cur.parallelStream().mapToDouble(Species::getFitness).sum();
        double variance = cur.parallelStream().
         mapToDouble(Species::getFitness)
         .map(f -> (f/sum )* Math.pow(f-avgFitness,2))
         .sum();
        logger.info("Variance {}",variance);
        try {
            if (generation % 1000 == 0) {
                writer.writeLine(String.valueOf(avgFitness),
                 String.valueOf(fittest), String.valueOf(variance));
            }
        } catch (IOException e) {
            logger.error("Oops");
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
