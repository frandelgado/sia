package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;
import ar.edu.itba.sia.g4.search.rollingcube.graphics.BoardGraphics;
import ar.edu.itba.sia.g4.search.rollingcube.graphics.BoardGraphicsHandler;
import ar.edu.itba.sia.g4.search.rollingcube.graphics.GraphicsFactory;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

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
        int size = rollDirections.length;
        for(int k = 0; k < size; k++){
            rules.add(new RollingCubeRule(rollDirections[k]));
        }
        return rules;
    }

    @Override
    public boolean isResolved(Board board) {
        return this.board.isResolved();
    }

}
