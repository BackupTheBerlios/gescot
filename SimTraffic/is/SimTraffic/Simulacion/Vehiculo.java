package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

/**
 * Clase abstracta que representa un vehiculo.
 * <p>
 * Esta clase tiene las variables principales de un vehiculo y los métodos para
 * accederlas, así como otros métodos requeridos por los vehículos para la
 * simulación.<br>
 * 
 * 
 * @author Grupo ISTrafico
 * 
 */
public abstract class Vehiculo {

	/**
	 * Velocidad a la que esta ciruculando el vehiculo.
	 */
	protected double velocidad;

	/**
	 * Máxima velocidad a la que puede cirucular el vehiculo.
	 */
	protected double velocidadMax;

	/**
	 * Aceleración actual del vehiculo.
	 */
	protected float aceleracion;

	/**
	 * Aceleración máxima que puede alcanzar el vehiculo.
	 */
	protected float aceleracionMax;

	/**
	 * Carril que esta ocupando el vehiculo.
	 */
	protected int carril;

	/**
	 * Distancia de seguridad que intentará mantener el vehiculo.
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
	 * Método para variar la aceleración.
	 * <p>
	 * Este método controla que se mantengan los valores correctos, y permite
	 * indicar mediante un entero si se desea que incremente mucho o poco, o si
	 * se quiere reducir.
	 * 
	 * @param cuanto
	 *            Entero que indica cuanto se quiere variar, siendo 0 nada, -5
	 *            desacelerar mucho y 5 acelerar mucho.
	 */
	public abstract void variarAceleracion(int cuanto);

	/**
	 * Estos metodos se incluyen porque son necesarios para relacionar los
	 * vehiculos con los nodos de entrada salida y poder mantener el numero de
	 * coches que salen de un nodo acorde a las especificaciones
	 */
	public void setNodoOrigen(Nodo nodo) {
		nodoOrigen = nodo;
	}

	public synchronized Nodo getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoDestino(Nodo nodo) {
		nodoDestino = nodo;
	}

	public synchronized Nodo getNodoDestino() {
		return nodoDestino;
	}

	public synchronized double getPosicion() {
		return posicion;
	}

	public synchronized Tramo getTramo() {
		return tramo;
	}

	public synchronized double getVelocidad() {
		return this.velocidad;
	}

	public float getAceleracion() {
		return this.aceleracion;
	}

	public int getDistanciaSeguridad() {
		return this.distanciaSeguridad;
	}

	public synchronized int getCarril() {
		return this.carril;
	}

}
