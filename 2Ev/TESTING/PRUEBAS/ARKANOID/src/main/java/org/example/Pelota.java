package org.example;


import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Shape {

    int velX, velY;

    public Pelota(int posX, int posY, int anchura, int altura, Color color) {
        super(posX, posY, anchura, altura, color);
        this.setColor(color);
    }

    public void dibujar(Graphics g) {
        g.setColor(getColor());
        g.fillOval(x, y, width, height);
    }

    public void mover() {
        x += velX;
        y += velY;
        if (x > GameConstants.SCREEN_LIMIT)
            velX = -velX;
        if(x < 0)
            velX = -velX;
        if(y < 0)
            velY = -velY;
    }
}
