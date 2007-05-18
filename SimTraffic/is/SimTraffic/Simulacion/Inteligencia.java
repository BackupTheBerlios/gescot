package is.SimTraffic.Simulacion;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;


/**
 * Clase que, de acuerdo con el patrón Flyweight, agrupa las funciones de
 * procesamiento comunes a todos los vehiculos para un grupo de estos.
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

	private Iterator<Vehiculo> iterador;

	Random random;

	/**
	 * Double que en cada proceso almacena la velocidad del coche de delante o
	 * -1 si no hay ninguno cerca
	 */
	private double velDelante;

	boolean puedeEntrar = true;

	private double distDelante;
	
	private double acelDelante;

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
		random = new Random();
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
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);
		else {
			controlarCochesDelante(vehiculo);

			controlarSeñales(vehiculo);

			if (controlarFinTramo(vehiculo))
				procesarTramoSiguiente(vehiculo);

			procesarCambioCarril(vehiculo);

			recalcularAceleracion(vehiculo);

			recalcularVelocidadYPosicion(vehiculo);
		}
	}

	/**
	 * Este método incializa un vehiculo, esto es, lo ubica en el mapa.
	 * <p>
	 * (Explicar Implementacion)
	 * 
	 * @param vehiculo
	 */
	private void inicializarVehiculo(Vehiculo vehiculo) {
		// TODO Puede ser una buena optimización que si ya tiene un camino lo
		// vuelva
		// a recorrer que con una probabilidad dada

		Nodo entrada = sim.getEntrada();
		if (entrada == null)
			return;
		Nodo salida = sim.getSalida();
		if (salida == null)
			return;
		// Añadida comprobación (para evitar que el nodo de entrada y de salida
		// sea el mismo, lo cual no es lógico y además genera problemas). Sin en
		// un numero finito de intento no consigue se sean distintos los nodos,
		// no incializa el vehiculo.
		int cont = 0;
		while (salida == entrada) {
			salida = sim.getSalida();
			cont++;
			if (cont > 10)
				return;
		}

		boolean salir = false;
		if (vehiculo.inicializar(entrada, salida)) {
			entrada = vehiculo.getNodoEntrada();
			vehiculo.setTramo(vehiculo.siguienteTramo());
			tabla.get(vehiculo.getTramo()).add(vehiculo);
			vehiculo.resetaerPosicion();
			vehiculo.setNodoOrigen(entrada);
			
			if (vehiculo.getTramo().getNodoInicial() == entrada) {
				vehiculo.setNodoDestino(vehiculo.getTramo().getNodoFinal());
				if (vehiculo.getTramo().getNumCarrilesDir1() == 0) {
					salir = true;
				}
				vehiculo.setCarril(random.nextInt(vehiculo.getTramo()
						.getNumCarrilesDir1()) + 1);
			} else {
				vehiculo.setNodoDestino(vehiculo.getTramo().getNodoInicial());
				if (vehiculo.getTramo().getNumCarrilesDir2() == 0) {
					salir = true;
				}
				vehiculo.setCarril(random.nextInt(vehiculo.getTramo()
						.getNumCarrilesDir2()) + 1);

			}
		} else
			sim.saleVehiculo();

		if (salir) {
			vehiculo.setTramo(null);
			vehiculo.setNodoDestino(null);
			vehiculo.setNodoOrigen(null);
			sim.saleVehiculo();
		}
	}

	/**
	 * Método que controla que el coche no este llegando a fin de tramo.
	 * <p>
	 * (Explicar implmentacion)
	 * 
	 * @return booleano que sera verdadero si ya se llego al fin del tramo
	 */
	private boolean controlarFinTramo(Vehiculo vehiculo) {
		if (vehiculo.getPosicion() > 1)
			vehiculo.posicion = 1;
		if (vehiculo.getTramo() == null)
			return false;
		if ((1 - vehiculo.getPosicion()) * vehiculo.getTramo().getLargo() < 2)
			return true;
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
		Tramo tramo1 = vehiculo.getTramo();
		Tramo tramo2 = vehiculo.siguienteTramo();

		if (tramo2 != null) {
			Señal señal = vehiculo.getNodoDestino().getSeñal();
			if (señal != null && señal.puedePasar(vehiculo, tramo1, tramo2) > 0) {
				// TODO frena demasiado... habria que hacerlo distinto
				// vehiculo.velocidad = 0;
				// return false;

				if (distDelante > (1 - vehiculo.getPosicion())
						* vehiculo.getTramo().getLargo()) {
					distDelante = (int) ((1 - vehiculo.getPosicion()) * vehiculo
							.getTramo().getLargo()) - 3;
					velDelante = 0;
				}

			}
		}
		return true;

	}

	/**
	 * Método para cambiar de tramo una vez que se llega al final del actual.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarTramoSiguiente(Vehiculo vehiculo) {
		// El camino esta prefijado en la inicialización, no se deben hacer
		// consideraciones aqui
		if (vehiculo.getTramo() == null)
			return;

		Tramo tramo = vehiculo.siguienteTramo();
		// en el caso de que el coche tenga que salir
		if (tramo == null) {
			sim.saleVehiculo();
			vehiculo.setNodoDestino(null);
			vehiculo.setNodoOrigen(null);
			tabla.get(vehiculo.getTramo()).remove(vehiculo);
			vehiculo.setTramo(null);
			return;
		}

		if (distDelante > 0.2) {
			tabla.get(vehiculo.getTramo()).remove(vehiculo);
			vehiculo.setTramo(tramo);
			tabla.get(tramo).add(vehiculo);

			vehiculo.setNodoOrigen(vehiculo.getNodoDestino());
			if (tramo.getNodoInicial() == vehiculo.getNodoOrigen()) {
				vehiculo.setNodoDestino(tramo.getNodoFinal());
				while (vehiculo.getCarril() > tramo.getNumCarrilesDir1()) {
					vehiculo.setCarril(vehiculo.getCarril() - 1);
				}
			} else {
				vehiculo.setNodoDestino(tramo.getNodoInicial());
				while (vehiculo.getCarril() > tramo.getNumCarrilesDir2()) {
					vehiculo.setCarril(vehiculo.getCarril() - 1);
				}
			}
			vehiculo.resetaerPosicion();
		}
	}

	/**
	 * Método para controlar si hay coches delante.
	 * <p>
	 * Este método crea un iterador para recorrer los coches que estan en el
	 * mismo tramo que el que se quiere analizar. Luego, controla uno a uno que
	 * se cumpla que:<br> + no es el mismo que el actual<br> + esta
	 * recorriendo el tramo en el mismo sentido<br> + esta en el mismo carril<br> +
	 * esta delante y no se habia encontrado uno más cerca<br>
	 * Si se cumplen las condiciones, guarda la velocidad del coche y la
	 * distancia hasta este.<br>
	 * Este método también toma en cuenta los coches que esten en el tramo
	 * siguiente.<br>
	 * Al final devuelve la velocidad encontrada.
	 * </p>
	 * 
	 */
	private synchronized void controlarCochesDelante(Vehiculo vehiculo) {
		// TODO
		if (vehiculo.tramo == null)
			return;
		iterador = (new ArrayList<Vehiculo>(tabla.get(vehiculo.tramo)))
				.iterator();

		Vehiculo temp = null;
		double velocidad = -1.0;
		double distancia = 1.0;
		acelDelante = 0;
		while (iterador.hasNext()) {
			temp = iterador.next();
			if (temp != null && temp != vehiculo) {
				if (temp.getNodoDestino() != null
						&& temp.getNodoDestino() == vehiculo.getNodoDestino()
						&& temp.getCarril() == vehiculo.getCarril())
					if (temp.getPosicion() > vehiculo.getPosicion()
							&& temp.getPosicion() - vehiculo.getPosicion() < distancia) {
						velocidad = temp.getVelocidad();
						acelDelante = temp.getAceleracion();
						distancia = temp.getPosicion() - vehiculo.getPosicion();
					}
			}
		}

		if (velocidad == -1.0) {
			Tramo tramo = vehiculo.siguienteTramo();
			int carril = vehiculo.getCarril();
			if (tramo != null) {
				if (tramo.getNodoInicial() == vehiculo.getNodoDestino()) {
					while (carril > tramo.getNumCarrilesDir1() && carril > 0)
						carril--;
				}
				else {
					while (carril > tramo.getNumCarrilesDir2() && carril > 0)
						carril--;
				}
				Double pos = 1 - vehiculo.posicion
						- (0.0 * vehiculo.getTramo().getLargo())
						/ tramo.getLargo();
				iterador = (new ArrayList<Vehiculo>(tabla.get(tramo)))
						.iterator();
				while (iterador.hasNext()) {
					temp = iterador.next();
					if (temp != null && temp != vehiculo) {
						if (temp.getNodoDestino() != null
								&& temp.getNodoOrigen() == vehiculo
										.getNodoDestino()
								&& temp.getCarril() == carril)
							if (temp.getPosicion() + pos < distancia) {
								velocidad = temp.getVelocidad();
								acelDelante = temp.getAceleracion();
								distancia = temp.getPosicion() + pos;
							}
					}
				}

			}
		}
		velDelante = velocidad;
		distDelante = (int) (distancia * vehiculo.getTramo().getLargo());

	}

	/**
	 * Método que, dada la información del coche de delanta, determina si
	 * cambiar de carril.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarCambioCarril(Vehiculo vehiculo) {
		// TODO controlar que se cumple alguna condición sobre velDelante
		// si cambia de carril, recalcular velDelante
		if (vehiculo.getTramo() == null)
			return;
		
		if (vehiculo.getUltimoCambioCarril() < 10)
			return;
		
		int carriles_tramo = 0;
		if (vehiculo.getNodoDestino() == vehiculo.getTramo().getNodoFinal()) {
			carriles_tramo = vehiculo.getTramo().getNumCarrilesDir1();
		}
		if (vehiculo.getNodoDestino() == vehiculo.getTramo().getNodoInicial()) {
			carriles_tramo = vehiculo.getTramo().getNumCarrilesDir2();
		}
		if (carriles_tramo <= 1)
			return;

		if (velDelante != -1 && velDelante < vehiculo.getTramo().getVelMax()
				&& velDelante < vehiculo.getVelocidadMax() && distDelante < 30
				&& vehiculo.getVelocidad() > 0.4) {
			// TODO aqui se cumplen las condiciones para que se tenga que hacer
			// camibo de carril
			// sin embargo, falta verificar que sea posible y que no sea porque
			// los otros coches
			// estan en un semáforo o algo asi
			if (vehiculo.getCarril() < carriles_tramo
					&& pudeCambiar(vehiculo, vehiculo.getCarril() + 1)) {
				vehiculo.setCarril(vehiculo.getCarril() + 1);
				return;
			}
			if (vehiculo.getCarril() > 1
					&& pudeCambiar(vehiculo, vehiculo.getCarril() - 1)) {
				vehiculo.setCarril(vehiculo.getCarril() - 1);
				return;
			}

		}
	}

	private boolean pudeCambiar(Vehiculo vehiculo, int carril) {
		iterador = (new ArrayList<Vehiculo>(tabla.get(vehiculo.tramo)))
				.iterator();

		Vehiculo temp = null;
		double distanciadelante = 1.0;
		double distanciadetras = -1.0;
		while (iterador.hasNext()) {
			temp = iterador.next();
			if (temp != null && temp != vehiculo) {
				if (temp.getNodoDestino() != null
						&& temp.getNodoDestino() == vehiculo.getNodoDestino()
						&& temp.getCarril() == carril)
					if (temp.getPosicion() > vehiculo.getPosicion()
							&& temp.getPosicion() - vehiculo.getPosicion() < distanciadelante) {
						distanciadelante = temp.getPosicion() - vehiculo.getPosicion();
					}
				    if (temp.getPosicion() < vehiculo.getPosicion() && temp.getPosicion() - vehiculo.getPosicion() > distanciadetras) {
				    	distanciadetras = temp.getPosicion() - vehiculo.getPosicion();
				    }
			}
		}
		distanciadelante = distanciadelante * vehiculo.getTramo().getLargo();
		distanciadetras = distanciadetras * vehiculo.getTramo().getLargo();
		if (distanciadelante > 10 && distanciadetras < -10)
			return true;
		else
			return false;
	}

	/**
	 * Método para recalcular la acelearción de acuerdo a las condiciones dadas.
	 * <p>
	 * En principio, controla que la velocidad del coche que tiene en frente sea
	 * mayor que la suya. Si es asi y todavia puede ir más rápido y acelerar
	 * más, entonces acelera.<br>
	 * Luego, si no hay coches delantes, acelrará hasta alcanzar la velocidad
	 * máxima del tramo.<br>
	 * En otros casos, comenzará a desacelerar.
	 */
	private void recalcularAceleracion(Vehiculo vehiculo) {
		// TODO
		// según se complica la física, podriamos tener en cuenta masas y
		// características físicas más "sofisticadas"
		// considerar también la utilización del método controlarSeñales,
		// dado que si hay una señal que obliga al coche adetenerse
		// mas adelante, debe ir frenando
		if (vehiculo.getTramo() == null)
			return;
		if (!puedeEntrar) {
			puedeEntrar = true;
			vehiculo.velocidad = 0;
			vehiculo.aceleracion = 0;
			return;
		}
		if ((velDelante == -1 || distDelante > 35)
				&& vehiculo.getVelocidad() < vehiculo.getTramo().getVelMax()) {
			vehiculo.variarAceleracion(30);
			return;
		}
		if (velDelante > vehiculo.getVelocidad() || distDelante > 25) {
			vehiculo.variarAceleracion(15);
			return;
		}
		if (velDelante <= vehiculo.getVelocidad()) {
			/*if (vehiculo.getDistanciaSeguridad() < distDelante) {
				vehiculo.variarAceleracion(-25);
			} else if (distDelante < 8) {
				vehiculo.variarAceleracion(-12);
			} else if (distDelante < 15) {
				vehiculo.variarAceleracion(-2);
			}*/
			if (distDelante < vehiculo.getDistanciaSeguridad() + 5 ) {
				vehiculo.velocidad = velDelante/1.2;
				vehiculo.aceleracion = acelDelante/1.2;
				//vehiculo.variarAceleracion(-80);
			}
			else if (distDelante < 20) {
				vehiculo.variarAceleracion((int)(- 60 / (distDelante - 5 - vehiculo.getDistanciaSeguridad())));
			}
			return;
		}

		vehiculo.variarAceleracion(-1);
	}

	/**
	 * Funcion para evitar que los coches se superpongan.
	 * <p>
	 * Esta función indica si hay un coche en el mismo sentido y en el mismo
	 * carril, que acabe de entrar al tramo, para que solo el primero de ellos
	 * pueda entrar al tramo.
	 * 
	 * @param vehiculo
	 * @return
	 */
	private synchronized boolean tieneQueEsperar(Vehiculo vehiculo) {
		if (vehiculo.posicion > 0)
			return false;
		if (vehiculo.nodoEntrada != vehiculo.nodoOrigen)
			return false;
		ArrayList<Vehiculo> lista = tabla.get(vehiculo.getTramo());
		int cont = 0;
		Vehiculo temp;
		while (cont < lista.size()) {
			temp = lista.get(cont);
			if (temp != vehiculo) {
				if (temp.nodoDestino == vehiculo.nodoDestino
						&& temp.carril == vehiculo.carril) {
					if (temp.posicion > 0
							&& temp.posicion * vehiculo.getTramo().getLargo() < vehiculo.distanciaSeguridad + 4) {
						return true;
					}
				}

			}
			cont++;
		}
		return false;
	}

	/**
	 * Método para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		if (vehiculo.getTramo() == null)
			return;
		
		//	Si es un autobus y llega a una parada tendra q esperar un tiempo
		if (vehiculo.getNombre()==Messages.getString("Inteligencia.1")){ //$NON-NLS-1$
			 ((Bus)vehiculo).parada();		  
		}
		
		if (tieneQueEsperar(vehiculo)) {
			vehiculo.aceleracion = 0;
			vehiculo.velocidad = 0;
			return;
		}
		
		vehiculo.actualizarVelocidad();
		vehiculo.posicion += vehiculo.velocidad
				/ vehiculo.getTramo().getLargo();
		// TODO aqui se podría verificar si hay accidentes

	}
}
