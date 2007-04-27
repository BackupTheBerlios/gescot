package is.SimTraffic.Simulacion;

import is.SimTraffic.IControlador;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Señales.Semaforo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

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
 */
public class Simulacion {

	/**
	 * Máximo de vehiculos que se pueden simular
	 */
	public static int maxVehiculos = 400;

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

	private IControlador controlador;

	private ControladorSim controladorSim;

	private boolean activa = false;

	private Random random;

	private int[] entradas = new int[3];

	private int[] salidas = new int[3];

	private int vehiculosActivos;

	private Reloj reloj;

	private Hashtable<Nodo, Hashtable<Nodo, ArrayList<Tramo>>> caminosDefecto;

	public Simulacion() {
		vehiculos = new ArrayList<Vehiculo>();
		tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
		caminosDefecto = new Hashtable<Nodo, Hashtable<Nodo, ArrayList<Tramo>>>();
		param = new ParametrosSimulacion();
		random = new Random();
		reloj = new Reloj();
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
		boolean intentar = true;

		while (intentar) {
			try {
				if (controladorSim != null) {
					controladorSim.despausar();
					return 0;
				}
				// tabla = new Hashtable<Tramo, ArrayList<Vehiculo>>();
				this.mapa = mapa;
				int max = max(param.getNumVehiculos());
				max = max + GrupoVehiculos.nroVehiculos - max
						% GrupoVehiculos.nroVehiculos;
				if (max > maxVehiculos)
					max = maxVehiculos;
				vehiculosActivos = 0;
				rellenarTabla();
				crearVehiculos(max + 20);
				// TODO esta funcion es problematica, yo creo que es mejor no calcular los caminos y listo
				//crearCaminosDefecto();
				controladorSim = new ControladorSim(vehiculos, this);
				controladorSim.start();
				activa = true;
				Iterator<Nodo> it = mapa.getNodos().iterator();
				EntradaSalida es;
				for (int i = 0; i < 3; i++) {
					entradas[i] = 0;
					salidas[i] = 0;
				}
				Nodo temp;
				Semaforo sem = new Semaforo(null);
				while (it.hasNext()) {
					temp = it.next();
					es = temp.getEs();
					if (es != null) {
						for (int i = 0; i < 3; i++) {
							entradas[i] = entradas[i]
									+ es.getPorcentajesEntrada()[i];
							salidas[i] = salidas[i]
									+ es.getPorcentajesSalida()[i];
						}
					}

					if (temp.getSeñal() != null
							&& temp.getSeñal().getClass() == sem.getClass()) {
						((Semaforo) temp.getSeñal()).setReloj(reloj);
					}
				}
				intentar = false;
			} catch (Exception e) {
				e.printStackTrace();
				this.detener();
				Object[] options = { "Si", "No" };
				int n = JOptionPane
						.showOptionDialog(
								null,
								"Discuple las molestias. Parece hubo un problema en la simulación.\n"
										+ "Por favor asegurse de que no haya errores en el mapa.\n"
										+ "¿Intetar simular nuevamente?",
								"Problema en la simulación",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.ERROR_MESSAGE, null, options,
								options[1]);
				if (n == 1) {
					intentar = false;
				}

			}
		}

		return 0;
	}

	private ArrayList<Tramo> buscarCamino(Nodo entrada, Nodo salida) {
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos(entrada,
				salida);
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		boolean resul = algoritmoAEstrella.ejecutar();
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		if (resul == false) {
			// no ha sido posible encontrar un camino entre los nodos
			return null;
		} else {
			// Mostrar solución en el mapa
			for (int i = (algoritmoAEstrella.getSolucion().size()); i > 0; i--) {
				if (algoritmoAEstrella.getSolucion().elementAt(i - 1)
						.getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i - 1).getOperador()))
							.getTramoElegido();
					tramos.add(tramoAux);
				}
			}
			return tramos;
		}

	}

	private void crearCaminosDefecto() {
		Iterator<Nodo> it = mapa.getNodos().iterator();
		int min;
		if ((entradas[0] == 0) && (entradas[1] == 0) && (entradas[2] == 0))
			min = -1;
		else
			min = 0;
		Nodo temp;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getEs() == null
					|| temp.getEs().getPorcentajesEntrada()[0] > min
					|| temp.getEs().getPorcentajesEntrada()[1] > min
					|| temp.getEs().getPorcentajesEntrada()[2] > min) {
				Iterator<Nodo> it2 = mapa.getNodos().iterator();
				Nodo temp2;
				while (it2.hasNext()) {
					temp2 = it2.next();
					ArrayList<Tramo> tramos;

					if (temp != temp2) {
						if (false && (caminosDefecto.get(temp2) != null)
								&& (caminosDefecto.get(temp2).get(temp) != null)) {
							tramos = caminosDefecto.get(temp2).get(temp);
							// TODO esta parte del método esta anulada porque para servir tendirmos
							//  que invertir el orden de los tramos en la lista
							if (caminosDefecto.get(temp) == null) {
								Hashtable<Nodo, ArrayList<Tramo>> temp3 = new Hashtable<Nodo, ArrayList<Tramo>>();
								temp3.put(temp2, tramos);
							}
						} else {

							try {
								tramos = buscarCamino(temp, temp2);
								if (tramos != null) {
									if (caminosDefecto.get(temp) == null) {
										Hashtable<Nodo, ArrayList<Tramo>> temp3 = new Hashtable<Nodo, ArrayList<Tramo>>();
										temp3.put(temp2, tramos);
									} else
										caminosDefecto.get(temp).put(temp2,
												tramos);
								}
							} catch (Exception e) {
								e.printStackTrace();
								// problema al buscar un camino, ignorar
							}
						}
					}
				}

			}
		}
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
		if (controladorSim != null) {
			controladorSim.terminar();
			controladorSim = null;
			activa = false;
			vehiculos.clear();
			tabla.clear();
		}
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
		controladorSim.pausar();
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
	private void crearVehiculos(int cant) {
		// TODO crear vehiculos de cada tipo segun la infromacón que hay
		// en los parametros. No se puede hacer ahora porque no se
		// entiende como se guarda esa información

		// Random rand = new Random();
		// param.getPorcentajeTipo();

		for (int i = 0; i < cant; i++) {
			Random r = new Random();
			int aux = r.nextInt(100);
			int[] por = param.getPorcentajeTipo();
			aux = aux - por[0];
			if (aux <= 0)
				vehiculos.add(new Turismo());
			else {
				aux = aux - por[1];
				if (aux <= 0)
					vehiculos.add(new Taxi());
				else {
					aux = aux - por[2];
					if (aux <= 0)
						vehiculos.add(new Camion());
					else {
						aux = aux - por[3];
						if (aux <= 0)
							vehiculos.add(new Bus(mapa.getLineasAutobuses()));
						else {
							aux = aux - por[4];
							if (aux <= 0)
								vehiculos.add(new Moto());
							else {
								aux = aux - por[5];
								if (aux <= 0)
									vehiculos.add(new Ambulancia(hospitales()));
								else {
									vehiculos.add(new Turismo());
								}
							}
						}
					}
				}
			}
		}
	}

	private List<Nodo> hospitales() {
		List<Nodo> res = new ArrayList<Nodo>();
		Iterator<Nodo> it = mapa.getNodos().iterator();
		Nodo temp;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getTipo().getValorTipo().compareTo("hospital") == 0) {
				res.add(temp);
			}
		}
		return res;
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
		reloj.actualizar();
	}

	/**
	 * Método que devuelve un nodo, que será entrada para un coche, según la
	 * necesidad de estos.
	 * <p>
	 * En este método se aprovecha para incrementar el número de coches activos
	 * que hay en el mapa si se le pasa un valor. En caso de no querese nuevos
	 * vehiculos se devolverá null.
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
		// si no hacen falta más coches
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
	 * Método para mejorar la elección de caminos (no solo en función de la
	 * distancia, sino en función del tráfico actual)
	 * 
	 * @param tramo
	 * @return
	 */
	public double densidadTramo(Tramo tramo) {
		ArrayList<Vehiculo> listaVehiculos = tabla.get(tramo);
		int longitudT = tramo.getLargo();
		int numCoches = listaVehiculos.size();
		// Versión simple, realmente debería comprobar el tramo en el sentido en
		// que quiere recorrerlo y no en ambos.
		int numCarriles = tramo.getNumCarrilesDir1()
				+ tramo.getNumCarrilesDir2();
		// Devuelve un valor entre 0 y 1, mayor cuantos más coches haya.
		double densidad = numCoches / (longitudT * numCarriles);
		// Se normaliza con la distancia (devolverá un valor entre 0 y la
		// distancia del tramo).
		densidad = longitudT * densidad;
		return densidad;
	}
}
