package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

public interface Inspector<T extends Species> {
    void onGeneration(List<T> oldGeneration, List<T> newGeneration, long generation);
}
