package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.dnd.DNDCharacter;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;

import java.util.List;

public class ContentTarget implements EvolutionaryTarget<DNDCharacter> {
    private long generationsBreach;
    private List<DNDCharacter> prevPop;
    private double[] prevMaxFitnesses;

    public ContentTarget(long generationsBreach) {
        if (generationsBreach < 1 ) {
            throw new IllegalArgumentException("generations breach must be > 0");
        }
        this.generationsBreach = generationsBreach;
        this. prevMaxFitnesses = new double[(int)generationsBreach +1];
    }

    @Override
    public boolean shouldEvolve(List<DNDCharacter> prev, List<DNDCharacter> curr, long generation) {
        if(!prev.isEmpty()) {
            prevMaxFitnesses[(int)(generation%generationsBreach)] = curr.parallelStream()
                    .mapToDouble(DNDCharacter::getFitness)
                    .max().orElse(0);


            return compare(prevMaxFitnesses[(int)(generation-1%generationsBreach)],
                    prevMaxFitnesses[(int)(generation%generationsBreach)]);
        }
        return true;
    }

    private boolean compare(double fitness1, double fitness2){
        return Math.abs(fitness1 - fitness2) < 0.0001;
    }

}
