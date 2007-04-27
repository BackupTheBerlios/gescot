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
import is.SimTraffic.Mapa.Se�ales.Semaforo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

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
	public static int maxVehiculos = 400;

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

					if (temp.getSe�al() != null
							&& temp.getSe�al().getClass() == sem.getClass()) {
						((Semaforo) temp.getSe�al()).setReloj(reloj);
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
								"Discuple las molestias. Parece hubo un problema en la simulaci�n.\n"
										+ "Por favor asegurse de que no haya errores en el mapa.\n"
										+ "�Intetar simular nuevamente?",
								"Problema en la simulaci�n",
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
			// Mostrar soluci�n en el mapa
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
							// TODO esta parte del m�todo esta anulada porque para servir tendirmos
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
	 * M�todo para detener la simulaci�n.
	 * <p>
	 * Este m�todo destruye todas las instancias de vehiculos, con lo cual no se
	 * podr� continuar con la misma simulaci�n.
	 * 
	 * @return Entero que indica el resultado del m�todo:<br>
	 *         0 - ejecuci�n satisfactoria
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
	 * M�todo que detiene temporalmente la simulaci�n y la reanuda si esta
	 * detenida.
	 * <p>
	 * Este m�todo deteniene la simulaci�n, envitando el avance de los coches
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
	 * M�todo para mejorar la elecci�n de caminos (no solo en funci�n de la
	 * distancia, sino en funci�n del tr�fico actual)
	 * 
	 * @param tramo
	 * @return
	 */
	public double densidadTramo(Tramo tramo) {
		ArrayList<Vehiculo> listaVehiculos = tabla.get(tramo);
		int longitudT = tramo.getLargo();
		int numCoches = listaVehiculos.size();
		// Versi�n simple, realmente deber�a comprobar el tramo en el sentido en
		// que quiere recorrerlo y no en ambos.
		int numCarriles = tramo.getNumCarrilesDir1()
				+ tramo.getNumCarrilesDir2();
		// Devuelve un valor entre 0 y 1, mayor cuantos m�s coches haya.
		double densidad = numCoches / (longitudT * numCarriles);
		// Se normaliza con la distancia (devolver� un valor entre 0 y la
		// distancia del tramo).
		densidad = longitudT * densidad;
		return densidad;
	}
}
