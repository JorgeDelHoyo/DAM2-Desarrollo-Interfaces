import org.example.GestorCarrito;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorCarritoTest {

    private final GestorCarrito gestor = new GestorCarrito();

    @Test
    public void testCalcularPrecioFinalProductosNullEmpty() {
        List<Double> productos = new ArrayList<>();

        double precioFinal1 = gestor.calcularPrecioFinal(null,null,false);
        double precioFinal = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(0, precioFinal1," Si productos null,devuelve 0");
        assertEquals(0, precioFinal1, "Si productos empty, devuelve 0");
    }

    @Test
    public void testCalcularPrecioFinalVariosProductos() {
        List<Double> productos = List.of(50.0,30.0,20.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(121.0,resultado, "El cálculo del IVA es incorrecto");
    }

    @Test
    public void testCalcularPrecioFinalUnProducto() {
        List<Double> productos = List.of(10.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(12.1, resultado, "El Bucle tiene 1 objeto");
    }

    @Test
    public void testCalcularPrecioFinalCeroProductos() {
        List<Double> productos = List.of();
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(0, resultado, "El bucle tiene 0 objetos");
    }

    @Test
    public void testCalcularPrecioFinalPrecioNull() {
        List<Double> productos = new ArrayList<>();
        productos.add(null);
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(0, resultado, "El precio es null");
    }

    @Test
    public void testCalcularPrecioFinalPrecioNegativo() {
        List<Double> productos = List.of(-10.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(0, resultado, "El precio es negativo");
    }

    @Test
    public void testCalcularPrecioFinalPrecioCorrecto() {
        List<Double> productos = List.of(100.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,false);

        assertEquals(121.0,resultado, "El precio es correcto");
    }

    @Test
    public void testCalcularPrecioFinalCuponNull() {
        double resultado = gestor.calcularPrecioFinal(null,null,false);

        assertEquals(0, resultado, "El descuento es null");
    }

    @Test
    public void testCalcularPrecioFinalCuponAleatorio() {
        List<Double> productos = List.of(100.0);
        double resultado = gestor.calcularPrecioFinal(productos,"aaa",false);

        assertEquals(121.0, resultado, "El descuento es otro");
    }

    @Test
    public void testCalcularPrecioFinalCuponCorrecto() {
        List<Double> productos = List.of(10.0);

        double resultado = gestor.calcularPrecioFinal(productos,"DESCUENTO10",false);

        assertEquals(10.89, resultado, "El descuento es correcto");
    }

    @Test
    public void testCalcularPrecioFinalEsPremium() {
        List<Double> productos = List.of(120.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,true);

        assertEquals(121.0, resultado,"Es premium");
    }

    @Test
    public void testCalcularPrecioFinalEsPremiumTotalMenor() {
        List<Double> productos = List.of(80.0);
        double resultado = gestor.calcularPrecioFinal(productos,null,true);

        assertEquals(96.8,resultado,"Es premium pero cantidad < 100");
    }
}