package ar.edu.itba.sia.g4.genetics.problem;

public class Couple<T extends Species> {
    private final T head;
    private final T tail;

    public Couple(T head, T tail) {
        this.head = head;
        this.tail = tail;
    }

    public T getHead() {
        return head;
    }

    public T getTail() {
        return tail;
    }
}
