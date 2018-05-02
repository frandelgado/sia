package ar.edu.itba.sia.g4.search.rollingcube.heuristic;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.game.Board;
import ar.edu.itba.sia.g4.search.rollingcube.game.Cube;

import java.util.HashMap;
import java.util.Map;

public class DistributedHalfHeuristic implements Heuristic<Board> {
    @Override
    public double getValue(Board board) {
        Map<FaceColor,Integer> colors = new HashMap<>();
        double result = 0.0;
        Cube cube;
        FaceColor faceColor;
        int i;
        int j;
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                cube = board.getMatrix()[i][j];
                if(board.getMatrix()[i][j] != null){
                    faceColor = cube.getFaceColor();
                    switch (faceColor){
                        case ALL_WHITE:
                            result += 2.0;
                        case ALL_BLACK:
                            result += 3.0;
                        default:
                            Integer count = 0;
                            if (colors.containsKey(faceColor)){
                                count = colors.get(faceColor);
                            }
                            colors.put(faceColor, ++count);
                            result += 1.0;
                    }
                }
            }
        }
        for (Integer v: colors.values()){
            result = result + Math.pow(v,1.5);
        }
        return result;
    }
}
