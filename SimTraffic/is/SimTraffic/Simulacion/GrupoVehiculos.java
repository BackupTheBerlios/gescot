package is.SimTraffic.Simulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que procesa un grupo de vehiculos.
 * <p>
 * Esta clase extiende la clase Thread, permitiendo procear un grupo de
 * vehiculos en paralelo con otro. Se utiliza esta estructura por cuestiones de
 * eficiencia. El nro de vehiculos se define como una constante, establecida por
 * experimentación en un valor óptimo.<br>
 * Esta clase crea su propia instancia de la inteligencia, donde se realiza el
 * procesamiento real de los vehiculos considerando su entorno.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class GrupoVehiculos extends Thread {
	/**
	 * Número de vehiculos administrados por un thread
	 */
	public static int nroVehiculos = 10;

	/**
	 * Lista de vehiculos administrados por el thread
	 */
	private Vehiculo[] lista;

	/**
	 * Clase encargada de procesar cada uno de los vehículos
	 */
	private Inteligencia intel;

	/**
	 * Booleano que indica si se terminó de procesar el vehículo
	 */
	private boolean termino = false;

	/**
	 * Elemento de sincronización, perimite que todos los threads terminen un
	 * ciclo antes de comenzar el siguiente
	 */
	private CyclicBarrier barrera;

	/**
	 * Método constructor de la clase.
	 * <p>
	 * Este método crea la "inteligencia" que procesará los coches e inicializa
	 * la lista de vehiculos.
	 * 
	 * @param vehiculos
	 *            ArrayList con todos los vehiculos de la simulación
	 * @param indice
	 *            Indice del primer vehiculo en el ArrayList perteneciente al
	 *            grupo
	 * @param sim
	 *            Simulación que se está ejecutando
	 */
	public GrupoVehiculos(List<Vehiculo> vehiculos, int indice,
			Simulacion sim, CyclicBarrier barrera) {
		lista = new Vehiculo[nroVehiculos];
		for (int i = indice; i < indice + nroVehiculos; i++) {
			lista[i - indice] = vehiculos.get(i);
		}
		intel = new Inteligencia(sim);
		this.barrera = barrera;
	}

	public void run() {
		while (!termino) {
			intel.actualizarInteligencia();
			System.out.println("procesando coche");
			for (int i = 0; i < nroVehiculos; i++)
				intel.procesar(lista[i]);
			// TODO
			try {
				barrera.await();
			} catch (InterruptedException e) {
				// TODO error porque se paro la espera en este thread
			} catch (BrokenBarrierException e) {
				// TODO error porque se paro la espera en otro thread
			}
		}
	}

	/**
	 * Método que finaliza la ejecución del thread.
	 */
	synchronized public void terminar() {
		termino = true;
	}
}
