package is.SimTraffic;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.List;

/**
 * Interface del Modelo del esquema MVC.
 * <p>
 * Esta interface es implementada por el modelo, que mantiene toda la
 * informaci�n de la instancia actual de la apliaci�n.
 * 
 * @author Grupo ISTrafico
 * 
 */
public interface IModelo {

	/**
	 * M�todo para obtener la instancia actual del mapa.
	 * 
	 * @return Mapa Instancia actual del mapa.
	 */
	public Mapa getMapa();

	/**
	 * M�todo para obtener la lista actual de veh�culos en simulaci�n.
	 * 
	 * @return java.util.List Lista con los veh�culos en simulaci�n.
	 */
	public List<Vehiculo> getVehiculos();

	public Simulacion getSimulacion();

	public void setMapa(Mapa mapa);

}
