package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Tramo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase que, de acuerdo con el patrón Flyweight, agrupa las funciones de
 * procesamiento comunes a todos los vehiculos.
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
		// TODO
		if (vehiculo.getTramo() == null)
			inicializarVehiculo(vehiculo);

		if (controlarFinTramo(vehiculo) && controlarSeñales(vehiculo))
			procesarTramoSiguiente(vehiculo);

		velDelante = controlarCochesDelante(vehiculo);

		procesarCambioCarril(vehiculo);

		recalcularAceleracion(vehiculo);

		recalcularVelocidadYPosicion(vehiculo);
	}

	/**
	 * Este método incializa un vehiculo, esto es, lo ubica en el mapa.
	 * <p>
	 * (Explicar Implementacion)
	 * 
	 * @param vehiculo
	 */
	private void inicializarVehiculo(Vehiculo vehiculo) {
		// TODO tiene que tener en cuenta propiedades del mapa (sim.getMapa())
		// y del vehiculo en si. Tambien puede ser bueno calcular en este
		// momento el camino que debe recorrer.
		// tambien puede ser una buena optimización que si ya tiene un camino,lo
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
	 * Método que controla que el coche no este llegando a fin de tramo.
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
		// TODO
		return true;

	}

	/**
	 * Método para cambiar de tramo una vez que se llega al final del actual.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarTramoSiguiente(Vehiculo vehiculo) {
		// TODO no olvidarse de actualizar la tabla de tramos y vehiculos
		// posiblemente el siguiente tramo se podria pedir al vehiculo, asi se
		// puede sobrecargar para cada uno de los tipos de vehiculos ya que sera
		// muy distinto si es un autobus (que ira por el recorrido) o un camion
		// (que debería elegir calles ampilas),etc.
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
	 * Método para controlar si hay coches delante.
	 * <p>
	 * Este método crea un iterador para recorrer los coches que estan en el
	 * mismo tramo que el que se quiere analizar. Luego, controla uno a uno que
	 * se cumpla que:<br> + no es el mismo que el actual<br> + esta
	 * recorriendo el tramo en el mismo sentido<br> + esta en el mismo carril<br> +
	 * esta delante y no se habia encontrado uno más cerca<br>
	 * Si se cumplen las condiciones, guarda la velocidad del coche y la
	 * distancia hasta este.<br>
	 * Al final devuelve la velocidad encontrada.
	 * </p>
	 * 
	 * @return Double que será la velocidad del coche de delante si hay uno
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
	 * Método que, dada la información del coche de delanta, determina si
	 * cambiar de carril.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void procesarCambioCarril(Vehiculo vehiculo) {
		// TODO controlar que se cumple alguna condición sobre velDelante
		// si cambia de carril, recalcular velDelante
		if (vehiculo.getTramo() == null) return;
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
	 * Método para recalcular la velocidad a la que circula el vehiculo y su
	 * posicion.
	 * <p>
	 * (Explicar implementacion)
	 */
	private void recalcularVelocidadYPosicion(Vehiculo vehiculo) {
		if (vehiculo.getTramo() == null) return;
		//vehiculo.velocidad += vehiculo.aceleracion;
		vehiculo.actualizarVelocidad();
		vehiculo.posicion += vehiculo.velocidad / 40;
		// TODO aqui se podría verificar si hay accidentes
		
	}
}
