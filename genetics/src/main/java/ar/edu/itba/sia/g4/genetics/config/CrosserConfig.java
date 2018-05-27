package ar.edu.itba.sia.g4.genetics.config;

public class CrosserConfig {
    private String type;
    private double chance;

    public String getType() {
        return type;
    }

    public CrosserConfig setType(String type) {
        this.type = type;
        return this;
    }

    public double getChance() {
        return chance;
    }

    public CrosserConfig setChance(double chance) {
        this.chance = chance;
        return this;
    }
}
