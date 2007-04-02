package is.SimTraffic.Simulacion;

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
		// TODO
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);

		if (controlarFinTramo(vehiculo) && controlarSe�ales(vehiculo))
			procesarTramoSiguiente(vehiculo);

		velDelante = controlarCochesDelante(vehiculo);

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
		int i = random.nextInt(sim.getMapa().getTramos().size());
		vehiculo.setTramo(sim.getMapa().getTramos().get(i));
		tabla.get(vehiculo.getTramo()).add(vehiculo);
		vehiculo.setNodoOrigen(vehiculo.getTramo().getNodoInicial());
		vehiculo.setNodoDestino(vehiculo.getTramo().getNodoFinal());
		vehiculo.resetaerPosicion();
		vehiculo.setCarril(random.nextInt(vehiculo.getTramo().getNumCarrilesDir1()) +1);
	}

	/**
	 * M�todo que controla que el coche no este llegando a fin de tramo.
	 * <p>
	 * (Explicar implmentacion)
	 * 
	 * @return booleano que sera verdadero si ya se llego al fin del tramo
	 */
	private boolean controlarFinTramo(Vehiculo vehiculo) {
		// TODO temporalmente controla que no supere el 98% de la dist del tramo
		if (vehiculo.getTramo() == null) return false;
		if (vehiculo.getPosicion() > 0.98)
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
		if (vehiculo.getTramo() == null) return;
		
		int i = random.nextInt(vehiculo.getNodoDestino().getTramos().size());
		tabla.get(vehiculo.getTramo()).remove(vehiculo);
		vehiculo.setTramo(vehiculo.getNodoDestino().getTramos().get(i));
		tabla.get(vehiculo.getTramo()).add(vehiculo);
		
		vehiculo.setNodoOrigen(vehiculo.getNodoDestino());
		if (vehiculo.getTramo().getNodoInicial() == vehiculo.getNodoOrigen()) {
			vehiculo.setNodoDestino(vehiculo.getTramo().getNodoFinal());
		} else
			vehiculo.setNodoDestino(vehiculo.getTramo().getNodoInicial());
		
		vehiculo.resetaerPosicion();
		vehiculo.aceleracion = 0;
		vehiculo.velocidad = 0;

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
	 * Al final devuelve la velocidad encontrada.
	 * </p>
	 * 
	 * @return Double que ser� la velocidad del coche de delante si hay uno
	 *         cerca y -1 en otro caso
	 */
	private synchronized double controlarCochesDelante(Vehiculo vehiculo) {
		// TODO verificar correcto funcionamiento, probar empiricamente si 0.4
		// esta bien como constante de acercamiento
		if (vehiculo.tramo == null)
			return -1;
		iterador = tabla.get(vehiculo.tramo).iterator();
		
		Vehiculo temp = null;
		double velocidad = -1.0;
		double distancia = 0.4;
		while (iterador.hasNext()) {
			temp = iterador.next();
			if (temp != vehiculo) {
				if (temp.getNodoDestino() == vehiculo.getNodoDestino()
						&& temp.getCarril() == temp.getCarril())
					if (temp.getPosicion() > vehiculo.getPosicion()
							&& temp.getPosicion() - vehiculo.getPosicion() < distancia) {
						velocidad = temp.getVelocidad();
						distancia = temp.getPosicion() - vehiculo.getPosicion();
					}
			}

		}
		return velocidad;
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
		if (vehiculo.getTramo() == null) return;
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
		if (vehiculo.getTramo() == null) return;
		if (velDelante > vehiculo.getVelocidad()) {
			vehiculo.variarAceleracion(2);
			return;
		}
		if (velDelante == -1
				&& vehiculo.getVelocidad() < vehiculo.getTramo().getVelMax()) {
			vehiculo.variarAceleracion(5);
			return;
		}
		if (velDelante < vehiculo.getVelocidad()) {
			vehiculo.variarAceleracion(-5);
			return;
		}
		vehiculo.variarAceleracion(-1);
	}

	/**
	 * M�todo para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		if (vehiculo.getTramo() == null) return;
		//vehiculo.velocidad += vehiculo.aceleracion;
		vehiculo.actualizarVelocidad();
		vehiculo.posicion += vehiculo.velocidad / 40;
		// TODO aqui se podr�a verificar si hay accidentes
		
	}
}
