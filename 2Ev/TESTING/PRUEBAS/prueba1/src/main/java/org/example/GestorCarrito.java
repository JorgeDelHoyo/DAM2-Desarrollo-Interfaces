package org.example;

import java.util.List;

public class GestorCarrito {

    public double calcularPrecioFinal(List<Double> productos, String cupon, boolean esPremium) {
        if (productos == null || productos.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        // Bucle con chicha: recorremos los productos
        for (Double precio : productos) {
            if (precio != null && precio > 0) {
                total += precio;
            }
        }

        // Lógica de descuentos
        if (cupon != null && cupon.equals("DESCUENTO10")) {
            total = total * 0.90;
        } else if (esPremium && total > 100) {
            total -= 20; // Regalo de 20€ a premium por compra grande
        }

        // Impuestos (IVA 21%)
        return total * 1.21;
    }
}
