package ar.edu.itba.sia.g4.genetics.problem;

import java.util.List;

public interface Selector<T extends Species> {
    List<T> select(List<T> population, int populationLimit, long generation);

}
