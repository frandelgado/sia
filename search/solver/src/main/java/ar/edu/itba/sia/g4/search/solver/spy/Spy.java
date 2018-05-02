package ar.edu.itba.sia.g4.search.solver.spy;

import ar.edu.itba.sia.g4.search.solver.Node;

import java.util.List;

public interface Spy<E> {

    default void onVisitedNode(Node<E> node) {};

    default void onQueuedChildren(List<Node<E>> nodes) {}

    default void onSolution(Node<E> node) {}

    default void onSolutionMissing(int maxDepthReached, int visitedNodesCount, int enqueuedNodesCount) {}

    default void onDepth(int depth) {}
}
