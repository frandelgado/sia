package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solver<E> {

    Problem<E> problem;
    Heuristic<E> heuristic;
    ListFactory listFactory;
    HashSet<Integer> visitedStates;

    public Solver(@NotNull Problem<E> problem, @NotNull Heuristic<E> heuristic){
        this.problem = problem;
        this.heuristic = heuristic;
        this.listFactory = new ListFactory();
        this.visitedStates = new HashSet<>();
    }

    private Node<E> nodeFromInitialState(E state) {
        double heuristicCost = heuristic == null ? 0 : heuristic.getValue(state);
        return new Node<>(state, 1, 1, 0, heuristicCost,null);
    }

    public Node<E> solve(int searchType){
        GenericList<Node<E>> queue = listFactory.getList(searchType);

        int enqueuedNodesCount = 1; // added to the queue
        int vistedNodesCount = 1; // visited and tested as solution
        Node<E> lastExpandedNode = nodeFromInitialState(problem.getInitialState());

        while(lastExpandedNode != null && !problem.isResolved(lastExpandedNode.getState())){
            List<Node<E>> frontierNodes = generateFrontierStates(lastExpandedNode);
            queue.addAll(frontierNodes);
            enqueuedNodesCount += frontierNodes.size();
            vistedNodesCount++;
            this.visitedStates.add(lastExpandedNode.getState().hashCode());
            lastExpandedNode.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(vistedNodesCount);
            lastExpandedNode = queue.poll();
            while(lastExpandedNode != null && this.visitedStates.contains(lastExpandedNode.getState().hashCode())){
                lastExpandedNode = queue.poll();
            }
            //System.out.println(this.visitedStates.size());
        }
        if(lastExpandedNode == null){
            return null;
        }
        return lastExpandedNode.setQueuedNodes(enqueuedNodesCount).setVisitedNodes(vistedNodesCount);
    }

    private LinkedList<Node<E>> generateFrontierStates(@NotNull Node<E> node){
        List<Rule<E>> rules = problem.getRules(node.getState());
        LinkedList<Node<E>> frontierNodes = new LinkedList<>();
        for(Rule<E> r : rules){
            E state = r.applyToState(node.getState());
            double newCost = node.getCost() + r.getCost();
            double heuristicCost = heuristic == null ? 0 : heuristic.getValue(state);
            Node<E> newNode = new Node<>(state, -1, -1, newCost, heuristicCost, node);
            frontierNodes.add(newNode);
        }
        return frontierNodes;
    }
}
