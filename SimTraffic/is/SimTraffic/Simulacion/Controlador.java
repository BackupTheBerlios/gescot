package is.SimTraffic.Simulacion;

import java.util.ArrayList;

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
public class Controlador extends Thread {

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
	 * M�todo constructor de la clase, que crea los grupos de vehiculos a partir
	 * de la lista de Vehiculos dada por la simulaci�n.
	 * 
	 * @param listaVehiculo
	 *            Lista de los vehiculos a simular
	 */
	Controlador(ArrayList<Vehiculo> listaVehiculo) {

	}

	public void run() {
		while (!termino) {

		}
	}

	/**
	 * M�todo para finalizar la ejecuci�n del thread.
	 */
	synchronized public void terminar() {
		termino = true;
	}
}
