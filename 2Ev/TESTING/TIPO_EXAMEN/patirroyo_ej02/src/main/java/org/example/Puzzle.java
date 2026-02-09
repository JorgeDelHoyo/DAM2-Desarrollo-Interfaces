package org.example;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import util.ResourceLoader;

/**
 *
 * @author alberto
 */
public class Puzzle extends JPanel{
    public static final int ROWS = 5;
    public static final int COLUMNS = 5;
    public static final int PIECES = 25;
    private Image imagen;
    private Graphics noseve;
    public final static int SIZEX = 1000;
    public final static int SIZEY = 600;
    private Image imagenes[];
    private Pieza piezas[];
    private Pieza actual;
    private int posActual;
    private int actualIniX;
    private int actualIniY;
    private Rectangle[] sitios;
    private Image referencia;

    public Puzzle(){
        init();
        configurarEventos();
    }

    public void init(){
        setPreferredSize(new Dimension(SIZEX, SIZEY));
        setFocusable(true);
        imagen = new BufferedImage(SIZEX, SIZEY, BufferedImage.TYPE_INT_ARGB);
        noseve = imagen.getGraphics();
        //instanciamos las 25 imágnes y las 25 piezas que se alimentaran las imagenes
        imagenes = new Image[PIECES];
        piezas = new Pieza[PIECES];

        //cargamos la imágenes y se las asignamos a las piezas con el método constructor.
        for(int i = 0; i < PIECES; i++){
            imagenes[i] = ResourceLoader.loadImage("Ejercicio02/directorioImagenes/" + (i+1) + ".png");
            //en este ejemplo no hace falta el segundo atributo, pero así funcionan las dos clases
            piezas[i] = new Pieza(imagenes[i], i);

        }

        //creamos los lugares donde tienen que ir las piezas
        sitios = new Rectangle[PIECES];
        //les damos el lugar correcto
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++)
                sitios[i*ROWS + j] = new Rectangle(j*Pieza.SIZE + 100, i*Pieza.SIZE + 150, Pieza.SIZE, Pieza.SIZE);
        }

        referencia = ResourceLoader.loadImage("Ejercicio02/directorioImagenes/mapamundi3.png");
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, SIZEX, SIZEY);
        //pintamos una imagen de referencia para ayudar
        noseve.drawImage(referencia, 100, 150, this);

        //pintamos las piezas y la cuadricula donde tienen que ir.
        for(int i = 0; i < PIECES; i++){
            piezas[i].paint(noseve, this);
            noseve.setColor(Color.YELLOW);
            noseve.drawRect(sitios[i].x, sitios[i].y, sitios[i].width, sitios[i].height);
        }

        g.drawImage(imagen, 0, 0, SIZEX, SIZEY, this);
    }

    private void configurarEventos(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for(int i = 0; i < PIECES; i++)
                    if(piezas[i].contains(x, y)){
                        actual = piezas[i];
                        posActual = i;
                        actualIniX = actual.x;
                        actualIniY = actual.y;
                        break;
                    }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(actual != null){
                    if(actual.intersects(sitios[posActual])){
                        actual.x = sitios[posActual].x;
                        actual.y = sitios[posActual].y;
                        actual.setOk();
                    } else{
                        actual.setX(actualIniX);
                        actual.setY(actualIniY);
                    }
                    repaint();
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(actual != null){
                    actual.update(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejercicio02 - Puzzle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Puzzle());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
