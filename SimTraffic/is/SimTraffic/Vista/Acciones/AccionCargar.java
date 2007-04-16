package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		if (controlador != null) {
			controlador.herramienta(new HCargarMapa(controlador, panel));
			panel.repaint();
			panel.recrear();
		}
	}

}
