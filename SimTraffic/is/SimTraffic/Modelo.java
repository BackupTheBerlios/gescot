package is.SimTraffic;

import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Simulacion.IVehiculo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.List;

public class Modelo implements IModelo {
	private Mapa mapa;

	private Simulacion simulacion;

	/**
	 * @roseuid 45B8B3A901D0
	 */
	public Modelo() {
		mapa = new Mapa();
		simulacion = new Simulacion();
	}

	/**
	 * @return Mapa
	 * @roseuid 45C1E1C8009B
	 */
	public Mapa getMapa() {
		return mapa;
	}

	/**
	 * @return java.util.ArrayList
	 * @roseuid 45C1E1C800BB
	 */
	public List<IVehiculo> getVehiculos() {
		return simulacion.getVehiculos();
	}

	/**
	 * @return Simulacion
	 * @roseuid 45C1E1C800CA
	 */
	public Simulacion getSimulacion() {
		return simulacion;
	}

	/**
	 * @param mapa
	 * @roseuid 45C1E1C800E9
	 */
	public void setMapa(Mapa mapa) {
		if (mapa != null)
			this.mapa = mapa;
	}
}
