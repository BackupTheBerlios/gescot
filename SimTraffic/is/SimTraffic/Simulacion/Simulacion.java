package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Tramo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Grupo ISTrafico
 * 
 */
public class Simulacion {

	/**
	 * Lista de los vehiculos que se estan simulando
	 */
	private List<IVehiculo> vehiculos;

	/**
	 * Par�metros de la simulaci�n
	 */
	private ParametrosSimulacion param;

	/**
	 * Tabla que mantiene por cada tramo una lista de los vehiculos que estan
	 * ciruclando por ahi
	 */
	private Hashtable<Tramo, ArrayList<IVehiculo>> tabla;

	/**
	 * Mapa en el que se esta realizando la simulaci�n
	 */
	private Mapa mapa;

	public Simulacion() {
		vehiculos = new ArrayList<IVehiculo>();
		tabla = new Hashtable<Tramo, ArrayList<IVehiculo>>();
	}

	public ParametrosSimulacion getParametros() {
		return this.param;
	}

	public int modificaParametros(ParametrosSimulacion parametros) {
		this.param = parametros;
		return 0;
	}

	public int comenzar(Mapa mapa) {
		tabla.clear();
		this.mapa = mapa;
		rellenarTabla();
		return 0;
	}

	public int detener() {

		return 0;
	}

	public int pausar() {
		return 0;
	}

	public List<IVehiculo> getVehiculos() {
		return vehiculos;
	}

	/**
	 * Rellena la tabla que mantiene la relacion entre tramos y vehiculos.
	 * <p>
	 * Este m�todo recorre todos los m�todos del mapa, haciendo una entrada
	 * nueva por cada uno de estos y a�adiendolos como indice en la tabla con
	 * una lista nueva sin elementos.
	 */
	private void rellenarTabla() {
		Iterator<Tramo> it = mapa.getTramos().iterator();
		while (it.hasNext()) {
			tabla.put(it.next(), new ArrayList<IVehiculo>());
		}
	}
}
