/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * menú que permiten "copiar" una selección en el sentido de windows, esto es,
 * al portapapeles para luego poder ser pegada en otro sitio una o varias veces.
 * <p>
 * Esta clase tomara en su constructor el modelo y el controlador de la
 * aplicación, así como el panel con la representación del mapa. Todo esto lo
 * almacenará como variables privadas para poder ejecutar la acción cuando lo
 * requiera.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionCopiar implements ActionListener {

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
	 * Constructora de Copiar.
	 * <p>
	 * Se recorren todos los tramos de la seleccion. Para cada tramo, se miran
	 * sus nodos inicial y final. Si estaban en el portapapeles, se añade el
	 * tramo a los tramos de los nodos. Si no, se añaden al portapapeles.
	 * Después se busca el nodo que tenga la posición mínima para que sirva de
	 * referencia a la hora de pegar el contenido del portapapeles.
	 * 
	 * @param modelo
	 *            Modelo de la aplicación
	 * @param controlador
	 *            Controlador de la aplicación
	 * @param panel
	 *            Panel con el representación del mapa
	 */
	public AccionCopiar(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent arg0) {
		panel.setFocusable(true);
		HCopiar herramientaCopiar = new HCopiar(modelo.getMapa().getSeleccion()
				.getNodosSeleccionados(), modelo.getMapa().getSeleccion()
				.getTramosSeleccionados());
		controlador.herramienta(herramientaCopiar);

	}

}
