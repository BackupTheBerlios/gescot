package PrototipoEditor.simuladortrafico.is.editor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JFrame;

public class VentanaTools extends JFrame {

	//Valores de la ventana
	private static final long serialVersionUID = 1L;
	static int ancho = 150;
	static int alto = 200;
	JPanel panel;
	JButton boton1, boton2, boton3, boton4, boton5;
	
	//Valores que ha de representar
	int coordX, coordY;
	
	//Controlador que gestiona la ventana
	Controlador controlador;
	
	public VentanaTools(Controlador controlador){
		this.controlador = controlador;
		
		//Creamos los componentes de la ventana auxiliar
		panel = new JPanel();
		boton1 = new JButton("Select");
		boton1.setName("Select");
		boton2 = new JButton("Crear");
		boton2.setName("Crear");
		boton3 = new JButton("Borrar");
		boton3.setName("Borrar");
		boton4 = new JButton("Unir");
		boton4.setName("Unir");
		
		boton5 = new JButton("Exportar");
		boton5.setName("Exportar");
		
		
		ImageIcon iconoSelec = new ImageIcon("/Iconos/selec.jpg","Seleccionar");
		ImageIcon iconoCrear = new ImageIcon("/Iconos/crear.jpg","Crear");
		ImageIcon iconoBorrar = new ImageIcon("/Iconos/borrar.jpg","Borrar");
		ImageIcon iconoUnir = new ImageIcon("/Iconos/unir.jpg","Unir");
		boton1.setIcon(iconoSelec);boton2.setIcon(iconoCrear);
		boton3.setIcon(iconoBorrar);boton4.setIcon(iconoUnir);
		
		//Asignamos el oyente a los botones
		OyenteBoton oyenteBoton = new OyenteBoton();
		
		boton1.addActionListener(oyenteBoton);
		boton2.addActionListener(oyenteBoton);
		boton3.addActionListener(oyenteBoton);
		boton4.addActionListener(oyenteBoton);
		boton5.addActionListener(oyenteBoton);
		
		panel.add(boton1);panel.add(boton2);panel.add(boton3);
		panel.add(boton4);panel.add(boton5);
		this.getContentPane().add(panel);	
		
		// Create and set up the window.
		//JFrame.setDefaultLookAndFeelDecorated(true);
		this.setResizable(false);
		this.setTitle("Herramientas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ancho, alto);
		this.setLocation(500,160);
		this.setVisible(true);
		this.setBackground(Color.GRAY);
	}
	
	class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			JButton botonFuente = (JButton)arg0.getSource();
			
			if (botonFuente.getName()=="Select"){
				controlador.cambiarModo("1");
				controlador.ventanaAux.actualizarHerramientaSeleccionada("Seleccionar");
			} else if (botonFuente.getName()=="Crear") {
				controlador.cambiarModo("2");
				controlador.ventanaAux.actualizarHerramientaSeleccionada("Crear");
			} else if (botonFuente.getName()=="Borrar") {
				controlador.cambiarModo("3");
				controlador.ventanaAux.actualizarHerramientaSeleccionada("Borrar");
			} else if (botonFuente.getName()=="Unir") {
				controlador.cambiarModo("4");
				controlador.ventanaAux.actualizarHerramientaSeleccionada("Unir");
			} else if (botonFuente.getName()=="Exportar") {
				controlador.exportar();
			}
			
			
		}
		
	}
	
	public static void main(String[] args) {
		Controlador temp = new Controlador();
		VentanaTools prueba = new VentanaTools(temp);
	}


	
}
