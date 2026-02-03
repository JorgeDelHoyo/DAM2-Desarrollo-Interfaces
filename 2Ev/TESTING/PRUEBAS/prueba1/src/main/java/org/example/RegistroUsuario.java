package org.example;

public class RegistroUsuario {

    public String limpiarNombre(String nombre) {
        if (nombre == null) return "";
        return nombre.trim().toUpperCase();
    }

    public int calcularNivelSeguridad(String password) {
        if (password == null) return 0;

        int puntos = 0;
        if (password.length() >= 8) puntos += 5;
        if (password.contains("@") || password.contains("#")) puntos += 3;
        if (password.matches(".*\\d.*")) puntos += 2; // Contiene al menos un número

        return puntos;
    }

    public boolean esRegistroValido(String usuario, String pass) {
        // Un registro es válido si el usuario no es corto y la pass tiene nivel > 5
        return (usuario != null && usuario.length() > 3) && (calcularNivelSeguridad(pass) > 5);
    }
}
