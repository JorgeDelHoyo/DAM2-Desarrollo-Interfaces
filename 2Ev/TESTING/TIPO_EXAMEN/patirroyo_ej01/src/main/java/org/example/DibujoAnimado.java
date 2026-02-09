package org.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

/**
 * @author jorge_sk8
 */
public class DibujoAnimado {
    private int actual = 0;//indice para indicar la imagen que se está mostrando
    private static final int SIZEY = 300;
    private static final int SIZEX = 200;
    private Image imagenes[];


    public DibujoAnimado(Image imagenes[]){
        this.imagenes = imagenes;

    }
    public void paint(Graphics g, Component observer){
        //200 anchura, 300 altura
        g.drawImage(imagenes[actual], 0, 0, SIZEX, SIZEY, observer);

    }

    public void update(){
        actual = ++actual % imagenes.length;
    }

    public Image[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image[] imagenes) {
        this.imagenes = imagenes;
    }

}