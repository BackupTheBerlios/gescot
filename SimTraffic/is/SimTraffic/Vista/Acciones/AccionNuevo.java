/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCrearNuevoMapa;
import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Grupo ISTrafico
 * 
 */
public class AccionNuevo implements ActionListener {

	private IControlador controlador;

	private PanelMapa panel_mapa;

	public AccionNuevo(IControlador controlador, PanelMapa panel_mapa) {
		this.controlador = controlador;
		this.panel_mapa = panel_mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (controlador.cambiosEnMapa()) {
			// Preguntar si desea guardar el mapa actual: Si el usuario dice que
			// sí,
			// crear aquí una herramienta de guardar mapa (Faltaría crear
			// ventana para ello).
			Object[] options = { "Si", "No", "Cancelar" };
			int n = JOptionPane.showOptionDialog(null,
					"Desea guardar los cambios?", "Cambios en el mapa",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if (n==0) {
				// TODO guardar mapa
				controlador.herramienta(new HGuardarMapa());
				controlador.herramienta(new HCrearNuevoMapa());
				panel_mapa.recrearMapa();
				panel_mapa.repaint();
			}
			else if (n==1) {
				controlador.herramienta(new HCrearNuevoMapa());
				panel_mapa.recrearMapa();
				panel_mapa.repaint();
			}
		}
		else {
			controlador.herramienta(new HCrearNuevoMapa());
			panel_mapa.recrearMapa();
			panel_mapa.repaint();
			
		}
	}

}
