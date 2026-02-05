import org.example.MasterMindLogic;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MasterMindLogicTest {
    private final Color[] PALETTE = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private final String[] LABELS = {"R", "G", "B", "Y"};


    @Test
    public void testGenerateSecretLength4() {
        MasterMindLogic logic = new MasterMindLogic(PALETTE, 4, LABELS);

        String secret = logic.showSecret();
        assertEquals(4, secret.length(), "Length must be 4");
    }

    @Test
    public void testShowSecretNotExistsColor() {
        Color[] forced = {Color.BLACK};
        MasterMindLogic logic = new MasterMindLogic(PALETTE,forced, LABELS);

        assertEquals("", logic.showSecret(), "Color doesn't exists");
    }


    @Test
    public void testCheckGuessAllBlacksZeroWhite() {
        Color[] forced = {Color.RED, Color.RED};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, forced, LABELS);

        Color[] rndom = {Color.RED, Color.RED};
        MasterMindLogic.Result result = logic.checkGuess(rndom);

        assertEquals(2, result.blacks, "2 correct position");
        assertEquals(0, result.whites, "0 incorrect position");
    }

    @Test
    public void testCheckGuessOneBlackZeroWhite() {
        Color[] forced = {Color.RED, Color.GREEN};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, forced, LABELS);

        Color[] rndom = {Color.RED, Color.RED};
        MasterMindLogic.Result result = logic.checkGuess(rndom);

        assertEquals(1, result.blacks, "1 correct position");
        assertEquals(0, result.whites, "0 incorrect position");
    }

    @Test
    public void testCheckGuessZeroBlackZeroWhite() {
        Color[] forced = {Color.RED, Color.GREEN};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, forced, LABELS);

        Color[] rndm = {Color.BLUE, Color.YELLOW};
        MasterMindLogic.Result result = logic.checkGuess(rndm);

        assertEquals(0, result.blacks, "0 correct position");
        assertEquals(0, result.whites, "0 incorrect position");
    }

    @Test
    public void testCheckGuessZeroBlackOneWhite() {
        Color[] forced = {Color.RED, Color.GREEN};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, forced, LABELS);

        Color[] rndm = {Color.BLUE, Color.RED};
        MasterMindLogic.Result result = logic.checkGuess(rndm);

        assertEquals(0, result.blacks, "0 correct position");
        assertEquals(1, result.whites, "1 incorrect position");
    }

    @Test
    public void testCheckGuessZeroBlackTwoWhite() {
        Color[] forced = {Color.RED, Color.GREEN};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, forced, LABELS);

        Color[] rndm = {Color.GREEN, Color.RED};
        MasterMindLogic.Result result = logic.checkGuess(rndm);

        assertEquals(0, result.blacks, "0 correct position");
        assertEquals(2, result.whites, "2 incorrect position");
    }
}
