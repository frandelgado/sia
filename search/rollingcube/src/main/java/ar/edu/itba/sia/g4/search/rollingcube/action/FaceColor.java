package ar.edu.itba.sia.g4.search.rollingcube.action;

public enum FaceColor {
    // Zones:
    // NW NE
    // SW SE
    // white = true, black = false

    ALL_WHITE(true, true, true, true),
    ALL_BLACK(false, false, false, false),
    N_BLACK_S_WHITE(false, false, true, true),
    N_WHITE_S_BLACK(true, true, false, false),
    W_BLACK_E_WHITE(false, true, false, true),
    W_WHITE_E_BLACK(true, false, true, false);

    private final boolean nw;
    private final boolean ne;
    private final boolean sw;
    private final boolean se;

    FaceColor(boolean nw, boolean ne, boolean sw, boolean se) {
        this.nw = nw;
        this.ne = ne;
        this.sw = sw;
        this.se = se;
    }

    public FaceColor roll(final RollDirection dir) {
        switch (this) {
        case ALL_BLACK:
            return allBlackRoll(dir);
        case ALL_WHITE:
            return allWhiteRoll(dir);
        case W_WHITE_E_BLACK:
            return westWhiteRoll(dir);
        case W_BLACK_E_WHITE:
            return eastWhiteRoll(dir);
        case N_BLACK_S_WHITE:
            return southWhiteRoll(dir);
        case N_WHITE_S_BLACK:
            return northWhiteRoll(dir);
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor allBlackRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return N_BLACK_S_WHITE;
        case SOUTH:
            return N_WHITE_S_BLACK;
        case EAST:
            return W_WHITE_E_BLACK;
        case WEST:
            return W_BLACK_E_WHITE;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor allWhiteRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return N_WHITE_S_BLACK;
        case SOUTH:
            return N_BLACK_S_WHITE;
        case EAST:
            return W_BLACK_E_WHITE;
        case WEST:
            return W_WHITE_E_BLACK;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor westWhiteRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return this;
        case SOUTH:
            return this;
        case EAST:
            return ALL_WHITE;
        case WEST:
            return ALL_BLACK;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor eastWhiteRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return this;
        case SOUTH:
            return this;
        case EAST:
            return ALL_BLACK;
        case WEST:
            return ALL_WHITE;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor southWhiteRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return ALL_WHITE;
        case SOUTH:
            return ALL_BLACK;
        case EAST:
            return this;
        case WEST:
            return this;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    private FaceColor northWhiteRoll(final RollDirection dir){
        switch (dir){
        case NORTH:
            return ALL_BLACK;
        case SOUTH:
            return ALL_WHITE;
        case EAST:
            return this;
        case WEST:
            return this;
        default:
            throw new IllegalArgumentException("Can't roll that way");
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ALL_BLACK:
                return "AB";
            case ALL_WHITE:
                return "AW";
            case W_WHITE_E_BLACK:
                return "WWEB";
            case W_BLACK_E_WHITE:
                return "WBEW";
            case N_BLACK_S_WHITE:
                return "NBSW";
            case N_WHITE_S_BLACK:
                return "NWSB";
            default:
                throw new IllegalArgumentException("Can't roll that way");
        }
    }
}
