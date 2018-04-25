package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RollingCubeGame implements Problem<RollingCubeState> {
    @Override
    public RollingCubeState getInitialState() {
        return null;
    }

    @NotNull
    @Override
    public List<Rule<RollingCubeState>> getRules(RollingCubeState rollingCubeState) {
        return null;
    }

    @Override
    public boolean isResolved(RollingCubeState rollingCubeState) {
        return false;
    }

    public RollingCubeGame(){

    }
}
