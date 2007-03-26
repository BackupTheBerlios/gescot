package is.SimTraffic;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.List;

/**
 * Interface del Modelo del esquema MVC.
 * <p>
 * Esta interface es implementada por el modelo, que mantiene toda la
 * información de la instancia actual de la apliación.
 * 
 * @author Grupo ISTrafico
 * 
 */
public interface IModelo {

	/**
	 * Método para obtener la instancia actual del mapa.
	 * 
	 * @return Mapa Instancia actual del mapa.
	 */
	public Mapa getMapa();

	/**
	 * Método para obtener la lista actual de vehículos en simulación.
	 * 
	 * @return java.util.List Lista con los vehículos en simulación.
	 */
	public List<Vehiculo> getVehiculos();

	public Simulacion getSimulacion();

	public void setMapa(Mapa mapa);

}
