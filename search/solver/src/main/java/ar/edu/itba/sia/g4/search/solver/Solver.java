package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Problem;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Solver<E> {

    private final Problem<E> problem;
    private final SearchStrategy<E> strategy;
    private final List<Consumer<Node<E>>> spies = new LinkedList<>();

    public Solver(@NotNull Problem<E> problem, @NotNull SearchStrategy<E> strategy){
        this.problem = problem;
        this.strategy = strategy;
    }

    private void dispatchNodeToSpies(Node<E> node) {
        for (Consumer<Node<E>> spy : spies) {
            spy.accept(node);
        }
    }

    public void subscribe(@NotNull Consumer<Node<E>> spy) {
        spies.add(spy);
    }

    public Node<E> solve(){
        int enqueuedNodesCount = 1; // added to the queue
        int visitedNodesCount = 1; // visited and tested as solution
        HashSet<E> visitedStates = new HashSet<>();

        SearchStrategy<E> gejo = this.strategy;
        Node<E> node = gejo.nodeFromInitialState(problem.getInitialState());
        gejo.offer(node);

        while ((node = gejo.getNextNode()) != null && !problem.isResolved(node.getState())) {

            visitedStates.add(node.getState());
            dispatchNodeToSpies(node);

            List<Node<E>> children = gejo.explodeChildren(node, problem.getRules(node.getState()))
                .filter(child -> !visitedStates.contains(child.getState()))
                .collect(Collectors.toList());
            gejo.offerAll(children);
            enqueuedNodesCount += children.size();
            node.setVisitedNodes(++visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
        }

        return node == null ? null : node.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(visitedNodesCount);

    }

    public Problem<E> getProblem() {
        return problem;
    }

    public SearchStrategy<E> getStrategy() {
        return strategy;
    }
}
