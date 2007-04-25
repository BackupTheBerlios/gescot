package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

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
	@SuppressWarnings("unused")
	private ParametrosSimulacion param;

	/**
	 * Alamacena la relaci�n entre tramos y vehiculos en un determinado momento.
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
	
	private int distDelante;

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
		random = new Random();
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
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);
		else {
			controlarCochesDelante(vehiculo);
			
			controlarSe�ales(vehiculo);
			
			if (controlarFinTramo(vehiculo))
				procesarTramoSiguiente(vehiculo);

			procesarCambioCarril(vehiculo);

			recalcularAceleracion(vehiculo);

			recalcularVelocidadYPosicion(vehiculo);
		}
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

		Nodo entrada = sim.getEntrada();
		if (entrada == null)
			return;
		Nodo salida = sim.getSalida();

		// A�adida comprobaci�n (para evitar que el nodo de entrada y de salida
		// sea el mismo, lo cual
		// no es l�gico y adem�s genera problemas adicionales)
		while (salida == entrada) {
			salida = sim.getSalida();
		}

		boolean salir = false;
		if (vehiculo.inicializar(entrada, salida)) {
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
	 * M�todo que controla que el coche no este llegando a fin de tramo.
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
		Tramo tramo1 = vehiculo.getTramo();
		Tramo tramo2 = vehiculo.siguienteTramo();

		if (tramo2 != null) {
			Se�al se�al = vehiculo.getNodoDestino().getSe�al();
			if (se�al != null && se�al.puedePasar(vehiculo, tramo1, tramo2) == 0) {
				// TODO frena demasiado... habria que hacerlo distinto
				//vehiculo.velocidad = 0;
				//return false;
				
				if (distDelante > (1 - vehiculo.getPosicion()) * vehiculo.getTramo().getLargo()) {
					distDelante = (int) ((1 - vehiculo.getPosicion()) * vehiculo.getTramo().getLargo()) - 3;
					velDelante = 0;
				}
				
				
			}
		}
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
				if (vehiculo.getCarril() > tramo.getNumCarrilesDir1()) {
					vehiculo.setCarril(random.nextInt(tramo.getNumCarrilesDir1()));
				}
			} else {
				vehiculo.setNodoDestino(tramo.getNodoInicial());
				if (vehiculo.getCarril() > tramo.getNumCarrilesDir2()) {
					vehiculo.setCarril(random.nextInt(tramo.getNumCarrilesDir2()));
				}
			}
			vehiculo.resetaerPosicion();
		} 
	}


	/**
	 * M�todo para controlar si hay coches delante.
	 * <p>
	 * Este m�todo crea un iterador para recorrer los coches que estan en el
	 * mismo tramo que el que se quiere analizar. Luego, controla uno a uno que
	 * se cumpla que:<br> + no es el mismo que el actual<br> + esta
	 * recorriendo el tramo en el mismo sentido<br> + esta en el mismo carril<br> +
	 * esta delante y no se habia encontrado uno m�s cerca<br>
	 * Si se cumplen las condiciones, guarda la velocidad del coche y la
	 * distancia hasta este.<br>
	 * Este m�todo tambi�n toma en cuenta los coches que esten en el tramo siguiente.<br>
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
		while (iterador.hasNext()) {
			temp = iterador.next();
			if (temp != null && temp != vehiculo) {
				if (temp.getNodoDestino() != null
						&& temp.getNodoDestino() == vehiculo.getNodoDestino()
						&& temp.getCarril() == vehiculo.getCarril())
					if (temp.getPosicion() > vehiculo.getPosicion()
							&& temp.getPosicion() - vehiculo.getPosicion() < distancia) {
						velocidad = temp.getVelocidad();
						distancia = temp.getPosicion() - vehiculo.getPosicion();
					}
			}
		}
		
		if (velocidad == -1.0) {
			Tramo tramo = vehiculo.siguienteTramo();
			if (tramo != null) {
				Double pos = 1 - vehiculo.posicion - (0.1 *  vehiculo.getTramo().getLargo()) / tramo.getLargo();
				iterador = (new ArrayList<Vehiculo>(tabla.get(tramo)))
				.iterator();
				while (iterador.hasNext()) {
					temp = iterador.next();
					if (temp != null && temp != vehiculo) {
						if (temp.getNodoDestino() != null
								&& temp.getNodoOrigen() == vehiculo.getNodoDestino()
								&& temp.getCarril() == vehiculo.getCarril())
							if (temp.getPosicion() + pos < distancia) {
								velocidad = temp.getVelocidad();
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
	 * M�todo que, dada la informaci�n del coche de delanta, determina si
	 * cambiar de carril.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarCambioCarril(Vehiculo vehiculo) {
		// TODO controlar que se cumple alguna condici�n sobre velDelante
		// si cambia de carril, recalcular velDelante
		if (vehiculo.getTramo() == null)
			return;
		if (vehiculo.getNodoDestino() == vehiculo.getTramo().getNodoFinal()
				&& vehiculo.getTramo().getNumCarrilesDir1() <= 1) {
			return;
		}
		if (vehiculo.getNodoDestino() == vehiculo.getTramo().getNodoInicial()
				&& vehiculo.getTramo().getNumCarrilesDir2() <= 1) {
			return;
		}

		if (velDelante < vehiculo.getTramo().getVelMax()
				&& velDelante < vehiculo.getVelocidadMax()) {
			// TODO aqui se cumplen las condiciones para que se tenga que hacer
			// camibo de carril
			// sin embargo, falta verificar que sea posible y que no sea porque
			// los otros coches
			// estan en un sem�foro o algo asi
		}
	}

	/**
	 * M�todo para recalcular la acelearci�n de acuerdo a las condiciones dadas.
	 * <p>
	 * En principio, controla que la velocidad del coche que tiene en frente sea
	 * mayor que la suya. Si es asi y todavia puede ir m�s r�pido y acelerar
	 * m�s, entonces acelera.<br>
	 * Luego, si no hay coches delantes, acelrar� hasta alcanzar la velocidad
	 * m�xima del tramo.<br>
	 * En otros casos, comenzar� a desacelerar.
	 */
	private void recalcularAceleracion(Vehiculo vehiculo) {
		// TODO
		// seg�n se complica la f�sica, podriamos tener en cuenta masas y
		// caracter�sticas f�sicas m�s "sofisticadas"
		// considerar tambi�n la utilizaci�n del m�todo controlarSe�ales,
		// dado que si hay una se�al que obliga al coche adetenerse
		// mas adelante, debe ir frenando
		if (vehiculo.getTramo() == null)
			return;
		if (!puedeEntrar) {
			puedeEntrar = true;
			vehiculo.velocidad = 0;
			vehiculo.aceleracion = 0;
			return;
		}
		if ((velDelante == -1 || distDelante > 30)
				&& vehiculo.getVelocidad() < vehiculo.getTramo().getVelMax()) {
			vehiculo.variarAceleracion(3);
			return;
		}
		if (velDelante > vehiculo.getVelocidad() || distDelante > 20) {
			vehiculo.variarAceleracion(2);
			return;
		}
		if (velDelante <= vehiculo.getVelocidad()) {
			if (vehiculo.getDistanciaSeguridad() < distDelante) {
				vehiculo.variarAceleracion(-25);
			} else if (distDelante < 8){
				vehiculo.variarAceleracion(-12);
			} else if (distDelante < 15) {
				vehiculo.variarAceleracion(-2);
			}
			return;
		}

		vehiculo.variarAceleracion(-1);
	}

	/**
	 * Funcion para evitar que los coches se superpongan.<p>
	 * Esta funci�n indica si hay un coche en el mismo sentido y en el mismo carril, que acabe
	 * de entrar al tramo, para que solo el primero de ellos pueda entrar al tramo.
	 * 
	 * @param vehiculo
	 * @return
	 */
	private synchronized boolean tieneQueEsperar(Vehiculo vehiculo) {
		if (vehiculo.posicion > 0)
			return false;
		iterador = tabla.get(vehiculo.getTramo()).iterator();
		Vehiculo temp;
		while (iterador.hasNext()) {
			temp = iterador.next();
			if (temp != vehiculo) {
				if (temp.nodoDestino == vehiculo.nodoDestino
						&& temp.carril == vehiculo.carril) {
					if (temp.posicion > 0 && temp.posicion * vehiculo.getTramo().getLargo() < 2) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	/**
	 * M�todo para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		if (vehiculo.getTramo() == null)
			return;
		if (tieneQueEsperar(vehiculo)) {
			vehiculo.aceleracion = 0;
			vehiculo.velocidad = 0;
			return;
		}

		vehiculo.actualizarVelocidad();
		vehiculo.posicion += vehiculo.velocidad
				/ vehiculo.getTramo().getLargo();
		// TODO aqui se podr�a verificar si hay accidentes

	}
}
