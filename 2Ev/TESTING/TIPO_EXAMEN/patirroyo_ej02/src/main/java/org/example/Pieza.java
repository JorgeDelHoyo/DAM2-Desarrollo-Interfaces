package org.example;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author jorge_sk8
 */
public class Pieza extends Rectangle{
    private Image image;
    public static final int SIZE = 60;
    private boolean ok = false;
    private int posicion;

    public Pieza(Image imagen, int pos){
        super((int)(Math.random()*400)+500, (int)(Math.random()*450)+50, SIZE, SIZE);
        this.image = imagen;
        posicion = pos;

    }

    public void paint(Graphics g, Component observer){
        g.drawImage(image, x, y, observer);
    }
    public void update(int x, int y){
        if(!ok){
            this.x = x - SIZE/2;
            this.y = y - SIZE/2;
        }
    }

    public void setOk() {
        this.ok = true;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPosicion() {
        return posicion;
    }

}