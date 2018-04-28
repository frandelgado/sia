package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;

public class Board {

    private Cube[][] matrix;
    private int[] emptySpot;
    private int whiteCount;

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
        this.whiteCount = 0;
    }

    public Board(Cube[][] matrix, int[] emptySpot, int whiteCount){
        this.matrix = matrix;
        this.emptySpot = emptySpot;
        this.whiteCount = whiteCount;
    }

    public boolean isResolved() {
        if(this.matrix[1][1] != null) {
            return false;
        }
        return this.whiteCount == 8;
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

    public void addWhite(){
        this.whiteCount++;
    }

    public void substractWhite(){
        this.whiteCount--;
    }

    public Board cloneBoard() {
        Cube[][] newMatrix = new Cube[3][3];
        int newWhiteCount;
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
        newWhiteCount = this.whiteCount;
        return new Board(matrix, newEmptySpot, newWhiteCount);
    }
}
