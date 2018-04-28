package ar.edu.itba.sia.g4.search.solver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * type 0: DFS
 * type 1: BFS
 * type 2: A* or Dijkstra
 * **/
public interface GenericList<Node> {
    public void add(Node node);
    public Node poll();
}
