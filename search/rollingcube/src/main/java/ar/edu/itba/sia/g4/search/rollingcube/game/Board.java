package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;

public class Board {

    private Cube[][] matrix;
    private int[] emptySpot;

    public Board(){
        int i;
        int j;
        this.matrix = new Cube[3][3];
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                matrix[i][j] = new Cube();
            }
        }
        this.matrix[1][1] = null;
        this.emptySpot = new int[]{1, 1};
    }

    public Board(Cube[][] matrix, int[] emptySpot){
        this.matrix = matrix;
        this.emptySpot = emptySpot;
    }

    public boolean isResolved() {
        if(this.matrix[1][1] != null) {
            return false;
        }
        int i;
        int j;
        Cube cube;
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                cube = this.matrix[i][j];
                if(cube != null && cube.getFaceColor() != FaceColor.ALL_WHITE){
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getEmptySpot() {
        return emptySpot;
    }

    public void setEmptySpot(int[] emptySpot) {
        this.emptySpot = emptySpot;
    }

    public Cube[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Cube[][] matrix) {
        this.matrix = matrix;
    }

    public void setEmptySpot(int i, int j){
        this.emptySpot = new int[]{i, j};
        this.matrix[i][j] = null;
    }

    public Board cloneBoard() {
        Cube[][] newMatrix = new Cube[3][3];
        int[] newEmptySpot;
        int i;
        int j;
        for(i = 0;i < 3; i++){
            for(j = 0; j < 3;j++){
                if(this.matrix[i][j] != null){
                    newMatrix[i][j] = this.matrix[i][j].cloneCube();
                }
            }
        }
        newMatrix[this.emptySpot[0]][this.emptySpot[1]] = null;
        newEmptySpot = new int[]{this.emptySpot[0], this.emptySpot[1]};
        return new Board(matrix, newEmptySpot);
    }
}
