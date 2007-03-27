package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

/**
 * Clase abstracta que representa un vehiculo.
 * <p>
 * Esta clase tiene las variables principales de un vehiculo y los m�todos para
 * accederlas, as� como otros m�todos requeridos por los veh�culos para la
 * simulaci�n.<br>
 * 
 * 
 * @author Grupo ISTrafico
 * 
 */
public abstract class Vehiculo {

	/**
	 * Velocidad a la que esta ciruculando el vehiculo.
	 */
	protected int velocidadActual;

	/**
	 * M�xima velocidad a la que puede cirucular el vehiculo.
	 */
	protected int velocidadMax;

	/**
	 * Aceleraci�n actual del vehiculo.
	 */
	protected float aceleracionActual;

	/**
	 * Aceleraci�n m�xima que puede alcanzar el vehiculo.
	 */
	protected float aceleracionMax;

	/**
	 * Desacelaraci�n que esta sufriendo el vehiculo.
	 */
	protected float desaceleracion;

	/**
	 * Carril que esta ocupando el vehiculo.
	 */
	protected int carril;

	/**
	 * Distancia de seguridad que intentar� mantener el vehiculo.
	 */
	protected int distanciaSeguridad;

	/**
	 * Posicion del vehiculo a lo largo del tramo
	 */
	protected double posicion;

	/**
	 * Tramo en el que se encuentra el vehiculo
	 */
	protected Tramo tramo;

	/**
	 * Nodo de origen del vehiculo
	 */
	protected Nodo nodoOrigen;

	/**
	 * Nodo al que se dirige el vehiculo
	 */
	protected Nodo nodoDestino;

	/**
	 * Estos metodos se incluyen porque son necesarios para relacionar los
	 * vehiculos con los nodos de entrada salida y poder mantener el numero de
	 * coches que salen de un nodo acorde a las especificaciones
	 */
	public void setNodoOrigen(Nodo nodo) {
		nodoOrigen = nodo;
	}

	public Nodo getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoDestino(Nodo nodo) {
		nodoDestino = nodo;
	}

	public Nodo getNodoDestino() {
		return nodoDestino;
	}

	public double getPosicion() {
		return posicion;
	}

	public Tramo getTramo() {
		return tramo;
	}

	public int getVelActual() {
		return this.velocidadActual;
	}

	public float getAceleracionActual() {
		return this.aceleracionActual;
	}

	public float getDesaceleracion() {
		return this.desaceleracion;
	}

	public int getDistanciaSeguridad() {
		return this.distanciaSeguridad;
	}

	public float getAceleracionMax() {
		return this.aceleracionMax;
	}

	public int getCarril() {
		return this.carril;
	}
}
