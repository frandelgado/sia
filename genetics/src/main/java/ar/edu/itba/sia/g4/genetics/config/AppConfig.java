package ar.edu.itba.sia.g4.genetics.config;

import java.io.Serializable;

public class AppConfig implements Serializable {

    private double generationalGap;
    private int populationSize;
    private String replacementAlgorithm;
    private Items items;
    private String profession;
    private MutatorConfig mutator;
    private SelectorsConfig selector;
    private SelectorsConfig replacer;
    private TargetConfig target;
    private CrosserConfig crosser;


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

    public String getProfession() {
        return profession;
    }

    public AppConfig setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public MutatorConfig getMutator() {
        return mutator;
    }

    public AppConfig setMutator(MutatorConfig mutator) {
        this.mutator = mutator;
        return this;
    }

    public SelectorsConfig getSelector() {
        return selector;
    }

    public AppConfig setSelector(SelectorsConfig selector) {
        this.selector = selector;
        return this;
    }

    public SelectorsConfig getReplacer() {
        return replacer;
    }

    public AppConfig setReplacer(SelectorsConfig replacer) {
        this.replacer = replacer;
        return this;
    }

    public TargetConfig getTarget() {
        return target;
    }

    public AppConfig setTarget(TargetConfig target) {
        this.target = target;
        return this;
    }

    public CrosserConfig getCrosser() {
        return crosser;
    }

    public AppConfig setCrosser(CrosserConfig crosser) {
        this.crosser = crosser;
        return this;
    }
}
