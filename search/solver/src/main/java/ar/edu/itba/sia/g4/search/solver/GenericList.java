package ar.edu.itba.sia.g4.search.solver;


import java.util.List;

/**
 * type 0: DFS
 * type 1: BFS
 * type 2: A* or Dijkstra
 * **/
public interface GenericList<T extends Node<?>> {
    void add(T node);

    default void addAll(List<T> nodes) {
        for (T node : nodes) {
            add(node);
        }
    }

    Node poll();
}
