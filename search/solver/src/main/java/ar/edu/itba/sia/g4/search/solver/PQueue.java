package ar.edu.itba.sia.g4.search.solver;

import java.util.PriorityQueue;

public class PQueue<Node> implements GenericList<Node> {

    private PriorityQueue<Node> pqueue;

    public PQueue(){
        pqueue = new PriorityQueue<>();
    }
    @Override
    public void add(Node node) {
        pqueue.add(node);
    }
    @Override
    public Node poll() {
        return pqueue.poll();
    }
}
