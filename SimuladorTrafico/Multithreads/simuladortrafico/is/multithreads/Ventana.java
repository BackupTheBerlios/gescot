/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import javax.swing.*;
import Multithreads.simuladortrafico.is.multithreads.mapa.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

/**
 * @author Grupo IS Tráfico
 * 
 */
public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1048623260700875862L;

	List<Nodo> nodos;

	List<Tramo> tramos;

	List<Vehiculo> vehiculos;

	Graphics fondo;

	long tiempo;

	int frame;

	boolean inicial;

	boolean conpanel;

	public Ventana() {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(800, 650);

		// Display the window.
		// frame.pack();
		this.setVisible(true);
		inicial = true;
		frame = 0;
		tiempo = System.currentTimeMillis();
		this.setBackground(Color.white);
		conpanel = false;
	}

	public Ventana(List<Nodo> nodos, List<Tramo> tramos,
			List<Vehiculo> vehiculos) {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(800, 650);

		this.nodos = nodos;
		this.tramos = tramos;
		this.vehiculos = vehiculos;

		PanelMapa panel = new PanelMapa(nodos, tramos, vehiculos);
		panel.setPreferredSize(new Dimension(800, 650));

		this.add(panel);

		// Display the window.
		// frame.pack();
		this.setVisible(true);
		inicial = true;
		frame = 0;
		tiempo = System.currentTimeMillis();
		this.setBackground(Color.white);
		conpanel = true;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public void setTramos(List<Tramo> tramos) {
		this.tramos = tramos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	private Image mapa;
	
	public void paint(Graphics g) {
		if (conpanel) {
			if (inicial) {
				super.paint(g);
				inicial = false;
			}
			else
				super.paintComponents(g);
		} else {
			if (inicial) {
				//super.paint(g);
				mapa = super.createImage(2000, 2000);
				Iterator<Tramo> it = tramos.iterator();

				while (it.hasNext()) {
					(it.next()).dibujar(mapa.getGraphics());
				}

				Iterator<Nodo> itn = nodos.iterator();

				while (itn.hasNext()) {
					(itn.next()).dibujar(mapa.getGraphics());
				}
				inicial = false;
			}
			g.drawImage(mapa, 0, 0, null);

			Iterator<Vehiculo> itv = vehiculos.iterator();

			while (itv.hasNext()) {
				(itv.next()).dibujar(g);
			}
			frame++;
			if (frame == 20)
				tiempo = System.currentTimeMillis();
			g.setColor(Color.white);
			g.fillRect(70, 70, 55, 30);
			g.setColor(Color.black);
			g.drawString("fps: "
					+ ((frame - 20) * 1000 / (System.currentTimeMillis()
							- tiempo + 1)), 80, 80);
		}
	}

}