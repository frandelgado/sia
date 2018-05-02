package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Problem;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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

    private boolean nodeHasALoop(@NotNull Node<E> node) {
        E myState = node.getState();
        Node<E> parent;
        while ((parent = node.getParent()) != null) {
            if (myState.equals(parent.getState())) {
                return true;
            }
        }
        return false;
    }

    public Node<E> solve(){
        int enqueuedNodesCount = 1; // added to the queue
        int visitedNodesCount = 1; // visited and tested as solution
        Set<E> queuedStates = new HashSet<>();

        Node<E> node = strategy.nodeFromInitialState(problem.getInitialState());
        strategy.offer(node);

        while ((node = strategy.getNextNode()) != null && !problem.isResolved(node.getState())) {

            dispatchNodeToSpies(node);

            List<Node<E>> children = strategy.explodeChildren(node, problem.getRules(node.getState()))
                .filter(child -> !queuedStates.contains(child.getState()))
                //.filter(child -> !nodeHasALoop(child))
                .collect(Collectors.toList());
            strategy.offerAll(children);
            children.forEach(n -> queuedStates.add(n.getState()));
            enqueuedNodesCount += children.size();
            node.setVisitedNodes(++visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
            for (Node<E> n : children) {
                if (problem.isResolved(n.getState())) {
                    return n.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(visitedNodesCount);
                }
            }
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
