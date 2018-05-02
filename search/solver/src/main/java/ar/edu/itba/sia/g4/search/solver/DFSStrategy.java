package ar.edu.itba.sia.g4.search.solver;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DFSStrategy<E> implements SearchStrategy<E> {
    private final Deque<Node<E>> stack = new ArrayDeque<>();

    @Override
    public Node<E> getNextNode() {
        return stack.poll();
    }

    @Override
    public Node<E> offer(@NotNull Node<E> node) {
        stack.push(node);
        return node;
    }

    @Override
    public int offerAll(List<Node<E>> nodes) {
        Lists.reverse(nodes).forEach(this::offer);
        return nodes.size();
    }
}
