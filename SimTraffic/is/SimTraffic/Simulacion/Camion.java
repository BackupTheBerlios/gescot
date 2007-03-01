package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.awt.Dimension;

/**
 * @author Grupo ISTrafico
 *
 */
public class Camion implements IVehiculo {
	/**
	 * Velocidad a la que esta ciruculando el vehiculo.
	 */
	private int velocidadActual;

	/**
	 * Máxima velocidad a la que puede cirucular el vehiculo.
	 */
	private int velocidadMax;

	/**
	 * Aceleración actual del vehiculo.
	 */
	private float aceleracionActual;

	/**
	 * Aceleración máxima que puede alcanzar el vehiculo.
	 */
	private float aceleracionMax;

	/**
	 * Desacelaración que esta sufriendo el vehiculo.
	 */
	private float desaceleracion;

	/**
	 * Carril que esta ocupando el vehiculo.
	 */
	private int carril;

	/**
	 * Distancia de seguridad que intentará mantener el vehiculo.
	 */
	private int distanciaSeguridad;

	/**
	 * @roseuid 45B8B3A8000B
	 */
	public Camion() {

	}

	/**
	 * @return Posicion
	 * @roseuid 45C1E574030C
	 */
	public Posicion getPosicion() {
		return null;
	}

	/**
	 * @return Tramo
	 * @roseuid 45C1E574033B
	 */
	public Tramo getTramo() {
		return null;
	}

	/**
	 * @return awt.Dimension
	 * @roseuid 45C1E574036A
	 */
	public Dimension getDimensiones() {
		return null;
	}

	/**
	 * @return int
	 * @roseuid 45C1E5740399
	 */
	public int getVelActual() {
		return 0;
	}

	/**
	 * @return float
	 * @roseuid 45C1E57403B8
	 */
	public float getAceleracionActual() {
		return 0;
	}

	/**
	 * @return float
	 * @roseuid 45C1E57403E7
	 */
	public float getDesaceleracion() {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 45C1E575001E
	 */
	public int getDistanciaSeguridad() {
		return 0;
	}

	/**
	 * @return float
	 * @roseuid 45C1E575004D
	 */
	public float getAceleracionMax() {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 45C1E575007C
	 */
	public int getCarril() {
		return 0;
	}

	public Nodo getNodoDestino() {
		// TODO Auto-generated method stub
		return null;
	}

	public Nodo getNodoOrigen() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNodoDestino(Nodo nodo) {
		// TODO Auto-generated method stub

	}

	public void setNodoOrigen(Nodo nodo) {
		// TODO Auto-generated method stub

	}
}
