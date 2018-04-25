package ar.edu.itba.sia.g4.search.solver;


import java.util.LinkedList;
import java.util.PriorityQueue;
import

/**
 * Hello world!
 *
 */
public class App<E>
{
    public static void main( String[] args )
    {

    }

    private class Result<E>{
        private Node<E> lastNode;
        private int expandedNodesCount;

        public Result(Node lastNode, int expandedNodesCount){
            this.lastNode = lastNode;
            this.expandedNodesCount = expandedNodesCount;
        }

        public Node<E> getLastNode() {
            return lastNode;
        }

        public int getExpandedNodesCount() {
            return expandedNodesCount;
        }
    }

    public Result<E> solve(){
        PriorityQueue<Node> pqueue = new PriorityQueue<>();
        LinkedList<Node<E>> fronteerNodes;
        int expandedNodesCount = 0;

        Problem<E> problem = new RollingCubeGame();
        Node<E> lastExpandedNode = new Node(problem.getInitialState(), null);

        while(!problem.isresolved(state)){
            fronteerNodes = generateFronteerStates(lastExpandedNode);
            for(Node<E> n : fronteerNodes){
                pqueue.add(n);
                expandedNodesCount++;
            }
            lastExpandedNode = pqueue.poll();
        }

        return new Result<>(lastExpandedNode,expandedNodesCount);
    }

    private LinkedList<Node<E>> generateFronteerStates(Node node){
        return null;
    }

}
