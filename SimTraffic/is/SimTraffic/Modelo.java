package is.SimTraffic;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.List;

/**
 * Clase Modelo donde se mantiene toda la informaci�n actual del mapa y de la simulaci�n.
 * <p>
 * Esta clase implementa la interfaz IModelo, y forma parte del patr�n MVC. En el modelo
 * se almacena toda la informaci�n de la instancia actual de la aplicaci�n, en este caso
 * compuesta por el mapa (clase tipo Mapa) y la simulaci�n (clase tipo Simulacion).
 * 
 * @author Grupo ISTrafico
 */
public class Modelo implements IModelo {
	
	/**
	 * Mantiene la instancia actual del mapa.
	 */
	private Mapa mapa;

	/**
	 * Mantiene la simulaci�n actual.
	 */
	private Simulacion simulacion;


	
	/**
	 * Constructor de la clase modelo.<p>
	 * Este constructor inicializa el mapa y la simulaci�n a sus valores por defecto.
	 * @roseuid 45B8B3A901D0
	 */
	public Modelo() {
		mapa = new Mapa();
		simulacion = new Simulacion();

	}

	/**
	 * @return Mapa
	 * @roseuid 45C1E1C8009B
	 */
	public Mapa getMapa() {
		return mapa;
	}

	/**
	 * @return java.util.ArrayList
	 * @roseuid 45C1E1C800BB
	 */
	public List<Vehiculo> getVehiculos() {
		return simulacion.getVehiculos();
	}

	/**
	 * @return Simulacion
	 * @roseuid 45C1E1C800CA
	 */
	public Simulacion getSimulacion() {
		return simulacion;
	}

	/**
	 * @param mapa
	 * @roseuid 45C1E1C800E9
	 */
	public void setMapa(Mapa mapa) {
		if (mapa != null)
			this.mapa = mapa;
	}
	


}
