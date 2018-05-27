package ar.edu.itba.sia.g4.genetics.config;

public class SelectorsConfig {
    private double mix;
    private SelectorConfig first;
    private SelectorConfig last;

    public double getMix() {
        return mix;
    }

    public SelectorsConfig setMix(double mix) {
        this.mix = mix;
        return this;
    }

    public SelectorConfig getFirst() {
        return first;
    }

    public SelectorsConfig setFirst(SelectorConfig first) {
        this.first = first;
        return this;
    }

    public SelectorConfig getLast() {
        return last;
    }

    public SelectorsConfig setLast(SelectorConfig last) {
        this.last = last;
        return this;
    }
}
