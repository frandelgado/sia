package ar.edu.itba.sia.g4.genetics.dnd.targets;

import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StructureTarget<T extends Species> implements EvolutionaryTarget<T> {
    private final int iterations;
    private final double delta;
    private List<List<T>> generations;

    public StructureTarget(int iterations, double delta) {
        this.iterations = iterations;
        this.generations = new LinkedList<>();
        this.delta = delta;
        if (iterations <= 1) {
            throw new IllegalArgumentException("Iterations must be > 1");
        }
    }

    @Override
    public boolean shouldEvolve(List<T> prev, List<T> curr, long generation) {
        if (generation == 0) {
            generations.add(prev);
        }
        generations.add(curr);
        if (generations.size() < iterations) {
            return true;
        }
        generations.remove(0);

        List<List<T>> auxGenerations = generations.stream().map(gen ->
            gen.parallelStream().map(item -> ((T)item.deepCopy())).collect(Collectors.toList())
        ).collect(Collectors.toList());

        long sharedPopulation = curr.stream().filter((T currItem) -> {
            if(auxGenerations.parallelStream()
                .allMatch(gen -> gen.parallelStream()
                        .anyMatch(item -> item.equals(currItem)))){
            auxGenerations.parallelStream().forEach(gen -> gen.remove(currItem));
            return true;
        }
        return false;
        }).count();
        System.out.println("gen: " + generation);
        System.out.println("shared: " + sharedPopulation);
        System.out.println("delta: " + delta * curr.size());
        return sharedPopulation < delta * curr.size();
    }

}
