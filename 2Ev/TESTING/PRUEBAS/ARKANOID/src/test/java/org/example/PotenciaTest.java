package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PotenciaTest {

    /**
     * Test para probar exponente == 0 con constructor
     */
    @Test
    public void testPovOf2ExpZero() {
        Potencia p = new Potencia();
        String zero = p.povOf2(0);

        assertEquals("0",zero, "If exp == 0, returns 0");
    }

    /**
     * Test para probar exponentes pequeños
     */
    @Test
    public void testPovOf2ExpOneTwoFour() {
        String one = Potencia.povOf2(1);
        String two = Potencia.povOf2(2);
        String three = Potencia.povOf2(3);

        assertEquals("2",one, "2¹ == 2");
        assertEquals("4", two, "2² == 4");
        assertEquals("8", three,"2³ == 8");
    }

    /**
     * Test para probar un exponente con 2 digitos
     */
    @Test
    public void testPovOf2Exp2Digits() {
        String twelve = Potencia.povOf2(12);

        assertEquals("4096",twelve,"2¹² == 4096");
    }

    /**
     * Test para probar que un resultado tenga 2 digitos
     */
    @Test
    public void testPovOf2ExpIfTempHas2Digits() {
        String four = Potencia.povOf2(4);

        assertEquals("16",four,"2⁴ == 16");
    }
}
