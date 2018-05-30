package ar.edu.itba.sia.g4.genetics.config;

public class MutatorConfig {
    private String type;
    private double chance;
    public double lambda;
    public boolean uniform;


    public String getType() {
        return type;
    }

    public MutatorConfig setType(String type) {
        this.type = type;
        return this;
    }

    public double getChance() {
        return chance;
    }

    public boolean uniformProbFunc(){
        return uniform;
    }

    public double getLambda(){
        return lambda;
    }

    public MutatorConfig setChance(double chance) {
        this.chance = chance;
        return this;
    }

    public MutatorConfig setLambda(double lambda) {
        this.lambda = lambda;
        return this;
    }

    public boolean isUniform() {
        return uniform;
    }

    public MutatorConfig setUniform(boolean uniform) {
        this.uniform = uniform;
        return this;
    }
}
