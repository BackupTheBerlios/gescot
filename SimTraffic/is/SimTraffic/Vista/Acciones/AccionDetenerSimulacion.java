package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HDetener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * detener una simualci�n en curso.
 * <p>
 * Esta clase tomara el controlador en su constructor y lo almacenar� para luego
 * poder aplicar la herramienta que detiene la simulaci�n en un modelo a traves
 * de su controlador.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionDetenerSimulacion implements ActionListener {

	/**
	 * Controlador de la apliaci�n
	 */
	private IControlador controlador;

	/**
	 * �nico constructor de la clase.
	 * <p>
	 * 
	 * @param controlador
	 *            IControlador correspondiente al controlador de la apicaci�n
	 */
	public AccionDetenerSimulacion(IControlador controlador) {
		super();
		this.controlador = controlador;
	}

	public void actionPerformed(ActionEvent arg0) {
		controlador.herramienta(new HDetener());
	}

}