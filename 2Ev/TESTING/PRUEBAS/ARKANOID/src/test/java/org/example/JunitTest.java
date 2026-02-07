package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JunitTest {

    @Test
    public void testGetAreaZero(){
        assertThrows(RuntimeException.class,() -> {
            Junit.getArea(1,1,3);
        },"Si la condición se cumple (lados imposibles), debe lanzar excepción");

        assertThrows(RuntimeException.class,() -> {
            Junit.getArea(3,1,1);
        },"Segunda condición para excepción");

        assertThrows(RuntimeException.class, () -> {
            Junit.getArea(1,3,1);
        },"Tercera condición para la excepción");
    }

    @Test
    public void testGetAreaValid() {
        Junit j = new Junit();
        double area = j.getArea(3,4,5);

        assertEquals(6.0, area, 0.001, "El área debe de ser 6");
    }

    @Test
    public void testTriangleException () {
        assertThrows(RuntimeException.class,() -> {
            Junit.triangle(3,2,1);
        },"Si la condición no se cumple lanza excepción");

        assertThrows(RuntimeException.class,() -> {
            Junit.triangle(1,10,2);
        },"Debe lanzar excepcion si b > c");
    }

    @Test
    public void testTriangleIsosceles() {
        String triangle = Junit.triangle(4,5,6);

        assertEquals("Triangulo isósceles",triangle,"Isóscele = --a < b-- y --b < c-- y --a²+b² > c²--");
    }

    @Test
    public void testTriangleEquilatero(){
        String triangle = Junit.triangle(3,4,5);

        assertEquals("Triangulo equilatero",triangle,"Equilatero = --a < b-- y --b < c-- y (a²+b² == c²)");
    }

    @Test
    public void testTriangleEscaleno() {
        /**
         * La línea del "escaleno" en Junit.java quedará amarilla (cobertura parcial).
         * Esto ocurre porque la condición (a*a + b*b < c*c) es redundante.
         * Los casos donde es MAYOR o IGUAL ya fueron capturados por los 'if' anteriores.
         * Es matemáticamente imposible que la condición evalúe a FALSE en ese punto.
         */
        String triangle = Junit.triangle(1,2,3);

        assertEquals("Triangulo escaleno", triangle, "Escaleno = --a < b-- y --b < c-- y --a²+b²<c²--");
    }


}
