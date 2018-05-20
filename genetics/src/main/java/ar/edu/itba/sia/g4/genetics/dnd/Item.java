package ar.edu.itba.sia.g4.genetics.dnd;

public class Item {
    private final double str;
    private final double agi;
    private final double exp;
    private final double res;
    private final double hp;

    public Item(double str, double agi, double exp, double res, double hp) {
        this.str = str;
        this.agi = agi;
        this.exp = exp;
        this.res = res;
        this.hp = hp;
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

    @Override
    public String toString() {
        return String.format("[Str:%.2f Agi:%.2f Exp:%.2f Res:%.2f HP:%.2f]",
         str, agi, exp, res, hp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (Double.compare(item.str, str) != 0) {
            return false;
        }
        if (Double.compare(item.agi, agi) != 0) {
            return false;
        }
        if (Double.compare(item.exp, exp) != 0) {
            return false;
        }
        if (Double.compare(item.res, res) != 0) {
            return false;
        }
        return Double.compare(item.hp, hp) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(str);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(agi);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(exp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(res);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
