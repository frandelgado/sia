package ar.edu.itba.sia.g4.genetics.config;

import java.io.Serializable;
import java.util.Map;

public class AppConfig implements Serializable {

    private double generationalGap;
    private int populationSize;
    private String replacementAlgorithm;
    private Items items;


    public double getGenerationalGap() {
        return generationalGap;
    }

    public AppConfig setGenerationalGap(double generationalGap) {
        this.generationalGap = generationalGap;
        return this;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public AppConfig setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public String getReplacementAlgorithm() {
        return replacementAlgorithm;
    }

    public AppConfig setReplacementAlgorithm(String replacementAlgorithm) {
        this.replacementAlgorithm = replacementAlgorithm;
        return this;
    }

    public Items getItems() {
        return items;
    }

    public AppConfig setItems(Items items) {
        this.items = items;
        return this;
    }
}
