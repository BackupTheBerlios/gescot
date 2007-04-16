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
 * menú que permiten "cortar" una selección en el sentido de windows, esto es,
 * copiar al portapaeles y eliminar de la instancia actual del mapa.
 * <p>
 * Esta clase tomara en su constructor el modelo y el controlador de la
 * aplicación, así como el panel con la representación del mapa. Todo esto lo
 * almacenará como variables privadas para poder ejecutar la acción cuando lo
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
	 * Panel con la representación del mapa
	 */
	private PanelMapa panel;

	/**
	 * Constructora que guarda en sus atributos los valores de modelo,
	 * controlador y panel.
	 * <p>
	 * 
	 * @param modelo
	 *            Modelo de la aplicación
	 * @param controlador
	 *            Controlador de la aplicación
	 * @param panel
	 *            Panel con el representación del mapa
	 */

	public AccionCortar(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}

	/**
	 * Método que coge los elementos de la selección, los mete en el
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
