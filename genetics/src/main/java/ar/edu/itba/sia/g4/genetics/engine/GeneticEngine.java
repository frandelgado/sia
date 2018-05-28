package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Species;

import java.util.List;

interface GeneticEngine<T extends Species> {

    List<T> evolve(List<T> population);
}
