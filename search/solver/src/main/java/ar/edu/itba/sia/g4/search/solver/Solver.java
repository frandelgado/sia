package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.g4.search.solver.spy.Spy;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver<E> {

    private final Problem<E> problem;
    private final SearchStrategy<E> strategy;
    private final List<Spy<E>> spies = new LinkedList<>();
    private final Spy<E> broadcast;

    public Solver(@NotNull Problem<E> problem, @NotNull SearchStrategy<E> strategy){
        this.problem = problem;
        this.strategy = strategy;
        this.broadcast = setupSpy(spies);
    }

    private static <E> Spy<E> setupSpy(List<Spy<E>> listeners) {
        return new Spy<E>() {
            @Override
            public void onVisitedNode(Node<E> node) {
                listeners.forEach(spy -> spy.onVisitedNode(node));
            }

            @Override
            public void onQueuedChildren(List<Node<E>> nodes) {
                listeners.forEach(spy -> spy.onQueuedChildren(nodes));
            }

            @Override
            public void onSolution(Node<E> node) {
                listeners.forEach(spy -> spy.onSolution(node));
            }

            @Override
            public void onSolutionMissing(int maxDepthReached, int visitedNodesCount, int enqueuedNodesCount) {
                listeners.forEach(spy -> spy.onSolutionMissing(maxDepthReached, visitedNodesCount, enqueuedNodesCount));
            }

            @Override
            public void onDepth(int depth) {
                listeners.forEach(spy -> spy.onDepth(depth));
            }
        };
    }

    public void subscribe(@NotNull Spy<E> spy) {
        spies.add(spy);
    }


    private Node<E> solveForDepth(int currentDepthLimit) {
        int enqueuedNodesCount = 1; // added to the queue
        int visitedNodesCount = 1; // visited and tested as solution
        Set<E> queuedStates = new HashSet<>();

        strategy.reset();
        Node<E> node = strategy.nodeFromInitialState(problem.getInitialState());
        broadcast.onDepth(currentDepthLimit);
        strategy.offer(node);
        queuedStates.add(node.getState());

        while ((node = strategy.getNextNode()) != null && !problem.isResolved(node.getState())) {
            broadcast.onVisitedNode(node);
            ++visitedNodesCount;

            if (node.getDepth() >= currentDepthLimit) {
                node = null;
                continue;
            }

            List<Node<E>> children = strategy.explodeNode(node, problem.getRules(node.getState()))
                 .filter(child -> !queuedStates.contains(child.getState()))
                 .collect(Collectors.toList());
            strategy.offerAll(children);
            broadcast.onQueuedChildren(children);
            children.forEach(n -> queuedStates.add(n.getState()));
            enqueuedNodesCount += children.size();

            for (Node<E> n : children) {
                if (problem.isResolved(n.getState())) {
                    n.setVisitedNodes(visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
                    return n;
                }
            }
        }
        if (node != null) {
            node.setVisitedNodes(visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
        }
        return node;
    }


    public Node<E> solve() {
        int currentDepthLimit = strategy.isIterative() ? 1 : Integer.MAX_VALUE;

        Node<E> node;

        do {
            node = solveForDepth(currentDepthLimit);
            if (node != null && problem.isResolved(node.getState())) {
                break;
            }
            currentDepthLimit++;
        } while (strategy.isIterative() && node == null);

        broadcast.onSolution(node);
        return node;

    }

}
