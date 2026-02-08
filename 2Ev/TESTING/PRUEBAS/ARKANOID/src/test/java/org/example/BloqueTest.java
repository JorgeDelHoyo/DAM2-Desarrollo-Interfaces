package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BloqueTest {

    @Test
    public void testIsVisible() {
        Bloque bloque = new Bloque(10,10,30,20, Color.RED);

        assertTrue(bloque.isVisible(), "El bloque por defecto es visible (true)");

        bloque.setVisible(false);
        assertFalse(bloque.isVisible(), "El bloque ha cambiado a no visible (false)");
    }

    @Test
    public void testDibujar() {
        Graphics graphicsMock = mock(Graphics.class);

        Bloque bloque = new Bloque(10,10,20,30,Color.RED);

        bloque.dibujar(graphicsMock);

        verify(graphicsMock).setColor(Color.RED);

        verify(graphicsMock).fillRect(10,10,20,30);
    }
}
