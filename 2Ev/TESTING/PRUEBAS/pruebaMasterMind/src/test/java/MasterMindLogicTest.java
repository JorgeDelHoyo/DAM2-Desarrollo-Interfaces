import org.example.MasterMindLogic;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterMindLogicTest {

    private final Color[] PALETTE = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private final String[] LABELS = {"R","G","B","Y"};

    @Test
    public void testGenerateSecret() {
        MasterMindLogic logic = new MasterMindLogic(PALETTE,4,LABELS);
        String secreto = logic.showSecret();
        assertEquals(4, secreto.length(), "El secreto aleatorio debe tiene 4 de longitud");
    }

    @Test
    public void testCheckGuessAciertoTotal() {
        Color[] secretoFijo = {Color.RED, Color.RED};
        MasterMindLogic logic = new MasterMindLogic(PALETTE,secretoFijo,LABELS);

        Color[] intento = {Color.RED, Color.RED};
        MasterMindLogic.Result resultado = logic.checkGuess(intento);

        assertEquals(2,resultado.blacks, "Hay 2/2 aciertos posición");
        assertEquals(0, resultado.whites, "Hay 0/2 fallos posición");
    }

    @Test
    public void testCheckGuessAciertoUna() {
        Color[] secretoFijo = {Color.RED, Color.RED};
        MasterMindLogic logic = new MasterMindLogic(PALETTE,secretoFijo,LABELS);

        Color[] intento = {Color.RED, Color.BLUE};
        MasterMindLogic.Result resultado = logic.checkGuess(intento);

        assertEquals(1,resultado.blacks, "Hay 1/2 aciertos posición");
        assertEquals(0, resultado.whites, "Hay 1/2 fallos posición");
    }

    @Test
    public void testCheckGuessNingunAcierto() {
        Color[] secretoFijo = {Color.RED, Color.RED};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, secretoFijo, LABELS);

        Color[] intento = {Color.BLUE, Color.YELLOW};
        MasterMindLogic.Result resultado = logic.checkGuess(intento);

        assertEquals(0,resultado.blacks,"Hay 0/2 aciertos posición");
        assertEquals(0, resultado.whites, "Hay 0/2 fallos posición");
    }

    @Test
    public void testCheckGuessUnaPosicionIncorrecta() {
        Color[] secretoFijo = {Color.RED, Color.BLUE};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, secretoFijo, LABELS);

        Color[] intento = {Color.BLUE, Color.YELLOW};
        MasterMindLogic.Result resultado = logic.checkGuess(intento);

        assertEquals(0, resultado.blacks, "Hay 0/2 aciertos posición");
        assertEquals(1, resultado.whites, "Hay 1/2 fallos posición");
    }

    @Test
    public void testCheckGuessNingunaPosicionCorrecta() {
        Color[] secretoFijo = {Color.RED, Color.YELLOW};
        MasterMindLogic logic = new MasterMindLogic(PALETTE, secretoFijo, LABELS);

        Color[] intento = {Color.YELLOW, Color.RED};
        MasterMindLogic.Result resultado = logic.checkGuess(intento);

        assertEquals(0, resultado.blacks, "Hay 0/2 aciertos posición");
        assertEquals(2, resultado.whites, "Hay 2/2 fallos posición");
    }
}
