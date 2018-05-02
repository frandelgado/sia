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
                listeners.forEach(spy -> onSolutionMissing(maxDepthReached, visitedNodesCount, enqueuedNodesCount));
            }
        };
    }

    public void subscribe(@NotNull Spy<E> spy) {
        spies.add(spy);
    }


    public Node<E> solve(){
        int enqueuedNodesCount = 1; // added to the queue
        int visitedNodesCount = 1; // visited and tested as solution
        int maxDepthReached = 0;
        Set<E> queuedStates = new HashSet<>();

        Node<E> node = strategy.nodeFromInitialState(problem.getInitialState());
        queuedStates.add(node.getState());

        if(strategy instanceof IDDFSStrategy){
            node = ((IDDFSStrategy) strategy).doIddfs(this, problem, queuedStates, node);
        }else{
            strategy.offer(node);
            while ((node = strategy.getNextNode()) != null && !problem.isResolved(node.getState())) {
                broadcast.onVisitedNode(node);

                maxDepthReached = Math.max(maxDepthReached, node.getDepth());

                List<Node<E>> children = strategy.explodeChildren(node, problem.getRules(node.getState()))
                 .filter(child -> !queuedStates.contains(child.getState()))
                 .collect(Collectors.toList());
                strategy.offerAll(children);
                broadcast.onQueuedChildren(children);
                children.forEach(n -> queuedStates.add(n.getState()));
                enqueuedNodesCount += children.size();
                node.setVisitedNodes(++visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
                for (Node<E> n : children) {
                    if (problem.isResolved(n.getState())) {
                        n.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(visitedNodesCount);
                        broadcast.onSolution(n);
                        return n;
                    }
                }
            }
        }

        if (node == null) {
            broadcast.onSolutionMissing(maxDepthReached, visitedNodesCount, enqueuedNodesCount);
            return null;
        }

        node.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(visitedNodesCount);
        broadcast.onSolution(node);
        return node;

    }

}
