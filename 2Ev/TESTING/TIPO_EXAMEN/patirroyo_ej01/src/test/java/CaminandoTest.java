import org.example.Caminando;
import org.example.DibujoAnimado;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class CaminandoTest {

    @Test
    public void testCambioPersonajeTecladoTodos() {
         Caminando panel = Mockito.spy(new Caminando());

         KeyEvent eventoG = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_G,'g');
         KeyEvent eventoH = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_H,'h');
         KeyEvent eventoV = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_V,'v');

         panel.getKeyListeners()[0].keyPressed(eventoG);
         assertArrayEquals(panel.imagenes[0], panel.personaje.getImagenes(), "Debe ser Guerrero");

         panel.getKeyListeners()[0].keyPressed(eventoH);
         assertArrayEquals(panel.imagenes[1], panel.personaje.getImagenes(), "Debe ser Hampon");

         panel.getKeyListeners()[0].keyPressed(eventoV);
         assertArrayEquals(panel.imagenes[2], panel.personaje.getImagenes(), "Debe ser Vaquero");

         assertNotNull(panel.personaje.getImagenes());
    }

    @Test
    public void testPintarPanel() {
        // GIVEN
        Caminando panel = Mockito.spy(new Caminando());
        Graphics gMock = Mockito.mock(Graphics.class);
        DibujoAnimado personajeMock = Mockito.mock(DibujoAnimado.class);

        // inyectamos el personaje del mock
        panel.personaje = personajeMock;

        // Pedimos que pinte
        panel.personaje.paint(gMock,panel);

        // THEN
        Mockito.verify(personajeMock).paint(any(),any());
    }

    @Test
    public void testMain() {
        try {
            Caminando.main(new String[]{});
        } catch (Exception e) {

        }
    }
}
