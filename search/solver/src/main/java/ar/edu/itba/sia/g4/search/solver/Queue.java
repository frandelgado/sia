package ar.edu.itba.sia.g4.search.solver;

import java.util.LinkedList;

public class Queue<Node> implements GenericList<Node> {

    private LinkedList<Node> queue;

    public Queue(){
        queue = new LinkedList<>();
    }

    @Override
    public void add(Node node) {
        queue.add(node);
    }

    @Override
    public Node poll() {
        return queue.poll();
    }
}
