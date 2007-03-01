/**
 * 
 */
package is.SimTraffic;

import is.SimTraffic.Vista.*;

/**
 * @author Grupo ISTrafico
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		controlador.setModelo(modelo);
		controlador.setVista(vista);
	}

}
