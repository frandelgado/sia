import java.util.LinkedList;

public class Problem implements GenericSearchProblem {

    @Override
    public LinkedList<Action> getNextPossibleAction(State state) {
        LinkedList<Action> actions = new LinkedList<>();
        Integer blankPosition = state.getBlankPosition();
        Integer row = blankPosition/3;
        Integer col = blankPosition%3;
        switch (blankPosition){
            case 0:
                actions.add(new Action(1,Action.MovingDirection.LEFT));
                actions.add(new Action(3,Action.MovingDirection.UP));
                break;

            case 1:
                actions.add(new Action(0,Action.MovingDirection.RIGHT));
                actions.add(new Action(2,Action.MovingDirection.LEFT));
                actions.add(new Action(4,Action.MovingDirection.UP));
                break;

            case 2:
                actions.add(new Action(1,Action.MovingDirection.RIGHT));
                actions.add(new Action(5,Action.MovingDirection.UP));
                break;

            case 3:
                actions.add(new Action(0,Action.MovingDirection.DOWN));
                actions.add(new Action(4,Action.MovingDirection.LEFT));
                actions.add(new Action(6,Action.MovingDirection.UP));
                break;

            case 4:
                actions.add(new Action(1,Action.MovingDirection.DOWN));
                actions.add(new Action(3,Action.MovingDirection.RIGHT));
                actions.add(new Action(5,Action.MovingDirection.LEFT));
                actions.add(new Action(7,Action.MovingDirection.UP));
                break;

            case 5:
                actions.add(new Action(2,Action.MovingDirection.DOWN));
                actions.add(new Action(4,Action.MovingDirection.RIGHT));
                actions.add(new Action(8,Action.MovingDirection.UP));
                break;

            case 6:
                actions.add(new Action(3,Action.MovingDirection.DOWN));
                actions.add(new Action(7,Action.MovingDirection.LEFT));
                break;

            case 7:
                actions.add(new Action(4,Action.MovingDirection.DOWN));
                actions.add(new Action(6,Action.MovingDirection.RIGHT));
                actions.add(new Action(8,Action.MovingDirection.LEFT));
                break;

            case 8:
                actions.add(new Action(5,Action.MovingDirection.DOWN));
                actions.add(new Action(7,Action.MovingDirection.RIGHT));
                break;
        }
        return actions;
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
