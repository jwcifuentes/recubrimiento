/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependenciasfuncionales;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jcifuentesz
 */
public class BotonDeImagen extends JButton{
    
    // Imagen para el boton en estado normal
    Image img;
    // Imagen para el botón en estado armed o presionado
    Image imgArmed;
    // Imagen para el botón cuando el puntero del mouse esta dentro del área botón
    Image imgOver;
    public BotonDeImagen(String archivoImagen, String imagenArmed, String imagenOver) {
        super();
        // Creamos las imagenes
        ImageIcon imgIcon = new ImageIcon(archivoImagen);
        img = imgIcon.getImage();
        imgArmed = new ImageIcon(imagenArmed).getImage();
        imgOver = new ImageIcon(imagenOver).getImage();
        // Establecemos el tamaño del boton de acuerdo al tamaño de la imagen
        Dimension size = new Dimension(imgIcon.getIconWidth(),imgIcon.getIconHeight());
        setPreferredSize(size);
        // Esto evita que el fondo por defecto del boton sea dibujado
        setContentAreaFilled(false);
        // Esto evita que se dibuje el borde
        setBorder(null);
    }
    // Metodo para dibujar la apariencia del boton
    protected void paintComponent(Graphics g) {
        // llamamos al metodo de la clase padre
        super.paintComponent(g);
        // De esta forma sabemos si el boton se encuentra en estado "Armed"
        // Osea que esta siendo presionado
        if (getModel().isArmed()) {
            // Dibujamos la imagen para el estado "Armed"
            g.drawImage(imgArmed,0,0,getWidth(),getHeight(),this);
        }
        // De esta forma sabemos si el boton se encuentra en estao "RollOver"
        // Osea que el puntero del mouse se encuentra dentro del area del boton
        else if ( getModel().isRollover() ){
        // Dibujamos la imagen para el estado "Rollover"
                g.drawImage(imgOver,0,0,getWidth(),getHeight(),this);
            }
        // Sino dibujamos el estado normal
        else {
        // Dibujamos la imagen para el estado normal
            g.drawImage(img,0,0,getWidth(),getHeight(),this);
        }
    }
// Metodo main para probar nuestra clase
public static void main(String[] args) {
//Creamos un boton indicando las 3 imagenes
JButton button = new BotonDeImagen(
"C:\\Users\\jcifuentesz\\Documents\\NetBeansProjects\\DependenciasFuncionales\\src\\resoruces\\btn1.png",
"C:\\Users\\jcifuentesz\\Documents\\NetBeansProjects\\DependenciasFuncionales\\src\\resoruces\\btn2.png",
"C:\\Users\\jcifuentesz\\Documents\\NetBeansProjects\\DependenciasFuncionales\\src\\resoruces\\btn3.png");
// Creamos una ventana en la cual se muestra el boton.
JFrame frame = new JFrame();
button.setText("TEX");
frame.getContentPane().add(button);
frame.getContentPane().setLayout(new FlowLayout());
frame.setSize(400, 400);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
    
}
