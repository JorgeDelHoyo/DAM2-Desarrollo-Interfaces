package org.example;

import java.awt.Color;
import java.awt.Rectangle;

public class Shape extends Rectangle {

    private Color color;

    public Shape(int posX, int posY, int anchura, int altura, Color color) {
        super(posX, posY, anchura, altura);
        this.setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}