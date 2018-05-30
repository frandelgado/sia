package ar.edu.itba.sia.g4.genetics.problem;

public interface Species {
    double getFitness();
    Object deepCopy();
}
