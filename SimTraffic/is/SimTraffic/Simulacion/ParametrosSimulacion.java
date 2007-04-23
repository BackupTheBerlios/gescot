package is.SimTraffic.Simulacion;

/**
 * Clase que almacena los parámetros de la simulación en un determinado momento.
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
	 * Numero de vehiculos que se deben simular en cada franja horaria
	 */
	private int[] numVehiculos = new int[3];

	/**
	 * Float que representa la hora del dia
	 */
	private float hora;

	/**
	 * Entero que indica el estado en el que se encuentran los coches
	 */
	private int estadoCoches;

	/**
	 * porcentaje de coches que hay de cada tipo en la simulacion actual.<br>
	 * Los valores deben estar normalizados para sumar 100, los valores se corresponden con:
	 * turismos, taxis, camiones, autobuses, motos, ambulancias
	 */
	private int[] porcentajeTipo;

	public ParametrosSimulacion() {
		clima = 1;
		numVehiculos[0] = 100;
		numVehiculos[1] = 100;
		numVehiculos[2] = 100;
		hora = 0;
		estadoCoches = 0;
		porcentajeTipo = new int[6];
	}

	public synchronized int getClima() {
		return this.clima;
	}

	public void setNumVehiculos(int[] numVehiculos) {
		this.numVehiculos = numVehiculos;
	}
	
	public int[] getNumVehiculos() {
		return this.numVehiculos;
	}

	public synchronized float getHora() {
		return this.hora;
	}

	public synchronized int getEstadoCoches() {
		return this.estadoCoches;
	}

	public void setPorcentajeTipo(int[] porcent) {
		this.porcentajeTipo = porcent;
	}
	
	public int[] getPorcentajeTipo() {
		return this.porcentajeTipo;
	}
}
