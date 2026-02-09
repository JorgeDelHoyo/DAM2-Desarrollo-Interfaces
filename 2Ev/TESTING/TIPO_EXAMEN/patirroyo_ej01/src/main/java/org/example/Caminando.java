package org.example;

import util.ResourceLoader;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author jorge_sk8
 */

public class Caminando extends JPanel implements Runnable {
    public static final int ROWS = 3;
    public static final int COLUMNS = 4;
    private int delay = 200;
    private Thread animacion;
    public Image imagen;
    public Graphics noseve;
    public final static int SIZEX = 600;
    public final static int SIZEY = 600;
    public Image img;
    public Image[][] imagenes;
    public String elementos[] = {"Guerrillero/g", "Hampon/h", "Vaquero/v"};
    public DibujoAnimado personaje;//declaro un dibujo animado para mostrarlo, reservo memoria.




    public Caminando(){
        init();
        start();
        configurarEventos();
    }

    public void init(){
        setPreferredSize(new Dimension(SIZEX, SIZEY));
        setFocusable(true);
        imagen = new BufferedImage(SIZEX, SIZEY, BufferedImage.TYPE_INT_ARGB);
        noseve = imagen.getGraphics();

        img = ResourceLoader.loadImage("Ejercicio01/Sprites/Guerrillero/g1.gif");
        /*este método devuelve algo, una imagen
        primero se le da el path (en ese caso usamos getCodeBase() para
        obtener el path), lo mete en una dirección de memoria ram y con el
        getImage accedemos a él.
        */
        imagenes = new Image[ROWS][COLUMNS];

        //Cargamos las imágenes en el array bidimensional.
        for(int i = 0; i < ROWS; i++)
            for(int j = 0; j < COLUMNS; j++)
                imagenes[i][j] = ResourceLoader.loadImage("Ejercicio01/Sprites/" + elementos[i] + (j+1) + ".gif");
        //instancio el personaje.
        personaje = new DibujoAnimado(imagenes[0]);
    }
    public void start(){
        animacion = new Thread(this);
        animacion.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, SIZEX, SIZEY);

        personaje.paint(noseve, this);

        g.drawImage(imagen, 0, 0, SIZEX, SIZEY, this);
    }

    public void run(){
        while(true){
            personaje.update();
            repaint();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex){
                System.out.println("Error en el hilo");
            }
        }
    }
    private void configurarEventos(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();
                switch (tecla){
                    case KeyEvent.VK_G:
                        personaje.setImagenes(imagenes[0]);
                        break;
                    case KeyEvent.VK_H:
                        personaje.setImagenes(imagenes[1]);
                        break;
                    case KeyEvent.VK_V:
                        personaje.setImagenes(imagenes[2]);
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejercicio01 - Caminando");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Caminando panel = new Caminando();
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            panel.requestFocusInWindow();
        });
    }
}