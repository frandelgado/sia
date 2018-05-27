package ar.edu.itba.sia.g4.genetics.dnd.selectors;

import ar.edu.itba.sia.g4.genetics.problem.Species;

class SelectorPair<T extends Species> {
    private final T thing;
    private final double fitness;

    public SelectorPair(T thing, double fitness) {
        this.thing = thing;
        this.fitness = fitness;
    }

    public T getThing() {
        return thing;
    }

    public double getFitness() {
        return fitness;
    }
}
