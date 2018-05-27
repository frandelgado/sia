package ar.edu.itba.sia.g4.genetics.config;

public class SelectorConfig {
    private String type;
    private boolean useBoltzmann;


    public String getType() {
        return type;
    }

    public SelectorConfig setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isUseBoltzmann() {
        return useBoltzmann;
    }

    public SelectorConfig setUseBoltzmann(boolean useBoltzmann) {
        this.useBoltzmann = useBoltzmann;
        return this;
    }
}
