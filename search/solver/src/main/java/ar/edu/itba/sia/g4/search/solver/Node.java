package ar.edu.itba.sia.g4.search.solver;

public class Node<E> implements Comparable {
    private Node<E> parent;
    private E state;
    private double cost;
    private double heuristicCost;
    private int expandedNodes;

    public Node(E state, int expandedNodes, double cost, double heuristicCost, Node parent){
        this.state = state;
        this.parent = parent;
        this.expandedNodes = expandedNodes;
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
        return state;
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

    public int getExpandedNodes() {
        return expandedNodes;
    }
}
