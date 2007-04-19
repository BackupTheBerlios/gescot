package is.SimTraffic.Simulacion;

import is.SimTraffic.IControlador;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Tramo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que ejecuta la simulaci�n en un mapa.
 * <p>
 * Esta clase mentienen los valores principales de la simulaci�n y los
 * veh�culos.<br>
 * Implementa principalmente dos m�todos, uno para comenzar una simulaci�n y
 * otro para temrinarla. Tambi�n mantiene, adem�s de una lista din�mica de
 * veh�culos, una tabla hash que metiene la relaci�n entre los coches y los
 * tramos que ocupan para facilitar el procesamiento de estos.<br>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Simulacion {

	/**
	 * M�ximo de vehiculos que se pueden simular
	 */
	private static int maxVehiculos = 2000;

	/**
	 * Lista de los vehiculos que se estan simulando
	 */
	private List<Vehiculo> vehiculos;

	/**
	 * Par�metros de la simulaci�n
	 */
	private ParametrosSimulacion param;

	/**
	 * Tabla que mantiene por cada tramo una lista de los vehiculos que estan
	 * ciruclando por ahi
	 */
	private Hashtable<Tramo, ArrayList<Vehiculo>> tabla;

	/**
	 * Mapa en el que se esta realizando la simulaci�n
	 */
	private Mapa mapa;

	private IControlador controlador;
	
	private ControladorSim controladorSim;
	
	private boolean activa = false;
	
	public Simulacion() {
		vehiculos = new ArrayList<Vehiculo>();
		tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
		param = new ParametrosSimulacion();
	}

	/**
	 * M�todo que modifica los par�metros actuales de la simulaci�n.
	 * <p>
	 * Este m�todo se utiliza en lugar de setParametros porque puede devolver
	 * informaci�n en el caso de que surga alg�n problema al cambiar los
	 * par�metros (por ejemplo, porque esta en ejecuci�n una simulaci�n y se
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
	 * M�todo para comenzar la simulaci�n a partir de un mapa.
	 * <p>
	 * Este m�todo comienza la simulaci�n a partir de un mapa, utilizando los
	 * paramentros de param que es del tipo ParametroSimulacion.<br>
	 * 
	 * @param mapa
	 *            Mapa en el que se hace la simulaci�n
	 * @return Entero que indica el resultado de la simulaci�n:<br> + 0 -
	 *         comienzo satisfactorio de la simulaci�n
	 */
	public int comenzar(Mapa mapa) {
		tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
		this.mapa = mapa;
		int max = max(param.getNumVehiculos());
		max = max + max % 10;
		vehiculos = new ArrayList<Vehiculo>(max + 20);
		rellenarTabla();
		crearVehiculos();
		//this.vista = vista;
		controladorSim = new ControladorSim(vehiculos, this);
		controladorSim.start();
		activa = true;
		return 0;
	}

	/**
	 * M�todo para detener la simulaci�n.
	 * <p>
	 * Este m�todo destruye todas las instancias de vehiculos, con lo cual no se
	 * podr� continuar con la misma simulaci�n.
	 * 
	 * @return Entero que indica el resultado del m�todo:<br>
	 *         0 - ejecuci�n satisfactoria
	 */
	public int detener() {
		controladorSim.terminar();
		activa = false;
		vehiculos = null;
		tabla = null;
		return 0;
	}

	/**
	 * M�todo que detiene temporalmente la simulaci�n y la reanuda si esta
	 * detenida.
	 * <p>
	 * Este m�todo deteniene la simulaci�n, envitando el avance de los coches
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
	 * Este m�todo recorre todos los m�todos del mapa, haciendo una entrada
	 * nueva por cada uno de estos y a�adiendolos como indice en la tabla con
	 * una lista nueva sin elementos.
	 */
	private void rellenarTabla() {
		Iterator<Tramo> it = mapa.getTramos().iterator();
		while (it.hasNext()) {
			tabla.put(it.next(), new ArrayList<Vehiculo>());
		}
	}

	/**
	 * M�todo que crea los vehiculos necesarios para esta simulaci�n y los a�ada
	 * a la lista de vehiculos
	 * <p>
	 * Este m�todo se encarga de crear el porcentaje de los vehiculos de acuerdo
	 * a lo establecido en los par�metros de simulaci�n, pero lo hace
	 * aleatoriamente usando los porcentajes como probabilidad para conseguir
	 * una distribuci�n homegenea.
	 */
	private void crearVehiculos() {
		int cant = param.getNumVehiculos()[0];
		if (cant > maxVehiculos)
			cant = maxVehiculos;
		// TODO crear vehiculos de cada tipo segun la infromac�n que hay
		// en los parametros. No se puede hacer ahora porque no se
		// entiende como se guarda esa informaci�n
		
		//Random rand = new Random();
		// param.getPorcentajeTipo();
		
		for (int i = 0; i < cant; i++) {
			vehiculos.add(new Turismo());
		}
	}
	
	private int max(int[] numeros) {
		int res = 0;
		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i] > res)
				res = numeros[i];
		}
		return res;
	}

	public void actualizar() {
		controlador.repintarCoches();
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
	
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;
	}
	
	public boolean estaActiva() {
		return activa;
	}
}
