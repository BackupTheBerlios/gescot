package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;

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
		if (controlarFinTramo(vehiculo))
			procesarTramoSiguiente(vehiculo);

		controlarCochesDelante(vehiculo);

		recalcularAceleracion(vehiculo);

		recalcularVelocidadYPosicion(vehiculo);
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
	 * Método para cambiar de tramo una vez que se llega al final del actual.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarTramoSiguiente(Vehiculo vehiculo) {
		// TODO no olvidarse de actualizar la tabla de tramos y vehiculos
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
	 * Método para recalcular la acelearción de acuerdo a las condiciones dadas.
	 * <p>
	 * (Explicar implmentacion)
	 */
	private void recalcularAceleracion(Vehiculo vehiculo) {
		// TODO
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
