package ar.edu.itba.sia.g4.search.rollingcube.game;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;

public class Cube {
    private FaceColor faceColor;

    public Cube(){
        this.faceColor = FaceColor.ALL_BLACK;
    }

    public Cube(FaceColor faceColor){
        this.faceColor = faceColor;
    }

    public FaceColor getFaceColor() {
        return faceColor;
    }

    public void roll(RollDirection rollDirection){
        this.faceColor = this.faceColor.roll(rollDirection);
    }

    public Cube cloneCube() {
        return new Cube(this.faceColor);
    }
}
