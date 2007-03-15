/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Grupo ISTrafico
 *
 */
public class AccionZoom implements ActionListener {

	private PanelMapa panel;
	
	private double cambio;
	
	public AccionZoom(PanelMapa panel, double cambio) {
		this.panel = panel;
		this.cambio = cambio;
	}
	public void actionPerformed(ActionEvent arg0) {
		panel.cambiaZoom(cambio);
	}

}
