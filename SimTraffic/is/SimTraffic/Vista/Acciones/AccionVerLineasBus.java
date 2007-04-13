package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * @author usuario_local
 * En construccion
 */

public class AccionVerLineasBus implements ActionListener{

	IControlador controlador;

	IModelo modelo;
	
	JFrame ventana;
	
	JPanel ventanaLineasBus;

	public AccionVerLineasBus(IControlador controlador, IModelo modelo,JFrame ventana) {
		super();
		this.controlador = controlador;
		this.modelo = modelo;
		this.ventana=ventana;
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		 //Auxiliar
		 JFrame ventana2 =new JFrame();
		 ventana2.setSize(200,300);
		 ventana2.setTitle("Lineas de Autobuses");
		 
		
		
		 //ventanaLineasBus = new JPanel();
		 //this.ventana.getContentPane().add(ventanaLineasBus, BorderLayout.EAST);
		 Point esquinaIzq=this.ventana.getLocationOnScreen();
		 int XVentana=(int)esquinaIzq.getX()+this.ventana.getWidth()-323;
		 int YVentana=(int)esquinaIzq.getY()+this.ventana.getHeight()-666;
		 //ventanaLineasBus.setBounds(XVentana,YVentana,300,400);
		 //ventanaLineasBus.setVisible(true);
		 ventana2.add(poneLineasEnLista());//,BorderLayout.CENTER);
		 ventana2.setVisible(true);
		 
		 
		// Donde va a aparecer la ventana son las dos primera componentes
		// El tamaño de la ventana son las dos ultimas componentes
		 

	}
	
	private JList poneLineasEnLista(){
		ArrayList<Via> lista=modelo.getMapa().getLineasAutobuses();
		DefaultListModel modelo = new DefaultListModel();
		Iterator<Via> it = lista.iterator();
		while (it.hasNext()){
		 modelo.addElement(it.next().getNombre());
		}
		JList lineasBus = new JList(modelo);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		lineasBus.setBorder(blackline);
		//ListSelectionListener seleccion = new SeleccionarLista();
		//lineasBus.addListSelectionListener();
		return lineasBus;
	}
	
	
	
	
	

	
}
