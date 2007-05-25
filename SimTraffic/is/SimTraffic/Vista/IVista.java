package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public interface IVista {

	/**
	 * Método que muestra la ventana u otro componente utilizado como interfaz con el usuario.
	 * @roseuid 45C1DF090280
	 */
	public void mostrar();

	/**
	 * Método que actualiza la interfaz con los ultimos valores del modelo.
	 */
	public void actualizar();

	/**
	 * Método que pasa la vista la instancia actual del modelo.
	 * @param modelo
	 * Instancia actual del modelo.
	 * @roseuid 45C1DFB203B8
	 */
	public void setModelo(IModelo modelo);
	
	/**
	 * Método que pasa a la vista la instacia actual del controlador.
	 * @param controlador
	 * Instancia actual del controlador.
	 */
	public void setControlador(IControlador controlador);
	
	public boolean limpiarDatosSimulacion();
}
