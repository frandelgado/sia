package ar.edu.itba.sia.g4.search.solver;

import java.util.LinkedList;

public class Stack<Node>  implements GenericList<Node>{

    private LinkedList<Node> stack;

    public Stack(){
        stack = new LinkedList<>();
    }

    @Override
    public void add(Node node) {
        stack.push(node);
    }

    @Override
    public Node poll() {
        return stack.pop();
    }
}
