package PrototipoEditor.simuladortrafico.is.editor;

import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class VentanaAux extends JFrame {

	//Valores de la ventana
	private static final long serialVersionUID = 1L;
	static int ancho = 250;
	static int alto = 150;
	JPanel panel;
	JLabel etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5, etiqueta6;
	
	//Valores que ha de representar
	int coordX, coordY;
	
	//Controlador que gestiona la ventana
	Controlador controlador;
	
	public VentanaAux(Controlador controlador){
		this.controlador = controlador;
		
		//Creamos los componentes de la ventana auxiliar
		panel = new JPanel();
		etiqueta1 = new JLabel("Pos X");
		etiqueta2 = new JLabel("0");
		etiqueta3 = new JLabel("Pos Y");
		etiqueta4 = new JLabel("0");
		etiqueta5 = new JLabel("Botón pulsado");
		etiqueta6 = new JLabel("Seleccionar");
		
		etiqueta6.setOpaque(true);
		etiqueta6.setBackground(Color.RED);
		panel.add(etiqueta1);panel.add(etiqueta2);panel.add(etiqueta3);
		panel.add(etiqueta4);panel.add(etiqueta5);panel.add(etiqueta6);
		this.getContentPane().add(panel);	
		
		// Create and set up the window.
		//JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("Ventana Auxiliar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ancho, alto);
		this.setLocation(500,0);
		this.setVisible(true);
	}
	
	public void actualizarPuntoPulsadoInfo(int nuevoX, int nuevoY) {
		this.coordX = nuevoX;
		this.coordY = nuevoY;
		etiqueta2.setText(Integer.toString(nuevoX));
		etiqueta4.setText(Integer.toString(nuevoY));
	}
	
	public void actualizarHerramientaSeleccionada(String string) {
		etiqueta6.setText(string);		
	}
	
	public void actualizarBotonPulsado(String string) {
		etiqueta5.setText(string);		
	}
	
	public static void main(String[] args) {
		Controlador temp = new Controlador();
		VentanaAux prueba = new VentanaAux(temp);
		prueba.actualizarPuntoPulsadoInfo(12,45);
	}


	
}
