package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * men� cargar un nuevo fichero de mapa.
 * <p>
 * <p>
 * Esta clase tomara el controlador y el panel con la representaci�n del mapa
 * como par�metros en su construcutr y lo almacenar� para que cuando se ejecute
 * la acci�n poder llamar a la herramienta HCargarMapa encargada de
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
	 * Panel con la representaci�n del mapa
	 */
	private PanelMapa panel;

	public AccionCargar(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (controlador.cambiosEnMapa()) {
			// Preguntar si desea guardar el mapa actual: Si el usuario dice que
			// s�,
			// crear aqu� una herramienta de guardar mapa (Faltar�a crear
			// ventana para ello).
			Object[] options = { Messages.getString("AccionCargar.0"), Messages.getString("AccionCargar.1"), Messages.getString("AccionCargar.2") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			int n = JOptionPane.showOptionDialog(null,
					Messages.getString("AccionCargar.3"), Messages.getString("AccionCargar.4"), //$NON-NLS-1$ //$NON-NLS-2$
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
