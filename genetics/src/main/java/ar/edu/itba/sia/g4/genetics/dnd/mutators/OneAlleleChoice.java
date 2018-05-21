package ar.edu.itba.sia.g4.genetics.dnd.mutators;

public interface OneAlleleChoice {
    public double mutatationProb(long generation, int alleleIndex);
}
