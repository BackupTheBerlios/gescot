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
	 * Una de las representaciones entra las que se alterna
	 */
	private Representacion rep1;

	/**
	 * La otra representación entre la que se alterna
	 */
	private Representacion rep2;

	/**
	 * Booleano para poder saber cual de las representaciones se esta utilizando
	 */
	boolean cambio;

	public AccionCambiarRep(PanelMapa panel, Representacion rep1,
			Representacion rep2) {
		this.panel = panel;
		this.rep1 = rep1;
		this.rep2 = rep2;
		cambio = false;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO posiblemente se podría añadir un menú, pero así funciona bien
		if (!cambio) {
			if (rep1 != null) {
				panel.setRepresentacion(rep1);
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
			cambio = true;
		} else {
			if (rep2 != null) {
				panel.setRepresentacion(rep2);
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
			cambio = false;

		}
	}

}
