package ar.edu.itba.sia.g4.search.rollingcube.graphics;

import ar.edu.itba.sia.g4.search.rollingcube.game.Cube;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BoardGraphics extends Canvas {

    private static double sceneHeight = 800;
    private static double sceneWidth = 800;

    private Cube[][] board;

    public BoardGraphics() {
        super(sceneWidth, sceneHeight);
    }

    public BoardGraphics(Cube[][] board) {
        super(sceneWidth, sceneHeight);
        this.board = board;
    }

    public void updateBoard(Cube[][] board) {
        this.board = board;
        updateBoardUI();
    }

    public void updateBoardUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Image image = null;
                        GraphicsContext gc = getGraphicsContext2D();
                        gc.clearRect(0, 0, sceneWidth, sceneHeight);
                        int j;
                        Cube cube;
                        for (int i = 0; i < 3; i++) {
                            for (j = 0; j < 3; j++) {
                                cube =  board[i][j];
                                if(cube ==  null){
                                    image = GraphicsFactory.getEmptySpotImage();
                                }else{
                                    image = GraphicsFactory.getFaceColorImage(cube.getFaceColor());
                                }
                                gc.drawImage(image, j * ((sceneWidth) / 3), i * ((sceneHeight) / 3));
                            }
                        }
                    }
                });
            }
        }).start();
    }
}