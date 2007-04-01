package is.SimTraffic.Simulacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que extiende Thread e implementa el controlador de la simualción.
 * <p>
 * Esta clase es la encargada de sincronizar el funcionamiento de los distintos
 * grupo de vehiculos, para conseguir el avance de todos ellos de forma regular,
 * asi como su pausa y finalización.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class ControladorSim extends Thread {

	/**
	 * Booleano que indica si se termino la ejecución del thread.
	 */
	private boolean termino = false;

	/**
	 * Lista de los grupos de vehiculos, que son los threads manejados por la
	 * clase
	 */
	private ArrayList<GrupoVehiculos> lista;

	/**
	 * Elemento de sincronización, perimite que todos los threads terminen un
	 * ciclo antes de comenzar el siguiente
	 */
	private CyclicBarrier barrera;

	/**
	 * Simluación actual a la que pertenece el controlador
	 */
	private Simulacion sim;

	/**
	 * Método constructor de la clase, que crea los grupos de vehiculos a partir
	 * de la lista de Vehiculos dada por la simulación.
	 * 
	 * @param listaVehiculo
	 *            Lista de los vehiculos a simular
	 */
	ControladorSim(List<Vehiculo> listaVehiculo, Simulacion sim) {
		int N = calculaNroThread(listaVehiculo.size());
		this.sim = sim;
		lista = new ArrayList<GrupoVehiculos>();
		barrera = new CyclicBarrier(N);
		for (int i = 0; i < N - 1; i++) {
			lista.add(new GrupoVehiculos(listaVehiculo,
					GrupoVehiculos.nroVehiculos * i, sim, barrera));
		}
	}
	
	public void run() {
		Iterator<GrupoVehiculos> it = lista.iterator();
		while (it.hasNext())
			it.next().start();
		while (!termino) {
			try {
				ControladorSim.sleep(50);
				barrera.await();
				sim.actualizar();
			} catch (InterruptedException e) {
				// TODO error porque se paro la espera en este thread
			} catch (BrokenBarrierException e) {
				// TODO error porque se paro la espera en otro thread
			}
		}
	}

	/**
	 * Método que calcula el número de thread de GrupoVehiculo
	 * <p>
	 * Este método calcula el número de threads que hay en la aplicación así
	 * como uno más para el controlador.
	 * 
	 * @param tam
	 *            Número de vehiculos en la simulación
	 * @return Número de threads de la aplicación
	 */
	private int calculaNroThread(int tam) {
		int threads = tam / GrupoVehiculos.nroVehiculos;
		if (tam % GrupoVehiculos.nroVehiculos > 0)
			threads++;
		threads++;
		return threads;
	}

	/**
	 * Método para finalizar la ejecución del thread.
	 */
	synchronized public void terminar() {
		Iterator<GrupoVehiculos> it = lista.iterator();
		while (it.hasNext())
			it.next().terminar();
		lista.clear();
		termino = true;
	}
}
