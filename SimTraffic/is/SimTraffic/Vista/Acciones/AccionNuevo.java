/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Grupo ISTrafico
 *
 */
public class AccionNuevo implements ActionListener {

	private IModelo modelo;
	private PanelMapa panel_mapa;
	
	public AccionNuevo(IModelo modelo, PanelMapa panel_mapa) {
		this.modelo = modelo;
		this.panel_mapa = panel_mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (modelo.getMapa().getNodos().size() != 0) {
			//Preguntar si desea guardar el mapa actual: Si el usuario dice que sí,
			//crear aquí una herramienta de guardar mapa (Faltaría crear ventana para ello).
		}
		modelo.setMapa(new Mapa());
		panel_mapa.recrearMapa();
		panel_mapa.repaint();
	}

}
