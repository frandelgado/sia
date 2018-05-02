package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IDDFSStrategy<E> implements SearchStrategy<E> {
    private final Deque<Node<E>> queue = new LinkedList<>();

    @Override
    public Node<E> getNextNode() {
        return queue.poll();
    }

    @Override
    public Node<E> offer(@NotNull Node<E> node) {
        queue.add(node);
        return node;
    }

    public Node<E> doIddfs(Solver<E> solver, Problem<E> problem, Set<E> queuedStates, Node<E> initialNode){
        int enqueuedNodesCount = 1;
        int visitedNodesCount = 1;
        for(int depth = 0; depth < Integer.MAX_VALUE; depth++){
            Node<E> found = doDfs(solver, problem, queuedStates, initialNode, depth, enqueuedNodesCount, visitedNodesCount);
            if(found != null){
                found.setQueuedNodes(visitedNodesCount).setVisitedNodes(enqueuedNodesCount);
                return found;
            }
        }
        return null;
    }

    private Node<E> doDfs(Solver<E> solver, Problem<E> problem, Set<E> queuedStates, Node<E> node, int depth, int enqueuedNodesCount, int visitedNodesCount){
        solver.dispatchNodeToSpies(node);
        if(depth == 0 && problem.isResolved(node.getState())){
            return node;
        }
        if(depth > 0){
            List children = this.explodeChildren(node, problem.getRules(node.getState()))
                    .filter(child -> !queuedStates.contains(child))
                    .collect(Collectors.toList());
            queuedStates.addAll(children);
            enqueuedNodesCount += children.size();
            node.setVisitedNodes(++visitedNodesCount).setQueuedNodes(enqueuedNodesCount);
            for(Node<E> child : (List<Node<E>>) children){
                Node<E> found = this.doDfs(solver, problem, queuedStates, child, depth - 1, enqueuedNodesCount, visitedNodesCount);
                if(found != null){
                    return found;
                }
            }
        }
        return null;
    }

    @Override
    public Stream<Node<E>> explodeChildren(@NotNull Node<E> parent, @NotNull List<Rule<E>> rules) {
        E currentState = parent.getState();
        return rules.parallelStream()
                .map(rule -> {
                    E state = rule.applyToState(currentState);
                    return new Node<>(state, -1, -1, parent.getDepth() + 1, 0, 0, parent);
                });
    }
}