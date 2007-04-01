package is.SimTraffic.Simulacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que extiende Thread e implementa el controlador de la simualci�n.
 * <p>
 * Esta clase es la encargada de sincronizar el funcionamiento de los distintos
 * grupo de vehiculos, para conseguir el avance de todos ellos de forma regular,
 * asi como su pausa y finalizaci�n.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class ControladorSim extends Thread {

	/**
	 * Booleano que indica si se termino la ejecuci�n del thread.
	 */
	private boolean termino = false;

	/**
	 * Lista de los grupos de vehiculos, que son los threads manejados por la
	 * clase
	 */
	private ArrayList<GrupoVehiculos> lista;

	/**
	 * Elemento de sincronizaci�n, perimite que todos los threads terminen un
	 * ciclo antes de comenzar el siguiente
	 */
	private CyclicBarrier barrera;

	/**
	 * Simluaci�n actual a la que pertenece el controlador
	 */
	private Simulacion sim;

	/**
	 * M�todo constructor de la clase, que crea los grupos de vehiculos a partir
	 * de la lista de Vehiculos dada por la simulaci�n.
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
	 * M�todo que calcula el n�mero de thread de GrupoVehiculo
	 * <p>
	 * Este m�todo calcula el n�mero de threads que hay en la aplicaci�n as�
	 * como uno m�s para el controlador.
	 * 
	 * @param tam
	 *            N�mero de vehiculos en la simulaci�n
	 * @return N�mero de threads de la aplicaci�n
	 */
	private int calculaNroThread(int tam) {
		int threads = tam / GrupoVehiculos.nroVehiculos;
		if (tam % GrupoVehiculos.nroVehiculos > 0)
			threads++;
		threads++;
		return threads;
	}

	/**
	 * M�todo para finalizar la ejecuci�n del thread.
	 */
	synchronized public void terminar() {
		Iterator<GrupoVehiculos> it = lista.iterator();
		while (it.hasNext())
			it.next().terminar();
		lista.clear();
		termino = true;
	}
}
