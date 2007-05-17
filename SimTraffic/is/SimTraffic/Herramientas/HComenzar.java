package is.SimTraffic.Herramientas;

import is.SimTraffic.*;
import is.SimTraffic.Utils.Tiempo;

public class HComenzar implements IHerramienta {

	int horas;
	int minutos;
	int segundos;
	/**
	 */
	public HComenzar(int h, int m, int s) {
		horas = h;
		minutos = m;
		segundos = s;
	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int hacer(IModelo modelo) {
		modelo.getSimulacion().getReloj().traduceATiempo(horas, minutos, segundos);
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
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HComenzar.0")+Messages.getString("HComenzar.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
