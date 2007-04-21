package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.util.Random;

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
	 * Nombre que identificar� al tipo de veh�culo (turismo, cami�n, etc.)
	 */
	protected String nombre;
	
	/**
	 * Color del veh�culo (para una posible representaci�n)
	 */
	protected Color color; 
	
	/**
	 * Velocidad a la que esta ciruculando el vehiculo.
	 */
	protected double velocidad;

	/**
	 * M�xima velocidad a la que puede cirucular el vehiculo.
	 */
	protected double velocidadMax;

	/**
	 * Aceleraci�n actual del vehiculo.
	 */
	protected double aceleracion;

	/**
	 * Aceleraci�n m�xima que puede alcanzar el vehiculo.
	 */
	protected double aceleracionMax;

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
	
	protected Nodo nodoEntrada;
	protected Nodo nodoSalida;
	
	/**
	 * Identificador del veh�culo
	 * */
	protected int id;
	
	/**
	 * N�mero global de coches creados.
	 * */
	protected static int ncochesglobal = 0;

	/**
	 * M�todo para variar la aceleraci�n.
	 * <p>
	 * Este m�todo controla que se mantengan los valores correctos, y permite
	 * indicar mediante un entero si se desea que incremente mucho o poco, o si
	 * se quiere reducir.
	 * 
	 * @param cuanto
	 *            Entero que indica cuanto se quiere variar, siendo 0 nada, -5
	 *            desacelerar mucho y 5 acelerar mucho.
	 */
	public abstract void variarAceleracion(int cuanto);

	public synchronized void actualizarVelocidad() {
		velocidad += aceleracion;
		if (velocidad > velocidadMax) {
			velocidad = velocidadMax;
			aceleracion = 0;
		}
		if (velocidad > tramo.getVelMax()) {
			velocidad = tramo.getVelMax();
			aceleracion = 0;
		}
		if (velocidad < 0) {
			velocidad = 0;
			if (aceleracion < 0)
				aceleracion = 0;
		}
		
	}
	
	public abstract Tramo siguienteTramo();
	
	
	public boolean inicializar(Nodo entrada, Nodo salida) {
		nodoEntrada = entrada;
		nodoSalida = salida; 
		return true;
	}
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
	
	public synchronized void setTramo(Tramo tramo) {
		this.tramo = tramo;
	}

	public synchronized void resetaerPosicion() {
		this.posicion = 0;
	}
	
	public synchronized double getVelocidad() {
		return this.velocidad;
	}

	public synchronized double getVelocidadMax() {
		return this.velocidadMax;
	}
	
	public double getAceleracion() {
		return this.aceleracion;
	}

	public int getDistanciaSeguridad() {
		return this.distanciaSeguridad;
	}

	public synchronized int getCarril() {
		return this.carril;
	}

	public synchronized void setCarril(int carril) {
		this.carril = carril;
	}

	public int getId() {	
		return this.id;	
		}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void generarColorAleatorio() {
		Random r = new Random();
		int auxColor = r.nextInt(5);
		if (auxColor == 0)
			color = Color.RED;
		else if (auxColor == 1)
			color = Color.BLUE;
		else if (auxColor == 2)
			color = Color.GREEN;
		else if (auxColor == 3)
			color = Color.ORANGE;
		else if (auxColor == 4)
			color = Color.PINK;
		else
			color = Color.WHITE;
	}
}
