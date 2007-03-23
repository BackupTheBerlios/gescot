package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.Representaciones.Representacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para los botones que cambia la forma de
 * representar el mapa.
 * <p>
 * Esta clase toma en su constructor como parámetros el panel del mapa y la
 * representacion que debe ponerle a este.<br>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionCambiarRep implements ActionListener {

	/**
	 * Panel de representacion del mapa al que se le cambiara la forma de
	 * representarlo.
	 */
	private PanelMapa panel;

	/**
	 * Clase de representacion del mapa que se utilizara
	 */
	private Representacion rep;

	public AccionCambiarRep(PanelMapa panel, Representacion rep) {
		this.panel = panel;
		this.rep = rep;
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if (rep != null)
			panel.setRepresentacion(rep);

	}

}
