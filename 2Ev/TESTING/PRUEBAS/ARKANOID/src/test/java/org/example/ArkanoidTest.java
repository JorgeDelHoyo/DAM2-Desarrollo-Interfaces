package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArkanoidTest {

    @Test
    public void testReadFileCanNotRead() {
        File f = Mockito.mock(File.class);

        Arkanoid arkanoid = new Arkanoid();
        when(f.canRead()).thenReturn(false);

        assertEquals(3000,arkanoid.readFile(f),"Si no puede leer devuelve 3000");
    }


    @Test
    public void testReadFileCanRead() throws IOException {
        Arkanoid arkanoid = new Arkanoid();

        java.io.File tempFile = java.io.File.createTempFile("test",".txt");
        tempFile.deleteOnExit();

        int resultado = arkanoid.readFile(tempFile);

        assertNotEquals(3000, resultado);
    }

    @Test
    public void testUpdate2(){
        Graphics graphicMock = Mockito.mock(Graphics.class);
        Arkanoid arkanoid = new Arkanoid();
        Rectangle rectangulo = new Rectangle(0,0,200,300);

        // Cuando llamas a getClipBounds se cree el rectángulo con el mock graphic
        when(graphicMock.getClipBounds()).thenReturn(rectangulo);

        // Verificamos que lo contiene
        assertTrue(arkanoid.update2(graphicMock)," Devuelve true porque el rectangulo lo contiene");

        // Verificamos que se crea
        verify(graphicMock).create();
    }

    @Test
    public void testMouseMoveDentroLimites(){
        Arkanoid arkanoid = new Arkanoid();
        // ratX empieza en 0, asi que entra en el if
        boolean resultado = arkanoid.mouseMove(null,100,0);

        assertTrue(resultado,"Debe devolver true si está en limites");
        assertEquals(75,arkanoid.ratX,"La posicion de raqueta debe ser x-25 == 100-25");
    }

    @Test
    public void testMouseMoveFueraLimites(){
        Arkanoid arkanoid = new Arkanoid();

        arkanoid.ratX = 300;
        boolean resultado = arkanoid.mouseMove(null, 300, 0);


        assertFalse(resultado," Debe devolver false si está fuera de límites");
        assertEquals(289,arkanoid.ratX, "Si se sale de límites, la raqueta se queda en 289");
    }

    @Test
    public void testInit_CrearBloques() {
        // Spy para ejecutar codigo personalizado
        Arkanoid arkaSpy = Mockito.spy(new Arkanoid());

        // Mocks para "pintar pantallas"
        Image imageMock = Mockito.mock(Image.class);
        Graphics graphMock = Mockito.mock(Graphics.class);

        // Cuando el juego pida crear una imagen se le pasará una falsa
        doReturn(imageMock).when(arkaSpy).createImage(anyInt(), anyInt());
        when(imageMock.getGraphics()).thenReturn(graphMock);

        // Init del spy que ejecuta el código real
        arkaSpy.init();

        // Verifiación
        assertEquals(5, arkaSpy.vidas.length,"Debe haber 5 vidas iniciales");

        assertNotNull(arkaSpy.bloques[0][0],"Los bloques deben haberse inicializado");
        assertNotNull(arkaSpy.pelota,"La pelota debe haberse creado");
        assertNotNull(arkaSpy.vidas[0],"El array de vidas debe estar lleno");

        // Verificar propiedades de un bloque al azar
        assertEquals(Color.BLUE, arkaSpy.bloques[0][0].getColor());
    }

    @Test
    public void testPaint_DibujaTodo() {
        Arkanoid arkaSpy = Mockito.spy(new Arkanoid());

        Image imageMock = Mockito.mock(Image.class);
        Graphics gMock = Mockito.mock(Graphics.class); // g
        Graphics gNoseveMock = Mockito.mock(Graphics.class); // noseve

        doReturn(imageMock).when(arkaSpy).createImage(anyInt(), anyInt());
        when(imageMock.getGraphics()).thenReturn(gNoseveMock);

        arkaSpy.init();

        arkaSpy.puntuacion = 6500; // Entra en el if de ENHORABUENA
        arkaSpy.numVidas = 0; // Entra en el if de GAME OVER

        arkaSpy.paint(gMock);

        verify(gNoseveMock).fillRect(0,0,400,400); // Fondo
        verify(gNoseveMock).drawString(contains("ENHORABUENA"),anyInt(),anyInt()); // "ENHORABUENA CRRRRRACK", 60, 100
        verify(gNoseveMock).drawString(contains("GAME OVER! LOOSER"),anyInt(),anyInt()); // "GAME OVER! LOOSER", 80, 100
    }

    @Test
    public void testMouseDown_ReiniciaJuego() {
        Arkanoid arkaSpy = Mockito.spy(new Arkanoid());

        Image imagenMock = Mockito.mock(Image.class);
        Graphics gMock = Mockito.mock(Graphics.class);

        // Llama a init
        doReturn(imagenMock).when(arkaSpy).createImage(anyInt(), anyInt());
        when(imagenMock.getGraphics()).thenReturn(gMock);

        arkaSpy.pelota = new Pelota(0,0,0,0,Color.BLUE);

        arkaSpy.numVidas = 0;
        arkaSpy.puntuacion = 6500;

        arkaSpy.mouseDown(null, 0,0);

        assertEquals(5, arkaSpy.numVidas," Al hacer click en Game Over, las vidas deben devolver 5");
        assertEquals(5, arkaSpy.numVidas,"Al llegar a 6500 puntos las vidas deben devolver 5");
    }

    @Test
    public void testGetVidasSetVidas() {
        Arkanoid arkanoid = new Arkanoid();
        Bloque[] vidas = new Bloque[3];

        arkanoid.setVidas(vidas);

        // Verifica que no es null
        assertNotNull(arkanoid.getVidas());
        // Verifica el contenido del array
        assertArrayEquals(vidas, arkanoid.getVidas());
    }

    @Test
    public void testRun_RomperLadrillo() {
        Arkanoid arkaSpy = Mockito.spy(new Arkanoid());
        Image imageMock = Mockito.mock(Image.class);
        Graphics gMock = Mockito.mock(Graphics.class);

        // Crear la imagen
        doReturn(imageMock).when(arkaSpy).createImage(anyInt(), anyInt());
        when(imageMock.getGraphics()).thenReturn(gMock);

        // Inicializar
        arkaSpy.init();

        // Colocar la pelota
        // Ladrillo [0][0] está en x = 0, y = 10
        arkaSpy.pelota.x = 5;
        arkaSpy.pelota.y = 15;

        // El ladrillo [0][0] es AZUL (colores[0] y suma 160 puntos

        doThrow(new RuntimeException("STOP_TEST")).when(arkaSpy).repaint();

        // Ejectar el run dentro de un try-catch
        try{
            arkaSpy.run();
        } catch (RuntimeException e){
            assertEquals("STOP_TEST",e.getMessage());
        }

        // Verificaciones
        // El ladrillo no debe de ser visible
        assertFalse(arkaSpy.bloques[0][0].isVisible(),"El bloque golpeado desaparece");

        // la puntuación debe de haber subido 160 puntos
        assertEquals(160, arkaSpy.puntuacion, "La puntuación debe de sumar 160 al romper el bloque");
    }

    @Test
    public void testRun_PerderVida() {
        Arkanoid arkaSpy = Mockito.spy(new Arkanoid());
        Image imageMock = Mockito.mock(Image.class);
        Graphics gMock = Mockito.mock(Graphics.class);

        // Crear a imagen
        doReturn(imageMock).when(arkaSpy).createImage(anyInt(), anyInt());
        when(imageMock.getGraphics()).thenReturn(gMock);

        // Inicializar
        arkaSpy.init();

        // Pelota cae al vacio (SCREEN_LIMIT = 300)
        arkaSpy.pelota.y = 350;

        assertEquals(5, arkaSpy.numVidas, " Inicialmente tenemos 5 vidas");

        doThrow(new RuntimeException("STOP_TEST")).when(arkaSpy).repaint();

        try {
            arkaSpy.run();
        } catch (RuntimeException e) {
            assertEquals("STOP_TEST", e.getMessage());
        }

        // Verificar vidas y posición de la pelota
        assertEquals(4, arkaSpy.numVidas, "Debería haber bajado 1 vida");
        assertEquals(195, arkaSpy.pelota.y,"La pelota debería haberse reseteado a Y=195");

        // Verificamos que la vida gráfica (el bloque rojo de la derecha) se ocultó
        // El índice de la vida perdida era el 4 (porque bajó de 5 a 4)
        assertFalse(arkaSpy.vidas[4].isVisible(), "El indicador de vidas debe desaparecer");
    }

    @Test
    public void testGameOver_Highscore() {
        Arkanoid game = spy(new Arkanoid());
        Image imageMock = mock(Image.class);
        Graphics gMock = mock(Graphics.class);
        doReturn(imageMock).when(game).createImage(anyInt(), anyInt());
        when(imageMock.getGraphics()).thenReturn(gMock);
        doThrow(new RuntimeException("STOP")).when(game).repaint();

        game.init();

        // PREPARACIÓN:
        game.numVidas = 1;       // Le queda 1 vida
        game.pelota.y = 500;     // Pelota fuera de pantalla (muerte)
        game.puntuacion = 9999;  // Puntuación actual alta
        game.puntuacionMax = 0;  // Record anterior bajo

        // EJECUCIÓN
        try { game.run(); } catch (RuntimeException e) {}

        // VERIFICACIÓN
        assertEquals(0, game.numVidas, "Debe llegar a 0 vidas");
        assertEquals(9999, game.puntuacionMax, "Debe haber actualizado el record");
        assertEquals(0, game.puntuacion, "La puntuación se resetea a 0");
    }
}
