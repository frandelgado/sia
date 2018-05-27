package ar.edu.itba.sia.g4.genetics.config;

public class MutatorConfig {
    private String type;
    private double chance;

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

    public MutatorConfig setChance(double chance) {
        this.chance = chance;
        return this;
    }
}
