import org.example.RegistroUsuario;
import org.junit.jupiter.api.Test;

import java.sql.Ref;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroUsuarioTest {

    private final RegistroUsuario usuario = new RegistroUsuario();

    @Test
    public void testLimpiarNombreNull() {
        String nombre = usuario.limpiarNombre(null);

        assertEquals("",nombre,"Si el nombre es null devuelve \"\"");
    }

    @Test
    public void testLimpiarNombreNotNull() {
        String nombre = usuario.limpiarNombre("Jacobo");

        assertEquals("JACOBO", nombre, "El nombre es correcto");
    }

    @Test
    public void testCalcularNivelSeguridadNull() {
        int nivel = usuario.calcularNivelSeguridad(null);

        assertEquals(0,nivel, "Si la contraseña es null devuelve 0");
    }

    @Test
    public void  testCalcularNivelSeguridadMinimo() {
        int nivel = usuario.calcularNivelSeguridad("abcdef");

        assertEquals(0,nivel, "No cumple ningún requisito");
    }

    @Test
    public void testcalcularNivelSeguridadLength8() {
        int nivel = usuario.calcularNivelSeguridad("qwertyui");

        assertEquals(5,nivel,"Longitud > 8 suma 5 puntos");
    }

    @Test
    public void testCalcularNivelSeguridadContainsFirstCharacter() {
        int nivel = usuario.calcularNivelSeguridad("yui@");

        assertEquals(3,nivel, "Si tiene un caracter de los dos, suma 3 puntos");
    }

    @Test
    public void testCalcularNivelSeguridadContainsSecondCharacter() {
        int nivel = usuario.calcularNivelSeguridad("yui#");

        assertEquals(3,nivel, "Si tiene un caracter de los dos, suma 3 puntos");
    }

    @Test
    public void testCalcularNivelSeguridadContainsDigits() {
        int nivel = usuario.calcularNivelSeguridad("1234");

        assertEquals(2, nivel, "Si hay numero, 2 puntos");
    }

    @Test
    public void testCalcularNivelSeguridadCompleto() {
        int nivel = usuario.calcularNivelSeguridad("abcdefg@1233#34");

        assertEquals(10, nivel, "Si todo es correcto, 10 puntos");
    }

    /**
     * Código Gemini (Preguntar Gorka)
    @Test
    public void testCalcularNivelSeguridad() {
        assertAll("Niveles de seguridad",
                () -> assertEquals(0, usuario.calcularNivelSeguridad(null)),
                () -> assertEquals(0, usuario.calcularNivelSeguridad("abc")),
                () -> assertEquals(5, usuario.calcularNivelSeguridad("qwertyui")), // Largo
                () -> assertEquals(3, usuario.calcularNivelSeguridad("@#")),      // Especiales
                () -> assertEquals(2, usuario.calcularNivelSeguridad("123")),    // Dígitos
                () -> assertEquals(10, usuario.calcularNivelSeguridad("pass@123")) // Completo
        );
    }
     */

    @Test
    public void testEsRegistroValidoUsuarioNull(){
        boolean esValido = usuario.esRegistroValido(null,"12345678");

        assertFalse(esValido, "Si el usuario es null devuelve false");
    }

    @Test
    public void testEsRegistroValidoUsuarioLengthInvalido() {
        boolean esValido = usuario.esRegistroValido("aa","12345678");

        assertFalse(esValido, "Si el usuario tiene longitud < 3 devuelve false");
    }

    @Test
    public void testEsRegistroValidoNivelInvalido() {
        boolean esValido = usuario.esRegistroValido("abcdefg","12");

        assertFalse(esValido, "El nivel es menor que 5 entonces no es valido");
    }

    @Test
    public void testEsRegistroValidoTodoValido() {
        boolean esValido = usuario.esRegistroValido("abcdefg","12347865");

        assertTrue(esValido, "Usuario y nivel validos");
    }
}
