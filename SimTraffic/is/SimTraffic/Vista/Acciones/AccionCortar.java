/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCortar;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que se encarga de cortar una selección de elementos del mapa
 * @author Grupo ISTrafico
 *
 */
public class AccionCortar implements ActionListener {
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	IModelo modelo;
	IControlador controlador;
	PanelMapa panel;
	
	/**
	 * Constructora que guarda en sus atributos los valores de modelo, controlador y panel
	 * @param modelo
	 * @param controlador
	 * @param panel
	 */
	
	public AccionCortar (IModelo modelo, IControlador controlador, PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}
	
	/**
	 * Método que coge los elementos de la selección, los mete en el portapapeles y los elimina del mapa.
	 * 
	 * 
	 */	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		panel.setFocusable(true);		
		HCortar herramientaCortar = new HCortar(modelo.getMapa().getSeleccion().getNodosSeleccionados(),
					modelo.getMapa().getSeleccion().getTramosSeleccionados());
		controlador.herramienta(herramientaCortar);
		modelo.getMapa().setSeleccion(new Seleccion());
		panel.setRecrear(true);
		panel.repaint();
	}
	
}
