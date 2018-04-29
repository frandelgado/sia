package ar.edu.itba.sia.g4.search.solver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Stack<T extends Node<?>>  implements GenericList<T>{

    private LinkedList<T> stack;

    public Stack(){
        stack = new LinkedList<>();
    }

    @Override
    public void add(T node) {
        stack.push(node);
    }

    @Override
    public void addAll(List<T> nodes) {
        // we add in reverse order :)
        for (int idx = nodes.size() - 1; idx >= 0; idx--) {
            add(nodes.get(idx));
        }
    }

    @Override
    public T poll() {
        return stack.pop();
    }
}
