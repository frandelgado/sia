package ar.edu.itba.sia.g4.search.solver;

import ar.edu.itba.sia.g4.search.solver.spy.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConsoleSpy<E> implements Spy<E> {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleSpy.class);

    @Override
    public void onVisitedNode(Node<E> node) {
        logger.debug("Visited node {} with state {}", node, node.getState());
    }

    @Override
    public void onQueuedChildren(List<Node<E>> list) {
        logger.debug("Added {} children", list.size());
    }

    @Override
    public void onSolution(Node<E> node) {
        logger.info("Found solution for problem with state {}", node.getState());
        logger.info("Retracing path");
        for (Node n = node; n != null; n = n.getParent()) {
            logger.info("State: {}", n.getState());
        }
    }

    @Override
    public void onSolutionMissing(int maxDepthReached, int visitedNodesCount, int enqueuedNodesCount) {
        logger.info("Could not find a solution for the problem. Searched max depth {}, visited {} nodes and queued {} nodes.",
         maxDepthReached, visitedNodesCount, enqueuedNodesCount);
    }
}
