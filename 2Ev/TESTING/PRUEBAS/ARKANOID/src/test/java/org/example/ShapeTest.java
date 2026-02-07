package org.example;

import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShapeTest {

    @Test
    public void testGetterSetters() {
        Shape shape = new Shape(10,20,100,200,Color.BLUE);

        assertEquals(10,shape.getX());
        assertEquals(Color.BLUE,shape.getColor());
        assertEquals(100,shape.getWidth());

        shape.setColor(Color.RED);
        assertEquals(Color.RED,shape.getColor());
    }
    /**
    @Test
    public void testConSpy() {

        Shape shape = new Shape(0,0,10,10,Color.BLUE);

        Shape shapeSpy = spy(shape);

        assertEquals(Color.BLUE, shapeSpy.getColor(), "Hemos forzado el color a azul");

        // Modificamos el color
        shapeSpy.setColor(Color.RED);

        // Verificamos que se ha modificado el color
        verify(shapeSpy).setColor(Color.RED);

        // Verificamos que el color cambió de verdad
        assertEquals(Color.RED, shapeSpy.getColor(),"Se ha cambiado el color");
    }
    */
}
