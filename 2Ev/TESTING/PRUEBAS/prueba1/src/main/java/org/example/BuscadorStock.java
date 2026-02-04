package org.example;

import java.util.List;

public class BuscadorStock {

    /**
     * Busca el primer producto que rompe el stock mínimo.
     * @return La posición (índice) del producto, o -1 si todo está bien.
     */
    public int buscarPrimerAgotado(List<Integer> cantidades, int stockMinimo) {
        if (cantidades == null) {
            return -1;
        }

        int i = 0;
        // Bucle while con "chicha"
        while (i < cantidades.size()) {
            Integer cantidad = cantidades.get(i);

            if (cantidad != null && cantidad < stockMinimo) {
                return i; // Cortocircuito: salimos en cuanto encontramos uno
            }
            i++;
        }

        return -1; // Hemos recorrido todo y no hay problemas
    }
}
