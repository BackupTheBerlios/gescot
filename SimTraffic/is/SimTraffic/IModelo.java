package is.SimTraffic;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.List;


/**
 * @author Grupo ISTrafico
 *
 */
public interface IModelo {

	/**
	 * M�todo para obtener la instancia actual del mapa.
	 * @return Mapa
	 * Instancia actual del mapa.
	 * @roseuid 45B8A8B60376
	 */
	public Mapa getMapa();

	/**
	 * M�todo para obtener la lista actual de veh�culos en simulaci�n.
	 * @return java.util.List
	 * Lista con los veh�culos en simulaci�n.
	 * @roseuid 45B8A8C60357
	 */
	public List<Vehiculo> getVehiculos();

	/**
	 * @return Simulacion
	 * @roseuid 45B8A94C01C1
	 */
	public Simulacion getSimulacion();

	/**
	 * @param mapa
	 * @roseuid 45B8A95A0347
	 */
	public void setMapa(Mapa mapa);
	
}
