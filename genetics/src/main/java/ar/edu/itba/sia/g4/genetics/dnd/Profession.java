package ar.edu.itba.sia.g4.genetics.dnd;

public enum Profession {
    WARRIOR1    (0.6, 1.1, 0.9, 0.8, 1.0, 0.9),
    WARRIOR2    (0.6, 1.2, 1.0, 0.8, 0.8, 0.8),
    WARRIOR3    (0.6, 0.8, 0.9, 0.9, 1.2, 1.1),
    ARCHER1     (0.9, 0.8, 1.1, 1.1, 0.9, 0.7),
    ARCHER2     (0.9, 0.9, 1.1, 1.0, 0.9, 0.8),
    ARCHER3     (0.9, 0.8, 0.8, 0.8, 1.1, 1.2),
    ASSASSIN1   (0.7, 0.8, 1.2, 1.1, 1.0, 0.8),
    ASSASSIN2   (0.7, 0.9, 1.0, 1.1, 1.0, 0.9),
    ASSASSIN3   (0.7, 0.9, 0.9, 1.0, 1.1, 1.0),
    DEFENDER1   (0.1, 1.0, 0.9, 0.7, 1.2, 1.1),
    DEFENDER2   (0.1, 1.1, 0.8, 0.8, 1.1, 1.1),
    DEFENDER3   (0.1, 0.9, 0.9, 0.9, 1.0, 1.3);

    private final double bias;
    private final double str;
    private final double agi;
    private final double exp;
    private final double res;
    private final double hp;


    Profession(double bias, double str, double agi, double exp, double res, double hp) {
        this.bias = bias;
        this.str = str;
        this.agi = agi;
        this.exp = exp;
        this.res = res;
        this.hp = hp;
    }

    public double getBias() {
        return bias;
    }

    public double getStr() {
        return str;
    }

    public double getAgi() {
        return agi;
    }

    public double getExp() {
        return exp;
    }

    public double getRes() {
        return res;
    }

    public double getHp() {
        return hp;
    }
}
