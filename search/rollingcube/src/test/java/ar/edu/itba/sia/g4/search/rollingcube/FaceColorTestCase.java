package ar.edu.itba.sia.g4.search.rollingcube;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;
import junit.framework.TestCase;

import static ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor.*;
import static ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection.*;

public class FaceColorTestCase extends TestCase {

    //ALL BLACK
    public void testAllBlackRollsNorth() {
        FaceColor given = ALL_BLACK;
        FaceColor expected = N_BLACK_S_WHITE;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testAllBlackRollsSouth() {
        FaceColor given = ALL_BLACK;
        FaceColor expected = N_WHITE_S_BLACK;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testAllBlackRollsEast(){
        FaceColor given = ALL_BLACK;
        FaceColor expected = W_WHITE_E_BLACK;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testAllBlackRollsWest() {
        FaceColor given = ALL_BLACK;
        FaceColor expected = W_BLACK_E_WHITE;
        this.assertEquals(given.roll(WEST), expected);
    }

    //ALL WHITE
    public void testAllWhiteRollsNorth() {
        FaceColor given = ALL_WHITE;
        FaceColor expected = N_WHITE_S_BLACK;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testAllWhiteRollsSouth() {
        FaceColor given = ALL_WHITE;
        FaceColor expected = N_BLACK_S_WHITE;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testAllWhiteRollsEast(){
        FaceColor given = ALL_WHITE;
        FaceColor expected = W_BLACK_E_WHITE;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testAllWhiteRollsWest() {
        FaceColor given = ALL_WHITE;
        FaceColor expected = W_WHITE_E_BLACK;
        this.assertEquals(given.roll(WEST), expected);
    }

    //N_BLACK_S_WHITE
    public void testNBlackSWhiteRollsNorth() {
        FaceColor given = N_BLACK_S_WHITE;
        FaceColor expected = ALL_WHITE;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testNBlackSWhiteRollsSouth() {
        FaceColor given = N_BLACK_S_WHITE;
        FaceColor expected = ALL_BLACK;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testNBlackSWhiteRollsEast(){
        FaceColor given = N_BLACK_S_WHITE;
        FaceColor expected = N_BLACK_S_WHITE;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testNBlackSWhiteRollsWest() {
        FaceColor given = N_BLACK_S_WHITE;
        FaceColor expected = N_BLACK_S_WHITE;
        this.assertEquals(given.roll(WEST), expected);
    }

    //N_WHITE_S_BLACK
    public void testNWhiteSBlackRollsNorth() {
        FaceColor given = N_WHITE_S_BLACK;
        FaceColor expected = ALL_BLACK;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testNWhiteSBlackRollsSouth() {
        FaceColor given = N_WHITE_S_BLACK;
        FaceColor expected = ALL_WHITE;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testNWhiteSBlackRollsEast(){
        FaceColor given = N_WHITE_S_BLACK;
        FaceColor expected = N_WHITE_S_BLACK;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testNWhiteSBlackRollsWest() {
        FaceColor given = N_WHITE_S_BLACK;
        FaceColor expected = N_WHITE_S_BLACK;
        this.assertEquals(given.roll(WEST), expected);
    }

    //W_BLACK_E_WHITE

    public void testWBlackEWhiteRollsNorth() {
        FaceColor given = W_BLACK_E_WHITE;
        FaceColor expected = W_BLACK_E_WHITE;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testWBlackEWhiteRollsSouth() {
        FaceColor given = W_BLACK_E_WHITE;
        FaceColor expected = W_BLACK_E_WHITE;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testWBlackEWhiteRollsEast(){
        FaceColor given = W_BLACK_E_WHITE;
        FaceColor expected = ALL_BLACK;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testWBlackEWhiteRollsWest() {
        FaceColor given = W_BLACK_E_WHITE;
        FaceColor expected = ALL_WHITE;
        this.assertEquals(given.roll(WEST), expected);
    }

    //W_WHITE_E_BLACK

    public void testWWhiteEBlackRollsNorth() {
        FaceColor given = W_WHITE_E_BLACK;
        FaceColor expected = W_WHITE_E_BLACK;
        this.assertEquals(given.roll(NORTH), expected);
    }

    public void testWWhiteEBlackRollsSouth() {
        FaceColor given = W_WHITE_E_BLACK;
        FaceColor expected = W_WHITE_E_BLACK;
        this.assertEquals(given.roll(SOUTH), expected);
    }

    public void testWWhiteEBlackRollsEast(){
        FaceColor given = W_WHITE_E_BLACK;
        FaceColor expected = ALL_WHITE;
        this.assertEquals(given.roll(EAST), expected);
    }

    public void testWWhiteEBlackRollsWest() {
        FaceColor given = W_WHITE_E_BLACK;
        FaceColor expected = ALL_BLACK;
        this.assertEquals(given.roll(WEST), expected);
    }

}
