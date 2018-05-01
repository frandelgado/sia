package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;

import java.util.HashSet;

public class RollingCubeRule implements Rule<Board> {

    private RollDirection rollDir;
    private double cost;

    public RollingCubeRule(RollDirection rollDir){
        this.rollDir = rollDir;
        this.cost = 1;
    }

    @Override
    public double getCost() {
        return this.cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public Board applyToState(Board board) {
        int[] emptySpot = board.getEmptySpot();
        int i = emptySpot[0] + this.rollDir.i;
        int j = emptySpot[1] + this.rollDir.j;
        Board newBoard = board.cloneBoard();
        Cube cubeToMove = newBoard.getMatrix()[i][j];
        if(cubeToMove.getFaceColor() == FaceColor.ALL_WHITE){
            newBoard.substractWhite();
        }
        FaceColor newFaceColor = cubeToMove.roll(rollDir.getOppositeDirection());
        if(newFaceColor == FaceColor.ALL_WHITE){
            newBoard.addWhite();
        }
        newBoard.getMatrix()[emptySpot[0]][emptySpot[1]] = cubeToMove;
        newBoard.setEmptySpot(i, j);
        return newBoard;
    }

}
