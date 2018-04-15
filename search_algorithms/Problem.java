import java.util.ArrayList;

public class Problem implements GenericSearchProblem {

    @Override
    public ArrayList<Action> getNextPossibleStep(State state) {
        return null;
    }

    @Override
    public State nextState(State state, Action action) {
        return state.applyAction(action);
    }

    @Override
    public State initialState() {
        return new State();
    }

    @Override
    public double StepCost(State state, Action action, State results) {
        return 0;
    }

    @Override
    public boolean isFinal(State state) {
        for(int i=0; i<9; i++){
            if(state.board[i] != State.CubeStates.WHITE || state.board[i] != State.CubeStates.EMPTY)
                return false;
        }
        return true;
    }
}
