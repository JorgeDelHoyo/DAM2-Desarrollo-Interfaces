package org.example;


import java.awt.Color;
import java.awt.Graphics;

public class Bloque extends Shape {
    private boolean visible;

    public Bloque(int posX, int posY, int anchura, int altura, Color color) {
        super(posX, posY, anchura, altura, color);
        setVisible(true);
    }
    public void dibujar(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, width, height);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
