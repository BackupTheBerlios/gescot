package is.SimTraffic.Herramientas;

import is.SimTraffic.*;

public class HComenzar implements IHerramienta {

	/**
	 */
	public HComenzar() {

	}

	/**
	 * 
	 * @param modelo
	 * @return int
	 */
	public int hacer(IModelo modelo) {
		modelo.getSimulacion().comenzar(modelo.getMapa());
		return 0;
	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int deshacer(IModelo modelo) {
		modelo.getSimulacion().detener();
		return 0;
	}
}
