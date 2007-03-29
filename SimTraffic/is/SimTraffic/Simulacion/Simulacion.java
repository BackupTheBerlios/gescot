package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Tramo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que ejecuta la simulación en un mapa.
 * <p>
 * Esta clase mentienen los valores principales de la simulación y los
 * vehículos.<br>
 * Implementa principalmente dos métodos, uno para comenzar una simulación y
 * otro para temrinarla. También mantiene, además de una lista dinámica de
 * vehículos, una tabla hash que metiene la relación entre los coches y los
 * tramos que ocupan para facilitar el procesamiento de estos.<br>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Simulacion {

	/**
	 * Máximo de vehiculos que se pueden simular
	 */
	private static int maxVehiculos = 2000;

	/**
	 * Lista de los vehiculos que se estan simulando
	 */
	private List<Vehiculo> vehiculos;

	/**
	 * Parámetros de la simulación
	 */
	private ParametrosSimulacion param;

	/**
	 * Tabla que mantiene por cada tramo una lista de los vehiculos que estan
	 * ciruclando por ahi
	 */
	private Hashtable<Tramo, ArrayList<Vehiculo>> tabla;

	/**
	 * Mapa en el que se esta realizando la simulación
	 */
	private Mapa mapa;

	public Simulacion() {
		vehiculos = new ArrayList<Vehiculo>();
		tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
	}

	public ParametrosSimulacion getParametros() {
		return this.param;
	}

	/**
	 * Método que modifica los parámetros actuales de la simulación.
	 * <p>
	 * Este método se utiliza en lugar de setParametros porque puede devolver
	 * información en el caso de que surga algún problema al cambiar los
	 * parámetros (por ejemplo, porque esta en ejecución una simulación y se
	 * cambia un valor no permitido.<br>
	 * 
	 * @param parametros
	 * @return
	 */
	public int modificaParametros(ParametrosSimulacion parametros) {
		this.param = parametros;
		return 0;
	}

	/**
	 * Método para comenzar la simulación a partir de un mapa.
	 * <p>
	 * Este método comienza la simulación a partir de un mapa, utilizando los
	 * paramentros de param que es del tipo ParametroSimulacion.<br>
	 * 
	 * @param mapa
	 *            Mapa en el que se hace la simulación
	 * @return Entero que indica el resultado de la simulación:<br> + 0 -
	 *         comienzo satisfactorio de la simulación
	 */
	public int comenzar(Mapa mapa) {
		tabla.clear();
		this.mapa = mapa;
		rellenarTabla();
		crearVehiculos();
		
		return 0;
	}

	/**
	 * Método para detener la simulación.
	 * <p>
	 * Este método destruye todas las instancias de vehiculos, con lo cual no se
	 * podrá continuar con la misma simulación.
	 * 
	 * @return Entero que indica el resultado del método:<br>
	 *         0 - ejecución satisfactoria
	 */
	public int detener() {

		return 0;
	}

	/**
	 * Método que detiene temporalmente la simulación y la reanuda si esta
	 * detenida.
	 * <p>
	 * Este método deteniene la simulación, envitando el avance de los coches
	 * hasta que se vuelva a ejecutar.
	 * 
	 * @return 0 en caso de funcionar satisfactoriamente.
	 */
	public int pausar() {
		return 0;
	}

	/**
	 * Rellena la tabla que mantiene la relacion entre tramos y vehiculos.
	 * <p>
	 * Este método recorre todos los métodos del mapa, haciendo una entrada
	 * nueva por cada uno de estos y añadiendolos como indice en la tabla con
	 * una lista nueva sin elementos.
	 */
	private void rellenarTabla() {
		Iterator<Tramo> it = mapa.getTramos().iterator();
		while (it.hasNext()) {
			tabla.put(it.next(), new ArrayList<Vehiculo>());
		}
	}

	/**
	 * Método que crea los vehiculos necesarios para esta simulación y los añada
	 * a la lista de vehiculos
	 * <p>
	 * Este método se encarga de crear el porcentaje de los vehiculos de acuerdo
	 * a lo establecido en los parámetros de simulación, pero lo hace
	 * aleatoriamente usando los porcentajes como probabilidad para conseguir
	 * una distribución homegenea.
	 */
	private void crearVehiculos() {
		int cant = param.getNumVehiculos();
		if (cant > maxVehiculos)
			cant = maxVehiculos;
		Random rand = new Random();
		// param.getPorcentajeTipo();
		// TODO crear vehiculos de cada tipo segun la infromacón que hay
		// en los parametros. No se puede hacer ahora porque no se
		// entiende como se guarda esa información
		for (int i = 0; i < cant; i++) {
			vehiculos.add(new Turismo());
		}
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public synchronized Hashtable<Tramo, ArrayList<Vehiculo>> getTabla() {
		return tabla;
	}

	public synchronized ParametrosSimulacion getParam() {
		return param;
	}

	public synchronized Mapa getMapa() {
		return mapa;
	}
}
