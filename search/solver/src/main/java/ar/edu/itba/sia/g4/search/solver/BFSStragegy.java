package ar.edu.itba.sia.g4.search.solver;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class BFSStragegy<E> implements SearchStrategy<E> {
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
}
