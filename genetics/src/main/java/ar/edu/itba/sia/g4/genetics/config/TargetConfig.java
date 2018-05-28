package ar.edu.itba.sia.g4.genetics.config;

public class TargetConfig {
    private String type;
    private int iterations;
    private double delta;

    public int getIterations() {
        return iterations;
    }

    public TargetConfig setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public String getType() {
        return type;
    }

    public TargetConfig setType(String type) {
        this.type = type;
        return this;
    }

    public double getDelta() {
        return delta;
    }

    public TargetConfig setDelta(double delta) {
        this.delta = delta;
        return this;
    }
}
