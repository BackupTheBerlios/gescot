/**
 * 
 */
package is.SimTraffic.Simulacion;

import java.util.ArrayList;

/**
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
	 * Clase encargada de procesar cada uno de los vehiculos
	 */
	private Inteligencia intel;

	/**
	 * Booleano que indica si se termino de procesar el vehiculo
	 */
	private boolean termino = false;

	public GrupoVehiculos(ArrayList<Vehiculo> vehiculos, int indice, Simulacion sim) {
		lista = new Vehiculo[10];
		for (int i = indice; i < indice + nroVehiculos; i++) {
			lista[i - indice] = vehiculos.get(i);
		}
		intel = new Inteligencia(sim);
	}

	public void run() {
		while (!termino) {
			for (int i = 0; i < nroVehiculos; i++)
				intel.procesar(lista[i]);
			// TODO
		}
	}

	synchronized public void terminar() {
		termino = true;
	}
}
