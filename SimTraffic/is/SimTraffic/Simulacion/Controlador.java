package is.SimTraffic.Simulacion;

import java.util.ArrayList;

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
public class Controlador extends Thread {

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
	 * Método constructor de la clase, que crea los grupos de vehiculos a partir
	 * de la lista de Vehiculos dada por la simulación.
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
	 * Método para finalizar la ejecución del thread.
	 */
	synchronized public void terminar() {
		termino = true;
	}
}
