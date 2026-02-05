package org.example;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.activation.FileDataSource;

public class Arkanoid extends Applet implements Runnable{
    private static final int SCREEN_LIMIT_Y = 300;
    int numVidas = 5;
    int puntuacionMax;
    Bloque[] vidas = new Bloque[numVidas];
    int puntuacion = 0;
    Image imagen;
    Graphics noseve;
    Thread animacion;
    Bloque[][] bloques = new Bloque[10][5];
    Pelota pelota;
    Bloque raqueta;
    Color colores[] = {Color.blue, Color.orange, Color.green, Color.magenta, Color.red};
    int ratX, ratY;

    public void init() {
        setSize(300,300);
        imagen = this.createImage(300,300);  //es de la clase applet
        noseve = imagen.getGraphics();
        pelota = new Pelota(150, 190, 15, 15, Color.white);
        raqueta = new Bloque(ratX, 210, 50, 5, Color.white);
        for(int f = 0; f < bloques.length; f++ )
            for (int c = 0; c < bloques[f].length; c++)
                bloques[f][c] = new Bloque(f*30, c*12+10, 28, 10, colores[c]);
        for(int i = 0; i < vidas.length; i++ )
            vidas[i] = new Bloque(270+(4*i), 260, 2, 10, Color.red);
    }

    public void paint(Graphics g) {
        noseve.setColor(Color.black);
        noseve.fillRect(0, 0, 400, 400);
        pelota.dibujar(noseve);
        raqueta.dibujar(noseve);
        noseve.setColor(Color.WHITE);
        noseve.drawLine(0, 230, 300, 230);
        for(int f = 0; f < bloques.length; f++ )
            for (int c = 0; c < bloques[f].length; c++) {
                if(bloques[f][c].isVisible())
                    bloques[f][c].dibujar(noseve);
            }
        noseve.setColor(Color.red);
        for(int i = 0; i < vidas.length; i++ )
            if(vidas[i].isVisible())
                vidas[i].dibujar(noseve);
        noseve.drawString("Puntuacion: " + puntuacion, 10, 270);
        if(puntuacion == 6500) {
            noseve.setColor(Color.GREEN);
            if(puntuacion > puntuacionMax)
                puntuacionMax = puntuacion;
            noseve.drawString("ENHORABUENA CRRRRRRACK", 60, 100);
            pelota.setColor(Color.black);
        }
        noseve.drawString("Puntuaci�n M�xima: " + puntuacionMax, 120, 270);
        if(numVidas == 0) {
            noseve.setColor(Color.red);
            noseve.drawString("GAME OVER! LOOSER", 80, 100);
            pelota.setColor(Color.black);
        }
        g.drawImage(imagen, 0, 0, this);
    }


    public boolean update2(Graphics g) { // El metodo update se ejecuta al llamar repaint y consiste en borrar y pintar,
        g.create();
        Rectangle clipBounds = g.getClipBounds();
        return clipBounds.contains(100, 200);
    }

    public int readFile(File file) {
        if (file.canRead()) {
            FileDataSource fileInputStream = new FileDataSource(file);
            return fileInputStream.hashCode();
        } else {
            return 3000;
        }
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void run() {
        do {
            pelota.mover();
            raqueta.x = ratX;
            for(int f = 0; f < bloques.length; f++ )
                for (int c = 0; c < bloques[f].length; c++) {
                    if(bloques[f][c].contains(pelota.x, pelota.y) && bloques[f][c].isVisible() == true && bloques[f][c].getColor() == colores[4]) {
                        rebounce(f, c);
                        puntuacion += 100;
                    }
                    if(bloques[f][c].contains(pelota.x, pelota.y) && bloques[f][c].isVisible() == true && bloques[f][c].getColor() == colores[3]) {
                        rebounce(f, c);
                        puntuacion += 115;
                    }
                    if(bloques[f][c].contains(pelota.x, pelota.y) && bloques[f][c].isVisible() == true && bloques[f][c].getColor() == colores[2]) {
                        rebounce(f, c);
                        puntuacion += 130;
                    }
                    if(bloques[f][c].contains(pelota.x, pelota.y) && bloques[f][c].isVisible() == true && bloques[f][c].getColor() == colores[1]) {
                        rebounce(f, c);
                        puntuacion += 145;
                    }
                    if(bloques[f][c].contains(pelota.x, pelota.y) && bloques[f][c].isVisible() == true && bloques[f][c].getColor() == colores[0]) {
                        rebounce(f, c);
                        puntuacion += 160;
                    }
                }
            if(raqueta.intersects(pelota) && ratX+25 < pelota.x) {
                pelota.velY = -pelota.velY;
                pelota.velX = 2;
            }else if(raqueta.intersects(pelota) && ratX+25 == pelota.x) {
                pelota.velY = -pelota.velY;
                pelota.velX = 0;
            }
            else if(raqueta.intersects(pelota) && ratX+25 > pelota.x) {
                pelota.velY = -pelota.velY;
                pelota.velX = -2;
            }
            if (pelota.y > SCREEN_LIMIT_Y) {
                pelota.y = 195;
                pelota.x = 150;
                pelota.velX = 0;
                pelota.velY = 0;
                numVidas--;
                vidas[numVidas].setVisible(false);
            }if(numVidas == 0) {
                if(puntuacion > puntuacionMax)
                    puntuacionMax = puntuacion;
                puntuacion = 0;
            }
            repaint();
            try {
                Thread.sleep(35);
            }catch(InterruptedException e){}

        }while(true);
    }

    private void rebounce(int f, int c) {
        pelota.velY = -pelota.velY;
        bloques[f][c].setVisible(false);
    }

    public boolean mouseMove(Event e, int x, int y) {
        if(ratX<=290) {
            ratX = x-25;
            return true;
        } else {
            ratX = 289;
            return false;
        }
    }

    public boolean mouseDown(Event e, int x, int y) {
        if(pelota.velX == 0 && pelota.velY == 0) {
            pelota.velX = 0;
            pelota.velY = -6;
        }
        if (numVidas == 0) {
            init();
            numVidas = 5;
        }
        if (puntuacion == 6500) {
            init();
            numVidas = 5;
        }
        return false;
    }

    public Bloque[] getVidas() {
        return vidas;
    }

    public void setVidas(Bloque[] vidas) {
        this.vidas = vidas;
    }

}