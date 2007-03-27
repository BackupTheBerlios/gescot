package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Clase que, de acuerdo con el patr�n Flyweight, agrupa las funciones de
 * procesamiento comunes a todos los vehiculos.
 * <p>
 * Esta clase esta pensada para reproducirse en cada grupo de vehiculos, con el
 * fin de que solo un n�mero limitado de estos accedan a los m�todos de cada un
 * de las instancias.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Inteligencia {

	/**
	 * Almacena una referencia a la simulaci�n actual
	 */
	private Simulacion sim;

	/**
	 * Almacena los parametros de la simulaci�n actual
	 */
	private ParametrosSimulacion param;

	/**
	 * Alamacena la relaci�n entre tramos y vehiculos en un determinado momento.
	 */
	private Hashtable<Tramo, ArrayList<Vehiculo>> tabla;

	/**
	 * Constructor de la clase.
	 * <p>
	 * Este construcutor toma como parametro una simulaci�n, a partir de la cual
	 * inicializar� sus valores.
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
	 * M�todo que coge del modelo los parametros que puedan haber variado.
	 */
	public void actualizarInteligencia() {
		param = sim.getParam();
	}

	/**
	 * M�todo que procesa el vehiculo.
	 * <p>
	 * Este m�todo recurre a otros para procesar el vehiculo, seg�n sus valores,
	 * su posicion, la de los otros y su recorrido. Este m�todo actualizara
	 * finalmente resultar� en la actualizaci�n de la posici�n y velocidad del
	 * coche.
	 * 
	 * @param vehiculo
	 */
	public void procesar(Vehiculo vehiculo) {
		// TODO
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);

		if (controlarFinTramo(vehiculo) && controlarSe�ales(vehiculo))
			procesarTramoSiguiente(vehiculo);

		controlarCochesDelante(vehiculo);

		procesarCambioCarril(vehiculo);

		recalcularAceleracion(vehiculo);

		recalcularVelocidadYPosicion(vehiculo);
	}

	/**
	 * Este m�todo incializa un vehiculo, esto es, lo ubica en el mapa.
	 * <p>
	 * (Explicar Implementacion)
	 * 
	 * @param vehiculo
	 */
	private void inicializarVehiculo(Vehiculo vehiculo) {
		// TODO tiene que tener en cuenta propiedades del mapa (sim.getMapa())
		// y del vehiculo en si. Tambien puede ser bueno calcular en este
		// momento el camino que debe recorrer.
		// tambien puede ser una buena optimizaci�n que si ya tiene un camino,lo
		// vuevla a recorrer con una probabilidad dada
	}

	/**
	 * M�todo que controla que el coche no este llegando a fin de tramo.
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
	 * M�todo que determina si la se�al siguiente deja pasar al coche.
	 * <p>
	 * Este m�todo se utiliza cuando se llega al fin del tramo para saber si se
	 * puede continuar, o en cualquier otro momento para regular la velocidad de
	 * acuerdo a lo que se deba hacer al final del tramo.<br>
	 * (Explicar implementaci�n)
	 * 
	 * @param vehiculo
	 *            Vehiculo que se esta procesando
	 * @return Booleano, que ser� cierto si hay una se�al que obliga al coche a
	 *         detenerse
	 */
	private boolean controlarSe�ales(Vehiculo vehiculo) {
		// TODO
		return true;

	}

	/**
	 * M�todo para cambiar de tramo una vez que se llega al final del actual.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarTramoSiguiente(Vehiculo vehiculo) {
		// TODO no olvidarse de actualizar la tabla de tramos y vehiculos
		// posiblemente el siguiente tramo se podria pedir al vehiculo, asi se
		// puede sobrecargar para cada uno de los tipos de vehiculos ya que sera
		// muy distinto si es un autobus (que ira por el recorrido) o un camion
		// (que deber�a elegir calles ampilas),etc.
	}

	/**
	 * M�todo para controlar si hay coches delante.
	 * <p>
	 * (Explicar implementacion)
	 * 
	 * @return Double que ser� la velocidad del coche de delante si hay uno cera
	 *         y 0 en otro caso
	 */
	private double controlarCochesDelante(Vehiculo vehiculo) {
		// TODO utilizar "tabla" para solo controlar entre los coches del tramo
		return 0;
	}

	/**
	 * M�todo que, dada la informaci�n del coche de delanta, determina si
	 * cambiar de carril.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarCambioCarril(Vehiculo vehiculo) {
		// TODO
	}

	/**
	 * M�todo para recalcular la acelearci�n de acuerdo a las condiciones dadas.
	 * <p>
	 * (Explicar implmentacion)
	 */
	private void recalcularAceleracion(Vehiculo vehiculo) {
		// TODO
		// seg�n se complica la f�sica, podriamos tener en cuenta masas y
		// caracter�sticas f�sicas m�s "sofisticadas"
		// considerar tambi�n la utilizaci�n del m�todo controlarSe�ales,
		// dado que si hay una se�al que obliga al coche adetenerse
		// mas adelante, debe ir frenando
	}

	/**
	 * M�todo para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		// TODO
	}
}
