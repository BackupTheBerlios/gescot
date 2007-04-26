package is.SimTraffic.Herramientas;

import is.SimTraffic.*;
import is.SimTraffic.Utils.Tiempo;

public class HPausar implements IHerramienta {

	/**
	 */
	public HPausar() {

	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int hacer(IModelo modelo) {
		modelo.getSimulacion().pausar();
		return 0;
	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int deshacer(IModelo modelo) {
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+"Simulación detenida";
		}
}
