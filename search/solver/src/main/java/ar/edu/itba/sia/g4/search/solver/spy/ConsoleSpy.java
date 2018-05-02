package ar.edu.itba.sia.g4.search.solver.spy;

import ar.edu.itba.sia.g4.search.solver.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConsoleSpy<E> implements Spy<E> {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleSpy.class);
    int maxDepthReached = 0;


    @Override
    public void onVisitedNode(Node<E> node) {
        logger.debug("Visited node {} with state {}", node, node.getState());
        if (maxDepthReached < node.getDepth()) {
            maxDepthReached = node.getDepth();
            logger.info("Reached max depth {}", maxDepthReached);
        }
    }

    @Override
    public void onQueuedChildren(List<Node<E>> list) {
        logger.debug("Added {} children", list.size());
    }

    @Override
    public void onSolution(Node<E> node) {
        logger.info("Found solution for problem with state {}", node.getState());
        logger.info("Retracing path");
        int x = 0;
        for (Node n = node; n != null; n = n.getParent()) {
            x++;
        }
        logger.info("in: {} steps", x);
    }

    @Override
    public void onSolutionMissing(int maxDepthReached, int visitedNodesCount, int enqueuedNodesCount) {
        logger.info("Could not find a solution for the problem. Searched max depth {}, visited {} nodes and queued {} nodes.",
         maxDepthReached, visitedNodesCount, enqueuedNodesCount);
    }

    @Override
    public void onDepth(int depth) {
        logger.info("Trying with depth {}", depth);
    }
}
