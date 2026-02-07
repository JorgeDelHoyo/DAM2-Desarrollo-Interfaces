package org.example;

public class Junit {

    /*
     * JUNIT este metodo calcula el area de un triangulo
     */
    public static double getArea(double a, double b, double c) {
        double s = (a + b + c) / 2.0; // s = perimeter/2
        if (a + b <= c || b + c <= a || a + c <= b) {
            throw new RuntimeException();
        } else {
            double x = ((s) * (s - a) * (s - b) * (s - c)); // herons formula
            double area = Math.sqrt(x);
            return area;
        }
    }

    /*
     * JUNIT este método devuelve el tipo de triángulo dados tres lados
     */
    public static String triangle(int a, int b, int c) {
        if (a < b && b < c && (a * a) + (b * b) > (c * c)) {
            return "Triangulo isósceles";
        } else if (a < b && b < c && ((a * a) + (b * b) == (c * c))) {
            return "Triangulo equilatero";
        } else if (a < b && b < c && (a * a) + (b * b) < (c * c)) {
            return "Triangulo escaleno";
        }
        throw new RuntimeException();
    }

}
