/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import Multithreads.simuladortrafico.is.multithreads.mapa.Nodo;
import Multithreads.simuladortrafico.is.multithreads.mapa.Tramo;



/**
 * @author Grupo IS Tráfico
 *
 */
public class PanelMapa extends JPanel {
	List<Nodo> nodos;

	List<Tramo> tramos;

	List<Vehiculo> vehiculos;
	
	boolean inicial;

	int frame;
	
	long tiempo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PanelMapa(List<Nodo> nodos, List<Tramo> tramos, List<Vehiculo> vehiculos) {
		
		this.nodos = nodos;
		this.tramos = tramos;
		this.vehiculos = vehiculos;
		inicial = true;
		tiempo = System.currentTimeMillis();
	}
	

	private Image inueva;

	public void paintComponent(Graphics g) {
		    //super.paintComponent(g);
		
			Graphics2D g2d = (Graphics2D) g;
			
			if (inicial)
			{	
				//super.paintComponent(g);
				inueva = super.createImage(2000, 2000);
				Iterator<Tramo> it = tramos.iterator();
				
				while (it.hasNext()) {
					(it.next()).dibujar(inueva.getGraphics());
				}
				
				Iterator<Nodo> itn = nodos.iterator();
				
				while (itn.hasNext()) {
					(itn.next()).dibujar(inueva.getGraphics());
				}
				inicial = false;
			}
			g.drawImage(inueva, 0,0, null);
			
			Iterator<Vehiculo> itv = vehiculos.iterator();
			
			while (itv.hasNext()) {
				(itv.next()).dibujar2d(g2d);
			}
			frame++;
			if (frame == 20) tiempo = System.currentTimeMillis();
			g.setColor(Color.white);
			g.fillRect(70,70,55,30);
			g.setColor(Color.black);
			g.drawString("fps: " + ((frame - 20)*1000/(System.currentTimeMillis() - tiempo + 1)), 80, 80);
		
		}


	
}
