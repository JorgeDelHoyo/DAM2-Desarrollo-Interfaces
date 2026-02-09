import org.example.DibujoAnimado;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class DibujoAnimadoTest {

    @Test
    public void testPaint(){
        // GIVEN
        Image img1 = Mockito.mock(Image.class);
        Graphics gMock = Mockito.mock(Graphics.class);
        Component cMock = Mockito.mock(Component.class);
        DibujoAnimado dibujoAnimado = new DibujoAnimado(new Image[]{img1});

        // WHEN
        dibujoAnimado.paint(gMock, cMock);

        // THEN
        // Verificar que se llamó a drawImage con los valores pasados
        Mockito.verify(gMock).drawImage(img1,0,0,200,300,cMock);
    }
}