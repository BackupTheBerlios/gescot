package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Herramientas.HCrearNuevoMapa;
import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * menú cargar un nuevo fichero de mapa.
 * <p>
 * <p>
 * Esta clase tomara el controlador y el panel con la representación del mapa
 * como parámetros en su construcutr y lo almacenará para que cuando se ejecute
 * la acción poder llamar a la herramienta HCargarMapa encargada de
 * efectivamente cargar el mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionCargar implements ActionListener {

	/**
	 * Interface del controlador en el MVC
	 */
	private IControlador controlador;

	/**
	 * Panel con la representación del mapa
	 */
	private PanelMapa panel;

	public AccionCargar(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
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
				controlador.herramienta(new HCargarMapa(controlador,panel));
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
			else if (n==1) {
				controlador.herramienta(new HCargarMapa(controlador,panel));
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
		}
		else {
			controlador.herramienta(new HCargarMapa(controlador,panel));
			panel.recrear();
			panel.recrearMapa();
			panel.repaint();
			
		}
	}

}
