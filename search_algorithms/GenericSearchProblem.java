import java.util.ArrayList;

public interface GenericSearchProblem {
    ArrayList<Action> getNextPossibleStep(State state);
    State nextState(State state, Action action);
    State initialState();
    double StepCost(State state, Action action, State results);
    boolean isFinal(State state);
}
