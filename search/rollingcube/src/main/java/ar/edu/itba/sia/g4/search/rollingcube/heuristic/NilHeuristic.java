package ar.edu.itba.sia.g4.search.rollingcube.heuristic;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.g4.search.rollingcube.game.RollingCubeState;

public class NilHeuristic implements Heuristic<RollingCubeState> {
    @Override
    public double getValue(RollingCubeState rollingCubeState) {
        return 0;
    }
}
