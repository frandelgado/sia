package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AStarStrategy<E> implements SearchStrategy<E> {
    private final PriorityQueue<Node> pqueue = new PriorityQueue<>();
    private final Heuristic<E> heuristic;

    public AStarStrategy(Heuristic<E> heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public Node<E> getNextNode() {
        return pqueue.poll();
    }

    @Override
    public Node<E> offer(@NotNull Node<E> node) {
        pqueue.offer(node);
        return node;
    }

    @Override
    public Stream<Node<E>> explodeChildren(@NotNull Node<E> parent, @NotNull List<Rule<E>> rules) {
        E currentState = parent.getState();
        return rules.parallelStream()
         .map(rule -> {
             E state = rule.applyToState(currentState);
             double newCost = parent.getCost() + rule.getCost();
             double heuristicCost = heuristic.getValue(state);
             return new Node<>(state, -1, -1, parent.getDepth() + 1, newCost, heuristicCost, parent);
         });
    }
}
