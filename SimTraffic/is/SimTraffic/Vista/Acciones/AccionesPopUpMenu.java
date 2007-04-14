package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;


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
}
