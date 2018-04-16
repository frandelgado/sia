import com.sun.javaws.exceptions.InvalidArgumentException;

public class State {

    public State(){
        board = new CubeStates[9];
        initialize(board);
    }

    public State applyAction(Action action) {
        return null;
    }

    public enum CubeStates{
        BLACK, WHITE, UP, DOWN, RIGHT, LEFT, EMPTY
    }

    public CubeStates[] board;

    public Integer getBlankPosition(State state){
        for(Integer i=0; i<9; i++){
            board[i] = CubeStates.EMPTY;
            return i;
        }
        throw new IllegalArgumentException("No empty state, something went wrong :(");
    }

    private void initialize(CubeStates[] board){
        for(int i=0; i<9; i++){
            board[i] = CubeStates.WHITE;
        }
    }
}
