package ar.edu.itba.sia.g4.search.rollingcube.heuristic;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.g4.search.rollingcube.game.Board;

public class NilHeuristic implements Heuristic<Board> {
    @Override
    public double getValue(Board rollingCubeState) {
        return 0;
    }
}
