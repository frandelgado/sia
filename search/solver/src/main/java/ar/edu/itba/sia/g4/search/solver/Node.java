package ar.edu.itba.sia.g4.search.solver;

public class Node<E> implements Comparable {
    private final Node<E> parent;
    private final E state;
    private final double cost;
    private final double heuristicCost;
    private int visitedNodes;
    private int queuedNodes;

    public Node(E state, int queuedNodes, int visitedNodes, double cost, double heuristicCost, Node parent){
        this.state = state;
        this.parent = parent;
        this.visitedNodes = visitedNodes;
        this.queuedNodes = queuedNodes;
        this.cost = cost;
        this.heuristicCost = heuristicCost;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Node){
            Node other = (Node)o;
            return (int) Math.signum(heuristicCost + cost - (other.heuristicCost + other.cost));
        }
        else
            return 1;

    }

    public E getState() {
        return this.state;
    }

    public Node<E> getParent() {
        return parent;
    }

    public double getCost() {
        return cost;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    public int getQueuedNodes() {
        return queuedNodes;
    }

    public Node<E> setVisitedNodes(int visitedNodes) {
        this.visitedNodes = visitedNodes;
        return this;
    }

    public Node<E> setQueuedNodes(int queuedNodes) {
        this.queuedNodes = queuedNodes;
        return this;
    }
}
