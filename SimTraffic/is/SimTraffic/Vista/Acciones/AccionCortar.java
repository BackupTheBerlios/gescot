/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCortar;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * men� que permiten "cortar" una selecci�n en el sentido de windows, esto es,
 * copiar al portapaeles y eliminar de la instancia actual del mapa.
 * <p>
 * Esta clase tomara en su constructor el modelo y el controlador de la
 * aplicaci�n, as� como el panel con la representaci�n del mapa. Todo esto lo
 * almacenar� como variables privadas para poder ejecutar la acci�n cuando lo
 * requiera.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionCortar implements ActionListener {

	/**
	 * Interfaz del modelo en el MVC
	 */
	private IModelo modelo;

	/**
	 * Interfaz del controlador en el MVC
	 */
	private IControlador controlador;

	/**
	 * Panel con la representaci�n del mapa
	 */
	private PanelMapa panel;

	/**
	 * Constructora que guarda en sus atributos los valores de modelo,
	 * controlador y panel.
	 * <p>
	 * 
	 * @param modelo
	 *            Modelo de la aplicaci�n
	 * @param controlador
	 *            Controlador de la aplicaci�n
	 * @param panel
	 *            Panel con el representaci�n del mapa
	 */

	public AccionCortar(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}

	/**
	 * M�todo que coge los elementos de la selecci�n, los mete en el
	 * portapapeles y los elimina del mapa.
	 */
	public void actionPerformed(ActionEvent arg0) {
		panel.setFocusable(true);
		HCortar herramientaCortar = new HCortar(modelo.getMapa().getSeleccion()
				.getNodosSeleccionados(), modelo.getMapa().getSeleccion()
				.getTramosSeleccionados());
		controlador.herramienta(herramientaCortar);
		modelo.getMapa().setSeleccion(new Seleccion());
		panel.setRecrear(true);
		panel.repaint();
	}

}
