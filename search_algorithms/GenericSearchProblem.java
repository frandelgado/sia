import java.util.LinkedList;

public interface GenericSearchProblem {
    LinkedList<Action> getNextPossibleAction(State state);
    State nextState(State state, Action action);
    State initialState();
    double StepCost(State state, Action action, State results);
    boolean isFinal(State state);
}
