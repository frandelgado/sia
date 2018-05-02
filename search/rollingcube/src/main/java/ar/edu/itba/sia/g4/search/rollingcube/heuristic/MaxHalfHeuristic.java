package ar.edu.itba.sia.g4.search.rollingcube.heuristic;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.g4.search.rollingcube.game.Board;
import ar.edu.itba.sia.g4.search.rollingcube.game.Cube;

public class MaxHalfHeuristic implements Heuristic<Board> {
    @Override
    public double getValue(Board board) {
        double result = 0.0;
        Cube cube;
        int i;
        int j;
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                cube = board.getMatrix()[i][j];
                if(board.getMatrix()[i][j] != null){
                    switch (cube.getFaceColor()){
                        case ALL_WHITE:
                            result += 2.0;
                        case ALL_BLACK:
                            result += 3.0;
                        default:
                            result += 1.0;
                    }
                }
            }
        }
        return result;
    }
}
