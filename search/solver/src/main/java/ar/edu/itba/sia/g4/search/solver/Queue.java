package ar.edu.itba.sia.g4.search.solver;

import java.util.LinkedList;

public class Queue<T extends Node<?>> implements GenericList<T> {

    private java.util.Queue<T> queue;

    public Queue(){
        queue = new LinkedList<>();
    }

    @Override
    public void add(T node) {
        queue.add(node);
    }

    @Override
    public T poll() {
        return queue.poll();
    }
}
