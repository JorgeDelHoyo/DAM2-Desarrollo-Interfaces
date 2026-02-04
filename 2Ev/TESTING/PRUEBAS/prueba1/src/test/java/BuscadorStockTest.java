import org.example.BuscadorStock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuscadorStockTest {

    private final BuscadorStock buscador = new BuscadorStock();

    @Test
    public void testBuscarPrimerAgotadoCantidadesNull() {
        int stock = buscador.buscarPrimerAgotado(null, 0);

        assertEquals(-1, stock, "Si cantidades es null, devuelve -1");
    }

    @Test
    public void testBuscarPrimerAgotadoCantidadesVacio() {
        List<Integer> cantidades = new ArrayList<>();
        int stock = buscador.buscarPrimerAgotado(cantidades,0);

        assertEquals(-1, stock,"Si recorre todo y sale, devuelve -1");
    }

    @Test
    public void testBuscarPrimerAgotadoCantidadesUna() {
        List<Integer> cantidades = List.of(1);
        int stock = buscador.buscarPrimerAgotado(cantidades,2);

        assertEquals(0, stock, "Ha encontrado el primero");
    }

    @Test
    public void testBuscarPrimerAgotadoCantidadesVarias() {
        List<Integer> cantidades = List.of(4,1,3);
        int stock = buscador.buscarPrimerAgotado(cantidades,2);

        assertEquals(1, stock, "Encontrado posición 1");
    }

    @Test
    public void testBuscarPrimerAgotadoCantidadNull() {
        List<Integer> cantidades = new ArrayList<>();
        cantidades.add(null);
        int stock = buscador.buscarPrimerAgotado(cantidades,0);

        assertEquals(-1, stock, "Si cantidad es null, devuelve -1");
    }

}
