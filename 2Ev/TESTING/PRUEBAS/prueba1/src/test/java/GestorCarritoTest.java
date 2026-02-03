import org.example.GestorCarrito;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorCarritoTest {

    private final GestorCarrito gestor = new GestorCarrito();

    @Test
    public void testCalcularPrecioFinalProductosNullVacio() {
        double precio = gestor.calcularPrecioFinal(null,null,false);

        List<Double> productos = new ArrayList<>();
        double precio2 = gestor.calcularPrecioFinal(productos, null, false);

        assertEquals(0.0,precio, "Si la lista es null devuelve 0");
        assertEquals(0.0, precio2,"Si la lista esta vacía devuelve 0");
    }
}
