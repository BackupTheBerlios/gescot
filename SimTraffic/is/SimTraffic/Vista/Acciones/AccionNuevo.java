/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HCrearNuevoMapa;
import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
			Object[] options = { Messages.getString("AccionNuevo.0"), Messages.getString("AccionNuevo.1"), Messages.getString("AccionNuevo.2") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			int n = JOptionPane.showOptionDialog(null,
					Messages.getString("AccionNuevo.3"), Messages.getString("AccionNuevo.4"), //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if (n==0) {
				// TODO guardar mapa
				controlador.herramienta(new HGuardarMapa());
				controlador.herramienta(new HCrearNuevoMapa());
				panel_mapa.getRepresentacion().setImagenes(new ArrayList<Image>());
				panel_mapa.recrearMapa();
				panel_mapa.repaint();
			}
			else if (n==1) {
				controlador.herramienta(new HCrearNuevoMapa());
				panel_mapa.getRepresentacion().setImagenes(new ArrayList<Image>());
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
