package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Clase que, de acuerdo con el patrón Flyweight, agrupa las funciones de
 * procesamiento comunes a todos los vehiculos.
 * <p>
 * Esta clase esta pensada para reproducirse en cada grupo de vehiculos, con el
 * fin de que solo un número limitado de estos accedan a los métodos de cada un
 * de las instancias.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Inteligencia {

	/**
	 * Almacena una referencia a la simulación actual
	 */
	private Simulacion sim;

	/**
	 * Almacena los parametros de la simulación actual
	 */
	private ParametrosSimulacion param;

	/**
	 * Alamacena la relación entre tramos y vehiculos en un determinado momento.
	 */
	private Hashtable<Tramo, ArrayList<Vehiculo>> tabla;

	/**
	 * Constructor de la clase.
	 * <p>
	 * Este construcutor toma como parametro una simulación, a partir de la cual
	 * inicializará sus valores.
	 * 
	 * @param sim
	 *            Simulacion a la que pertenece la inteligencia
	 */
	public Inteligencia(Simulacion sim) {
		this.sim = sim;
		tabla = sim.getTabla();
		actualizarInteligencia();
	}

	/**
	 * Método que coge del modelo los parametros que puedan haber variado.
	 */
	public void actualizarInteligencia() {
		param = sim.getParam();
	}

	/**
	 * Método que procesa el vehiculo.
	 * <p>
	 * Este método recurre a otros para procesar el vehiculo, según sus valores,
	 * su posicion, la de los otros y su recorrido. Este método actualizara
	 * finalmente resultará en la actualización de la posición y velocidad del
	 * coche.
	 * 
	 * @param vehiculo
	 */
	public void procesar(Vehiculo vehiculo) {
		// TODO
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);

		if (controlarFinTramo(vehiculo) && controlarSeñales(vehiculo))
			procesarTramoSiguiente(vehiculo);

		controlarCochesDelante(vehiculo);

		procesarCambioCarril(vehiculo);

		recalcularAceleracion(vehiculo);

		recalcularVelocidadYPosicion(vehiculo);
	}

	/**
	 * Este método incializa un vehiculo, esto es, lo ubica en el mapa.
	 * <p>
	 * (Explicar Implementacion)
	 * 
	 * @param vehiculo
	 */
	private void inicializarVehiculo(Vehiculo vehiculo) {
		// TODO tiene que tener en cuenta propiedades del mapa (sim.getMapa())
		// y del vehiculo en si. Tambien puede ser bueno calcular en este
		// momento el camino que debe recorrer.
		// tambien puede ser una buena optimización que si ya tiene un camino,lo
		// vuevla a recorrer con una probabilidad dada
	}

	/**
	 * Método que controla que el coche no este llegando a fin de tramo.
	 * <p>
	 * (Explicar implmentacion)
	 * 
	 * @return booleano que sera verdadero si ya se llego al fin del tramo
	 */
	private boolean controlarFinTramo(Vehiculo vehiculo) {
		// TODO
		return false;
	}

	/**
	 * Método que determina si la señal siguiente deja pasar al coche.
	 * <p>
	 * Este método se utiliza cuando se llega al fin del tramo para saber si se
	 * puede continuar, o en cualquier otro momento para regular la velocidad de
	 * acuerdo a lo que se deba hacer al final del tramo.<br>
	 * (Explicar implementación)
	 * 
	 * @param vehiculo
	 *            Vehiculo que se esta procesando
	 * @return Booleano, que será cierto si hay una señal que obliga al coche a
	 *         detenerse
	 */
	private boolean controlarSeñales(Vehiculo vehiculo) {
		// TODO
		return true;

	}

	/**
	 * Método para cambiar de tramo una vez que se llega al final del actual.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarTramoSiguiente(Vehiculo vehiculo) {
		// TODO no olvidarse de actualizar la tabla de tramos y vehiculos
		// posiblemente el siguiente tramo se podria pedir al vehiculo, asi se
		// puede sobrecargar para cada uno de los tipos de vehiculos ya que sera
		// muy distinto si es un autobus (que ira por el recorrido) o un camion
		// (que debería elegir calles ampilas),etc.
	}

	/**
	 * Método para controlar si hay coches delante.
	 * <p>
	 * (Explicar implementacion)
	 * 
	 * @return Double que será la velocidad del coche de delante si hay uno cera
	 *         y 0 en otro caso
	 */
	private double controlarCochesDelante(Vehiculo vehiculo) {
		// TODO utilizar "tabla" para solo controlar entre los coches del tramo
		return 0;
	}

	/**
	 * Método que, dada la información del coche de delanta, determina si
	 * cambiar de carril.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarCambioCarril(Vehiculo vehiculo) {
		// TODO
	}

	/**
	 * Método para recalcular la acelearción de acuerdo a las condiciones dadas.
	 * <p>
	 * (Explicar implmentacion)
	 */
	private void recalcularAceleracion(Vehiculo vehiculo) {
		// TODO
		// según se complica la física, podriamos tener en cuenta masas y
		// características físicas más "sofisticadas"
		// considerar también la utilización del método controlarSeñales,
		// dado que si hay una señal que obliga al coche adetenerse
		// mas adelante, debe ir frenando
	}

	/**
	 * Método para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		// TODO
	}
}
