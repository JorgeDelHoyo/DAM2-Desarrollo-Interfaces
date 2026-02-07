package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ColaTest {

    @Test
    public void testColaVacio() {
        Cola<String> cola = new Cola<>();
        assertEquals(0, cola.numElementos()," La cola debería tener 0 elementos");
    }

    @Test
    public void testEncolarCola() {
        Cola<String> cola = new Cola<>();
        cola.encolar("Prueba");

        assertEquals(1,cola.numElementos(),"Debería haber añadido 'PRUEBA' a cola");
    }

    @Test
    public void testDesencolarCola() throws ColaVacia {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(1);
        cola.encolar(2);

        assertEquals(1, cola.desencolar(),"Debe de salir el 1");
        assertEquals(2, cola.desencolar(),"Debe de salir el 2");
    }

    @Test
    public void testDesencolarColaVacia(){
        Cola<Integer> cola = new Cola<>();

        assertThrows(ColaVacia.class, () -> {
            cola.desencolar();
        },"Debería de salir una excepcion si está vacía");
    }
}
