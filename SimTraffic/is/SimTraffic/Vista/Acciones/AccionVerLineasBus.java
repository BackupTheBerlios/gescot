package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.LineaBus;
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
	JPanel ventanaLineasBus;
	JPanel panel_mapa;
	List lineasBus;

	public AccionVerLineasBus(IControlador controlador, IModelo modelo,JFrame ventana,JPanel panel_mapa) {
		super();
		this.controlador = controlador;
		this.modelo = modelo;
		this.panel_mapa=panel_mapa;
		poneLineasEnLista();
	}

	public void actionPerformed(ActionEvent arg0) {
		
		 
		 ventanaLineasBus = new JPanel(new BorderLayout());
		 
		 
		 JLabel iconoBus = new JLabel((new ImageIcon(
		 			"is\\SimTraffic\\Vista\\Imagenes\\autobus.jpg" )));
		 JPanel imagenBus =new JPanel();
		 imagenBus.setLayout(new BorderLayout());
		 imagenBus.add(iconoBus);
		 ventanaLineasBus.add(imagenBus,BorderLayout.WEST); 
		 JScrollPane panel_lista =new JScrollPane(lineasBus);
		 panel_lista.setBorder(BorderFactory.createEmptyBorder(20, 10, 150, 20));
		 JPanel titulo =new JPanel();
		 JLabel label=new JLabel("Lineas de autobus");
		 label.setFont(new Font("serif", Font.BOLD, 15));
		 titulo.add(label);
		 ventanaLineasBus.add(titulo,BorderLayout.NORTH);
		 ventanaLineasBus.add(panel_lista,BorderLayout.CENTER);
		 ventanaLineasBus.setBorder(BorderFactory.createRaisedBevelBorder());
		 JButton boton_salir= new JButton("Salir");
		 boton_salir.addActionListener(new accionSalir());
		 JButton boton_buscar=new JButton("Buscar Linea");
		 boton_buscar.addActionListener(new accionBuscar());
		 JPanel botones=new JPanel(new BorderLayout());
		 botones.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		 botones.add(boton_buscar,BorderLayout.NORTH);
		 botones.add(boton_salir,BorderLayout.SOUTH);
		 ventanaLineasBus.add(botones,BorderLayout.SOUTH);
		 panel_mapa.add(ventanaLineasBus,BorderLayout.WEST);
		 panel_mapa.repaint();

	}
	
	private void poneLineasEnLista(){
		ArrayList<LineaBus> lista=modelo.getMapa().getLineasAutobuses();
		 lineasBus = new List();
		Iterator<LineaBus> it = lista.iterator();
		while (it.hasNext()){
		 lineasBus.add(it.next().getNombre());
		}
	}
	
	private class accionSalir implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			panel_mapa.remove(ventanaLineasBus);
			ventanaLineasBus=null;
			panel_mapa.repaint();
		}
	}
	
	private class accionBuscar implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<LineaBus> lista=modelo.getMapa().getLineasAutobuses();
			if (!lista.isEmpty()){
			LineaBus seleccionada = lista.get(lineasBus.getSelectedIndex());
			Iterator tramos = seleccionada.getTramos().iterator();
			Iterator paradas = seleccionada.getParadas().iterator();
			paradas.next();
			  while(tramos.hasNext()){
			   Tramo aux = (Tramo)tramos.next();
			   Boolean parada = (Boolean)paradas.next();
			   //A�adimos cada uno de los tramos	
			   modelo.getMapa().getSeleccion().a�adirTramo(aux);
			   //A�adimos cada uno de los nodos que une cada tramo de la via
			   modelo.getMapa().getSeleccion().a�adirNodo(aux.getNodoInicial());
			   //Si hay una parada la a�adimos a la seleccion
			   if (parada)
			   modelo.getMapa().getSeleccion().a�adirNodo(aux.getNodoFinal());
			 }
			}
		}
	}

	

	
}
