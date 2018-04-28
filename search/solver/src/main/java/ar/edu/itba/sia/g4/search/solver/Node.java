package ar.edu.itba.sia.g4.search.solver;

public class Node<E> implements Comparable {
    private final Node<E> parent;
    private final E state;
    private final double cost;
    private final double heuristicCost;
    private final int expandedNodes;
    private final int visitedNodes;

    public Node(E state, int expandedNodes,int visitedNodes, double cost, double heuristicCost, Node parent){
        this.state = state;
        this.parent = parent;
        this.expandedNodes = expandedNodes;
        this.visitedNodes = visitedNodes;
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

    public int getVisitedNodes() {
        return visitedNodes;
    }
}
