package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.awt.Dimension;

public class Turismo implements IVehiculo {
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
	 * @roseuid 45B8B3A9029B
	 */
	public Turismo() {

	}

	/**
	 * @return Posicion
	 * @roseuid 45C3083F02D6
	 */
	public Posicion getPosicion() {
		return null;
	}

	/**
	 * @return Tramo
	 * @roseuid 45C3083F02E6
	 */
	public Tramo getTramo() {
		return null;
	}

	/**
	 * @return awt.Dimension
	 * @roseuid 45C3083F02F5
	 */
	public Dimension getDimensiones() {
		return null;
	}

	/**
	 * @return int
	 * @roseuid 45C3083F0314
	 */
	public int getVelActual() {
		return 0;
	}

	/**
	 * @return float
	 * @roseuid 45C3083F0324
	 */
	public float getAceleracionActual() {
		return 0;
	}

	/**
	 * @return float
	 * @roseuid 45C3083F0334
	 */
	public float getDesaceleracion() {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 45C3083F0343
	 */
	public int getDistanciaSeguridad() {
		return distanciaSeguridad;
	}

	/**
	 * @return float
	 * @roseuid 45C3083F0363
	 */
	public float getAceleracionMax() {
		return aceleracionMax;
	}

	/**
	 * @return int
	 * @roseuid 45C3083F0372
	 */
	public int getCarril() {
		return carril;
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
