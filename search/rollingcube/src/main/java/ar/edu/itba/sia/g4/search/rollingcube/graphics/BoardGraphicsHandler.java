package ar.edu.itba.sia.g4.search.rollingcube.graphics;

import ar.edu.itba.sia.g4.search.rollingcube.game.Board;
import ar.edu.itba.sia.g4.search.rollingcube.tools.Observer;

public class BoardGraphicsHandler implements Observer<Board> {
    private BoardGraphics boardGraphics;

    public BoardGraphicsHandler(BoardGraphics boardGraphics) {
        this.boardGraphics = boardGraphics;
    }

    @Override
    public void Update(Board board) {
        this.boardGraphics.updateBoard(board.getCubeMatrix());
    }
}
