package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

import static ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection.*;


public class RollingCubeGame implements Problem<Board> {

    private Board board;
    private RollDirection[][][] rollDirectionsMatrix;

    public RollingCubeGame() {
        this.board = new Board();
        this.rollDirectionsMatrix = this.generateRuleMatrix();
    }

    private RollDirection[][][] generateRuleMatrix() {
        RollDirection[][][] result = {
                {
                        {EAST, SOUTH}, {EAST, SOUTH, WEST}, {SOUTH, WEST}
                },
                {
                        {NORTH, EAST, SOUTH}, {NORTH, EAST, SOUTH, WEST}, {NORTH, SOUTH, WEST}
                },
                {
                        {NORTH, EAST}, {NORTH, EAST, WEST}, {NORTH, WEST}
                }
        };
        return result;
    }

    @Override
    public Board getInitialState() {
        return this.board;
    }

    @NotNull
    @Override
    public List<Rule<Board>> getRules(Board board) {
        int[] emptySpot = board.getEmptySpot();
        RollDirection[] rollDirections = this.rollDirectionsMatrix[emptySpot[0]][emptySpot[1]];
        List<Rule<Board>> rules = new ArrayList<>();
        RollDirection rollDir;
        Cube cubeToMove;
        Board newBoard;
        int size = rollDirections.length;
        int i;
        int j;
        for(int k = 0; k < size; i++){
            rollDir = rollDirections[k];
            i = emptySpot[0] + rollDir.i;
            j = emptySpot[1] + rollDir.j;
            newBoard = board.cloneBoard();
            cubeToMove = newBoard.getMatrix()[i][j];
            cubeToMove.roll(rollDir);
            newBoard.getMatrix()[emptySpot[0]][emptySpot[1]] = cubeToMove;
            newBoard.setEmptySpot(i, j);
            rules.add(new RollingCubeRule(newBoard));
        }
        return rules;
    }

    @Override
    public boolean isResolved(Board rollingCubeState) {
        return this.board.isResolved();
    }

}
