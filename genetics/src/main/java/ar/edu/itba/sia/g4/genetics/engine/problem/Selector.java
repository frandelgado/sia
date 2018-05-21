package ar.edu.itba.sia.g4.genetics.engine.problem;

import java.util.List;

public interface Selector<T extends Species> {
    List<T> select(List<T> population);
}
