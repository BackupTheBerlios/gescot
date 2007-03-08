package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para la accion de deshacer.
 * <p>
 * Esta clase toma el contorlador y el panel del mapa en su constructor, el
 * primero para poder aplicar la accion de deshacer y el segundo para referescar
 * el mapa una vez aplicada la operacion.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionDeshacer implements ActionListener {

	IControlador controlador;

	PanelMapa panel_mapa;

	public AccionDeshacer(IControlador controlador, PanelMapa panel_mapa) {
		this.controlador = controlador;
		this.panel_mapa = panel_mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (controlador != null)
			controlador.deshacer();
		if (panel_mapa != null)
		{
			panel_mapa.recrearMapa();
			panel_mapa.repaint();
		}
	}

}
