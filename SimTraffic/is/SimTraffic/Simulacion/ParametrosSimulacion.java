package is.SimTraffic.Simulacion;

import java.util.ArrayList;

/**
 * Clase que almacen los parámetros de la simulación en un determinado momento.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class ParametrosSimulacion {
	/**
	 * Entero que representa el tipo de clima
	 */
	private int clima;

	/**
	 * Numero de vehiculos que se deben simular
	 */
	private int numVehiculos;

	/**
	 * Float que representa la hora del dia
	 */
	private float hora;

	/**
	 * Entero que indica el estado en el que se encuentran los coches
	 */
	private int estadoCoches;

	/**
	 * porcentaje de coches que hay de cada tipo en la simulacion actual
	 */
	private ArrayList porcentajeTipo;

	public ParametrosSimulacion() {

	}

	public synchronized int getClima() {
		return this.clima;
	}

	public int getNumVehiculos() {
		return this.numVehiculos;
	}

	public synchronized float getHora() {
		return this.hora;
	}

	public synchronized int getEstadoCoches() {
		return this.estadoCoches;
	}

	public ArrayList getPorcentajeTipo() {
		return this.porcentajeTipo;
	}
}
