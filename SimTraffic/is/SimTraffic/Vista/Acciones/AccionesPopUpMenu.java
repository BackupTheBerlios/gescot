package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;

public class AccionesPopUpMenu implements ActionListener {

		protected IModelo modelo;
		protected PanelMapa panel_mapa;
		protected IControlador controlador;
		
		public AccionesPopUpMenu(IModelo modelo, IControlador controlador, PanelMapa panel_mapa) {
			this.modelo = modelo;
			this.panel_mapa = panel_mapa;
			this.controlador = controlador;
		}

		public void actionPerformed(ActionEvent arg0) {
	}
		
		public Nodo buscarNodo(int posX, int posY) {
			double error = 3;
			Iterator<Nodo> iter = modelo.getMapa().getNodos().iterator();
			Nodo sel = null;
			boolean encontrado = false;
			while (!encontrado && iter.hasNext()) {
				Nodo next = iter.next();
				int nodox = panel_mapa.getRepresentacion().x_MapaARep(
						next.getPos().getLon());
				int nodoy = panel_mapa.getRepresentacion().y_MapaARep(
						next.getPos().getLat());
				if ((nodoy - error <= posY) && (nodoy + error >= posY)
						&& (nodox - error <= posX) && (nodox + error >= posX)) {
					encontrado = true;
					sel = next;
				}
			}
			if (encontrado)
				return sel;
			else
				return null;
		}
		
		/**
		 * Método para obtener un tramo a partir de un punto en el panel del mapa.
		 * <p>
		 * Este método recorre la lista de tramos y busca el tramo que tiene una
		 * posicion (x,y) similar a la pasada como parámetro.
		 * 
		 * @param x
		 *            Posicion a lo largo del eje x (coordenadas del panel)
		 * @param y
		 *            Posicion a lo largo del eje y (coordenadas del panel)
		 * @return Tramo encontrado en la posicion dada o null
		 */
		public Tramo buscarTramo(int posX, int posY) {
			Iterator<Tramo> iter = modelo.getMapa().getTramos().iterator();
			Tramo sel = null;
			boolean encontrado = false;
			while (!encontrado && iter.hasNext()) {
				Tramo next = iter.next();
				Polygon p = panel_mapa.getRepresentacion().generarAreaTramo(next);
				if (p.contains(posX, posY)) {
					encontrado = true;
					sel = next;
				}
			}
			if (encontrado)
				return sel;
			else
				return null;
		}
		

}
