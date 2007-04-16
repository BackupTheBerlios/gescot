package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HDetener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * detener una simualción en curso.
 * <p>
 * Esta clase tomara el controlador en su constructor y lo almacenará para luego
 * poder aplicar la herramienta que detiene la simulación en un modelo a traves
 * de su controlador.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionDetenerSimulacion implements ActionListener {

	/**
	 * Controlador de la apliación
	 */
	private IControlador controlador;

	/**
	 * Único constructor de la clase.
	 * <p>
	 * 
	 * @param controlador
	 *            IControlador correspondiente al controlador de la apicación
	 */
	public AccionDetenerSimulacion(IControlador controlador) {
		super();
		this.controlador = controlador;
	}

	public void actionPerformed(ActionEvent arg0) {
		controlador.herramienta(new HDetener());
	}

}