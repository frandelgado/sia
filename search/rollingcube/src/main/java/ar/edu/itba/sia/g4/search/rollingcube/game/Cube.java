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

    public FaceColor roll(RollDirection rollDirection){
        this.faceColor = this.faceColor.roll(rollDirection);
        return this.faceColor;
    }

    public Cube cloneCube() {
        return new Cube(this.faceColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cube cube = (Cube) o;

        return faceColor == cube.faceColor;
    }

    @Override
    public int hashCode() {
        return faceColor.hashCode();
    }
}
