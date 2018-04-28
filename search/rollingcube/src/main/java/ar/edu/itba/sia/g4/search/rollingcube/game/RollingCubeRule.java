package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.com.itba.sia.Rule;

public class RollingCubeRule implements Rule<Board> {

    private Board board;

    public RollingCubeRule(Board board){
        this.board = board;
    }

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public void setCost(double v) {

    }

    @Override
    public Board applyToState(Board board) {
        return null;
    }

}
