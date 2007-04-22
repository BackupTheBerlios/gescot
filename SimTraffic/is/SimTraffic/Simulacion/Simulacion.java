package is.SimTraffic.Simulacion;

import is.SimTraffic.IControlador;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
 */
public class Simulacion {

	/**
	 * M�ximo de vehiculos que se pueden simular
	 */
	public static int maxVehiculos = 2000;

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

	Random random;

	private int[] entradas = new int[3];

	private int[] salidas = new int[3];

	private int vehiculosActivos;

	public Simulacion() {
		vehiculos = new ArrayList<Vehiculo>();
		tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
		param = new ParametrosSimulacion();
		random = new Random();
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
		max = max + 10 - max % GrupoVehiculos.nroVehiculos;
		if (max > maxVehiculos)
			max = maxVehiculos;
		System.out.println(" " + max);
		vehiculos = new ArrayList<Vehiculo>(max + 20);
		vehiculosActivos = 0;
		rellenarTabla();
		crearVehiculos(max);
		// this.vista = vista;
		controladorSim = new ControladorSim(vehiculos, this);
		controladorSim.start();
		activa = true;
		Iterator<Nodo> it = mapa.getNodos().iterator();
		EntradaSalida es;
		for (int i = 0; i < 3; i++) {
			entradas[i] = 0;
			salidas[i] = 0;
		}
		while (it.hasNext()) {
			es = it.next().getEs();
			for (int i = 0; i < 3; i++) {
				entradas[i] = entradas[i] + es.getPorcentajesEntrada()[i];
				salidas[i] = salidas[i] + es.getPorcentajesSalida()[i];
			}
		}
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
	private void crearVehiculos(int cant) {
		// TODO crear vehiculos de cada tipo segun la infromac�n que hay
		// en los parametros. No se puede hacer ahora porque no se
		// entiende como se guarda esa informaci�n

		// Random rand = new Random();
		// param.getPorcentajeTipo();

		for (int i = 0; i < cant; i++) {
			Random r = new Random();
			int aux = r.nextInt(10);
			//Se generan ahora mismo 3 camiones por cada 10 vehiculos (deber�a ser un par�metro).
			if (aux>2)
				vehiculos.add(new Turismo());
			else
				vehiculos.add(new Camion());
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

	/**
	 * M�todo que devuelve un nodo, que ser� entrada para un coche, seg�n la
	 * necesidad de estos.
	 * <p>
	 * En este m�todo se aprovecha para incrementar el n�mero de coches activos
	 * que hay en el mapa si se le pasa un valor. En caso de no querese nuevos
	 * vehiculos se devolver� null.
	 * 
	 * @return Nodo de entrada o null
	 */
	synchronized public Nodo getEntrada() {
		// TODO da los nodos como corresponde, pero no mira la franja horaria en
		// la que se esta
		int franjaHoraria = 0;
		if (vehiculosActivos < param.getNumVehiculos()[franjaHoraria]) {
			// si no hay nodos de entrada, elegir aleatoriamente
			if (entradas[franjaHoraria] == 0) {
				int i = random.nextInt(mapa.getNodos().size());
				vehiculosActivos++;
				return mapa.getNodos().get(i);
			}
			// si hay nodos de entrada, elegir por eso
			int i = random.nextInt(entradas[franjaHoraria]);
			Iterator<Nodo> it = mapa.getNodos().iterator();
			while (it.hasNext()) {
				Nodo nodo = it.next();
				i -= nodo.getEs().getPorcentajesEntrada()[franjaHoraria];
				if (i <= 0) {
					vehiculosActivos++;
					return nodo;
				}
			}
		}
		// si no hacen falta m�s coches
		return null;
	}

	synchronized public Nodo getSalida() {
		// TODO da los nodos como corresponde, pero no mira la franja horaria en
		// la que se esta
		int franjaHoraria = 0;
		// si no hay nodos de salida, elegir aleatoriamente
		if (salidas[franjaHoraria] == 0) {
			int i = random.nextInt(mapa.getNodos().size());
			return mapa.getNodos().get(i);
		}
		// si hay nodos de entrada, elegir por eso
		int i = random.nextInt(salidas[franjaHoraria]);
		Iterator<Nodo> it = mapa.getNodos().iterator();
		Nodo nodo = mapa.getNodos().get(0);
		while (it.hasNext()) {
			nodo = it.next();
			i -= nodo.getEs().getPorcentajesSalida()[franjaHoraria];
			if (i <= 0) {
				return nodo;
			}
		}
		return nodo;

	}

	public synchronized void saleVehiculo() {
		vehiculosActivos--;
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
	
	/**
	 * M�todo para mejorar la elecci�n de caminos (no solo en funci�n de la distancia, sino en funci�n del tr�fico actual)
	 * @param tramo
	 * @return
	 */
	public double densidadTramo(Tramo tramo) { 
		ArrayList<Vehiculo> listaVehiculos= tabla.get(tramo);
		int longitudT = tramo.getLargo();
		int numCoches = listaVehiculos.size();
		//Versi�n simple, realmente deber�a comprobar el tramo en el sentido en que quiere recorrerlo y no en ambos.
		int numCarriles = tramo.getNumCarrilesDir1() + tramo.getNumCarrilesDir2();
		//Devuelve un valor entre 0 y 1, mayor cuantos m�s coches haya.
		double densidad = numCoches / (longitudT * numCarriles);
		//Se normaliza con la distancia (devolver� un valor entre 0 y la distancia del tramo).
		densidad = longitudT * densidad;
		return densidad;
	}
}
