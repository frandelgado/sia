package ar.edu.itba.sia.g4.search.rollingcube.heuristic;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.game.Board;
import ar.edu.itba.sia.g4.search.rollingcube.game.Cube;

public class MaxWhiteHeuristic implements Heuristic<Board> {
    @Override
    public double getValue(Board board) {
        double result = 0.0;
        Cube cube;
        int i;
        int j;
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                cube = board.getMatrix()[i][j];
                if(board.getMatrix()[i][j] != null && cube.getFaceColor() != FaceColor.ALL_WHITE){
                    if(cube.getFaceColor() == FaceColor.ALL_BLACK){
                        result += 2.0;
                    }else{
                        result += 1.0;
                    }
                }
            }
        }
        return result;
    }
}
