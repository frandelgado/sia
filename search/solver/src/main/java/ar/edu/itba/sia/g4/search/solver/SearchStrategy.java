package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Rule;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public interface SearchStrategy<E> {
    default Node<E> nodeFromInitialState(E state) {
        return new Node<>(state, 1, 1, 1, 0, 0,null);
    }

    Node<E> getNextNode();

    Node<E> offer(Node<E> node);

    default int offerAll(List<Node<E>> nodes) {
        nodes.forEach(this::offer);
        return nodes.size();
    }

    default Stream<Node<E>> explodeChildren(Node<E> parent, List<Rule<E>> rules) {
        E currentState = parent.getState();
        return rules.parallelStream()
         .map(rule -> {
             E state = rule.applyToState(currentState);
             double newCost = parent.getCost() + rule.getCost();
             return new Node<>(state, -1, -1, parent.getDepth() + 1, newCost, -1, parent);
         });
    }

    default boolean isIterativeDeepening() {
        return false;
    }

}
