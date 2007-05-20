package is.SimTraffic.Herramientas;

//import sun.security.krb5.internal.crypto.m;
import is.SimTraffic.*;
import is.SimTraffic.Utils.Tiempo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarYMover;

public class HComenzar implements IHerramienta {

	int horas;
	int minutos;
	int segundos;
	Ventana ventana;
	/**
	 */
	public HComenzar(Ventana ventana, int h, int m, int s) {
		horas = h;
		minutos = m;
		segundos = s;
		this.ventana = ventana;
	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int hacer(IModelo modelo) {
		modelo.getSimulacion().getReloj().traduceATiempo(horas, minutos, segundos);
		modelo.getSimulacion().comenzar(modelo.getMapa());
		//ventana.getEscuchaSeleccionar().desactivar();
		//ventana.setEscuchaSeleccionar(null);
		return 0;
	}

	/**
	 * @param modelo
	 * @return int
	 */
	public int deshacer(IModelo modelo) {
		modelo.getSimulacion().detener();
		//ventana.getEscuchaSeleccionar().activar();
		//ventana.setEscuchaSeleccionar(new MLSeleccionarYMover(modelo));
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HComenzar.0")+Messages.getString("HComenzar.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
