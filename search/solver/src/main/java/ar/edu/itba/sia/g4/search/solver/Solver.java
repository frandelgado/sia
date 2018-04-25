package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.g4.search.rollingcube.game.RollingCubeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solver<E> {

    Problem<E> problem;
    Heuristic<E> heuristic;

    public Solver(Problem<E> problem, Heuristic<E> heuristic){
        this.problem = problem;
        this.heuristic = heuristic;
    }

    public Node<E> solve(){
        PriorityQueue<Node> pqueue = new PriorityQueue<>();
        LinkedList<Node<E>> fronteerNodes;
        int expandedNodesCount = 0;
        E state = problem.getInitialState();
        Node<E> lastExpandedNode = new Node<>(state,0, 0, heuristic.getValue(state),null);

        while(!problem.isResolved(lastExpandedNode.getState())){
            fronteerNodes = generateFronteerStates(lastExpandedNode, expandedNodesCount);
            for(Node<E> n : fronteerNodes){
                pqueue.add(n);
                expandedNodesCount++;
            }
            lastExpandedNode = pqueue.poll();
        }

        return lastExpandedNode;
    }

    private LinkedList<Node<E>> generateFronteerStates(Node<E> node, int expandedNodesCount){
        List<Rule<E>> rules = problem.getRules(node.getState());
        LinkedList<Node<E>> fronteerNodes = new LinkedList<>();
        for(Rule<E> r : rules){
            E state = r.applyToState(node.getState());
            Node<E> newNode = new Node<>(state, expandedNodesCount,node.getCost() + r.getCost(), heuristic.getValue(state), node);
            fronteerNodes.add(newNode);
        }
        return fronteerNodes;
    }
}
