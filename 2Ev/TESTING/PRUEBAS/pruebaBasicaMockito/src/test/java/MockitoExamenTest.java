import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.MockitoExamen;
import org.example.ServicioEnvio;
import org.example.Validador;
import org.junit.jupiter.api.Test;

public class MockitoExamenTest {

    @Test
    public void testCuandoEstadoNoEsCeroLanzaException() {
        // 1. Mocks
        Validador vMock = mock(Validador.class);
        ServicioEnvio sMock = mock(ServicioEnvio.class);

        // 2. Configurar comportamiento fallo
        when(vMock.obtenerCodigoEstado()).thenReturn(1); // Si no es 0 return

        MockitoExamen examen = new MockitoExamen();

        // 3. Verificar que se lanza la excepcion
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            examen.mockitoEj1(vMock,"PaqueteError",sMock);
        });
    }

    @Test
    public void testCuandoEstadoEsCeroVuelve() {
        // 1. Mocks
        Validador vMock = mock(Validador.class);
        ServicioEnvio sMock = mock(ServicioEnvio.class);

        // 2. Configuración que devuelva 0
        when(vMock.obtenerCodigoEstado()).thenReturn(0);

        MockitoExamen examen = new MockitoExamen();

        // 3. Ejecución método(No debe lanzar ninguna excepcion)
        examen.mockitoEj1(vMock,"PaqueteOk",sMock);

        // 4. Verificación: ¿Se llamó al envío a pesar de ser estado 0?
        // Esto es "chicha" de examen: comprobar interacciones.
        verify(sMock).enviar("PaqueteOk");

        // Verificamos que se consultó el estado exactamente una vez
        verify(vMock, times(1)).obtenerCodigoEstado();
    }
}