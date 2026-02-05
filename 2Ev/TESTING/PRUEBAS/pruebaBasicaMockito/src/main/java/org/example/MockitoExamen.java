package org.example;

public class MockitoExamen {
    public void mockitoEj1(Validador validador, String paquete, ServicioEnvio envio) {
        envio.enviar(paquete);

        if (validador.obtenerCodigoEstado() == 0) {
            return;
        } else {
            throw new RuntimeException("Error en la validación");
        }
    }
}