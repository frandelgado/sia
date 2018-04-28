package ar.edu.itba.sia.g4.search.rollingcube;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection;
import junit.framework.TestCase;

import static ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor.ALL_BLACK;
import static ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor.ALL_WHITE;
import static ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor.N_BLACK_S_WHITE;
import static ar.edu.itba.sia.g4.search.rollingcube.action.RollDirection.NORTH;

public class FaceColorTestCase extends TestCase {

    public void testAllBlackRollsNorth() {
        FaceColor given = ALL_BLACK;
        FaceColor expected = N_BLACK_S_WHITE;
        this.assertEquals(given.roll(NORTH), expected);
    }
}
