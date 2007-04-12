package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;


import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AccionVerLineasBus implements ActionListener{

	IControlador controlador;

	IModelo modelo;
	
	JFrame ventana;
	
	JPanel ventanaLineasBus;

	public AccionVerLineasBus(IControlador controlador, IModelo modelo,JFrame ventana) {
		super();
		this.controlador = controlador;
		this.modelo = modelo;
		this.ventana =ventana;
	}

	public void actionPerformed(ActionEvent arg0) {
		 ventanaLineasBus = new JPanel();
		 this.ventana.getContentPane().add(ventanaLineasBus, BorderLayout.EAST);
		 Point esquinaIzq=this.ventana.getLocationOnScreen();
		 int XVentana=(int)esquinaIzq.getX()+this.ventana.getWidth()-323;
		 int YVentana=(int)esquinaIzq.getY()+this.ventana.getHeight()-666;
		 ventanaLineasBus.setBounds(XVentana,YVentana,300,400);
		 ventanaLineasBus.setVisible(true);
		// Donde va a aparecer la ventana son las dos primera componentes
		// El tamaño de la ventana son las dos ultimas componentes
		 

	}
	
}
