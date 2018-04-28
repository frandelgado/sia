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
    ListFactory listFactory;

    public Solver(Problem<E> problem, Heuristic<E> heuristic){
        this.problem = problem;
        this.heuristic = heuristic;
        this.listFactory = new ListFactory();
    }

    public Node<E> solve(int searchType){
        GenericList<Node> queue = listFactory.getList(searchType);
        LinkedList<Node<E>> fronteerNodes;
        int expandedNodesCount = 1;
        int vistedNodesCount = 1;
        E state = problem.getInitialState();
        double heuristicCost;

        if(heuristic != null)
            heuristicCost = heuristic.getValue(state);
        else
            heuristicCost = 0;

        Node<E> lastExpandedNode = new Node<>(state,expandedNodesCount,vistedNodesCount, 0, heuristic.getValue(state),null);

        while(!problem.isResolved(lastExpandedNode.getState())){
            fronteerNodes = generateFronteerStates(lastExpandedNode, expandedNodesCount, vistedNodesCount);
            for(Node<E> n : fronteerNodes){
                queue.add(n);
                expandedNodesCount++;
            }
            lastExpandedNode = queue.poll();
            vistedNodesCount++;
        }

        return lastExpandedNode;
    }

    private LinkedList<Node<E>> generateFronteerStates(Node<E> node, int expandedNodesCount, int visitedNodesCount){
        List<Rule<E>> rules = problem.getRules(node.getState());
        LinkedList<Node<E>> fronteerNodes = new LinkedList<>();
        for(Rule<E> r : rules){
            E state = r.applyToState(node.getState());
            Node<E> newNode = new Node<>(state, expandedNodesCount, visitedNodesCount,node.getCost() + r.getCost(), heuristic.getValue(state), node);
            fronteerNodes.add(newNode);
        }
        return fronteerNodes;
    }
}
