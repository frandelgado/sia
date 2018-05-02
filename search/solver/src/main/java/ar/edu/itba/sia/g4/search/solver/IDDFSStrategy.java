package ar.edu.itba.sia.g4.search.solver;

public class IDDFSStrategy<E> extends DFSStrategy<E> {
    @Override
    public boolean isIterative() {
        return true;
    }
}