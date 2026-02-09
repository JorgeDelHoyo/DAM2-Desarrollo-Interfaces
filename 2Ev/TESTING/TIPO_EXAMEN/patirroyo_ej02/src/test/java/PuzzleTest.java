import org.example.Pieza;
import org.example.Puzzle;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PuzzleTest {

    @Test
    public void testInitCargaTodo() {
        Puzzle puzzle = new Puzzle();

        assertNotNull(puzzle);
        assertEquals(25, puzzle.piezas.length);
    }

    @Test
    public void testMouseReleasedEncajaPieza() {
        // GIVEN: Una pieza en su sitio correspondiente
        // Suponemos que usamos la pieza 0 y el sitio 0
        Puzzle puzzle = new Puzzle();
        Pieza pieza = puzzle.piezas[0];
        Rectangle sitio = puzzle.sitios[0];

        // Forzamos que la pieza esté solapada con el sitio
        pieza.x = sitio.x +1;
        pieza.y = sitio.y +1;

        // WHEN: Simulamos el evento de soltar el ratón
        // Necesitamos que 'actual' sea la pieza 0 y 'posActual' sea 0
        puzzle.actual = pieza;
        puzzle.posActual = 0;

        MouseEvent event = new MouseEvent(puzzle,MouseEvent.MOUSE_RELEASED,0,0,0,0,0,false);
        puzzle.getMouseListeners()[0].mouseReleased(event);

        // THEN: La pieza debe haber saltado a la posición exacta
        assertEquals(100, pieza.x);
        assertEquals(150, pieza.y);
    }

    @Test
    public void testMouseReleasedNull() {
        Puzzle puzzle = new Puzzle();
        Pieza pieza = puzzle.piezas[0];
        puzzle.actual = pieza;
        puzzle.actualIniX = 800;
        puzzle.actualIniY = 800;

        pieza.x = 0;
        pieza.y = 0;

        MouseEvent event = new MouseEvent(puzzle,MouseEvent.MOUSE_RELEASED,0,0,0,0,0,false);
        puzzle.getMouseListeners()[0].mouseReleased(event);

        assertEquals(800, pieza.x," Debería volver a su posición inicial");
    }

    @Test
    public void testMouseDraggedMuevePieza() {
        Puzzle puzzle = new Puzzle();
        Pieza pieza = puzzle.piezas[0];
        puzzle.actual = pieza;

        MouseEvent dragEvent = new MouseEvent(puzzle, 0, 0, 0, 300, 300, 0, false);
        puzzle.getMouseMotionListeners()[0].mouseDragged(dragEvent);

        // Verificamos que la pieza se haya movido
        // x=300 - 30 = 270 --> Metodo update de Pieza
        assertEquals(270, pieza.x);
    }
}
