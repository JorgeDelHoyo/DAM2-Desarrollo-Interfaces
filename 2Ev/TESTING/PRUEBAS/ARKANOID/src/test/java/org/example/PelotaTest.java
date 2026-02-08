package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PelotaTest {

    @Test
    public void testPelota() {
        Pelota pelota = null;
        Pelota pelota2 = new Pelota(50,50,20,20,Color.CYAN);

        assertNull(pelota,"Constructor inicializado null");
        assertEquals(50,pelota2.getX());
        assertEquals(Color.CYAN,pelota2.getColor());
    }

    @Test
    public void testDibujar() {
        // Crear mock con .class
        Graphics graphicsMock = mock(Graphics.class);
        // Crear constructor entero
        Pelota p = new Pelota(10,20,30,30,Color.MAGENTA);
        // Llamar al metodo
        p.dibujar(graphicsMock);
        // Verificar
        verify(graphicsMock).setColor(Color.MAGENTA);
        verify(graphicsMock).fillOval(10,20,30,30);
    }

    @Test
    public void testMoverNormal(){
        Pelota p = new Pelota(0,0,10,10,Color.BLUE);

        p.velX = 5;
        p.velY = 2;

        p.mover();

        assertEquals(5, p.getX(),"La x ha aumentado 5");
        assertEquals(2,p.getY(),"La y ha aumentado 2");
    }

    @Test
    public void testMoverReboteIzquierda(){
        Pelota p = new Pelota(-5,10,10,10,Color.RED);
        p.velX = -5;

        p.mover();
        // Posicion -10

        assertEquals(5, p.velX,"La pelota rebota hacia la derecha");
    }

    @Test
    public void testMoverReboteDerecha(){
        Pelota p = new Pelota(GameConstants.SCREEN_LIMIT+10,10,10,10,Color.GRAY);
        p.velX = 10;

        p.mover();
        // Posición 310

        assertEquals(-10,p.velX,"La pelota rebota hacia la izquierda");
    }

    @Test
    public void testMoverReboteArriba() {
        Pelota p = new Pelota(20,-10,10,30,Color.GREEN);
        p.velY = -10;

        p.mover();
        // Posicion -20

        assertEquals(10,p.velY,"La pelota rebota hacia arriba");
    }
}
