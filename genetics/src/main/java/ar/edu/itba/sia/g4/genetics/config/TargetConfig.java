package ar.edu.itba.sia.g4.genetics.config;

public class TargetConfig {
    private String type;
    private int iterations;
    private double delta;
    private int seconds;

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

    public int getSeconds() {
        return seconds;
    }

    public TargetConfig setSeconds(int seconds) {
        this.seconds = seconds;
        return this;
    }
}
