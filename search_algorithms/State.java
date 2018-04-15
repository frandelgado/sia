public class State {

    public State applyAction(Action action) {
        return null;s
    }

    public enum CubeStates{
        BLACK, WHITE, UP, DOWN, RIGHT, LEFT, EMPTY
    }

    public CubeStates[] board = new CubeStates[9];
}
