import org.example.Pieza;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

public class PiezaTest {

    @Test
    public void testPaint() {
        Graphics gMock = Mockito.mock(Graphics.class);
        Component cMock = Mockito.mock(Component.class);
        Image imgMock = Mockito.mock(Image.class);
        Pieza pieza = new Pieza(imgMock, 1);

        pieza.paint(gMock, cMock);

        Mockito.verify(gMock).drawImage(imgMock,pieza.x,pieza.y,cMock);

        assertEquals(1, pieza.getPosicion(), "Devuelve la posicion");
    }

    @Test
    public void testUpdateNotOk() {
        Image imgMock = Mockito.mock(Image.class);
        Pieza pieza = new Pieza(imgMock, 1);

        pieza.update(100,60);

        // x=100 -> this.x = 100-(60/2)
        assertEquals(70, pieza.x);
        // x=60 -> this.y = 60-(60/2)
        assertEquals(30, pieza.y);
    }

    @Test
    public void testSetOkBloqueaUpdate() {
        Image imgMock = Mockito.mock(Image.class);
        Pieza pieza = new Pieza(imgMock,1);

        int xInicial = pieza.x;
        int yInicial = pieza.y;

        pieza.setOk();

        pieza.update(100,200);

        assertEquals(xInicial,pieza.x,"No debería cambiar si esta OK");
        assertEquals(yInicial, pieza.y,"No debería cambiar si esta OK");
    }

    @Test
    public void testSettersPosicion() {
        Image imgMock = Mockito.mock(Image.class);
        Pieza pieza = new Pieza(imgMock,1);

        pieza.setX(500);
        pieza.setY(600);

        assertEquals(500, pieza.x,"La X debería ser 500");
        assertEquals(600, pieza.y,"La Y debería ser 600");
    }
}
