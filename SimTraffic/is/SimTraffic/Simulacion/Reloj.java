package is.SimTraffic.Simulacion;

import java.io.Serializable;


/**
 * Controla el tiempo global de la simulación. Sólo existirá un
 * único reloj por simulación.
 * @author grupo IS Tráfico
 */
public class Reloj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342545297592950862L;
	private long tiempo;
	private int cont;
	public Reloj(long tiempo) {
		this.tiempo = tiempo;
		this.cont = 0;
	}

	public Reloj() {
		tiempo = 0;
	}

	/**
	 * Pasar un ciclo de reloj.
	 */
	public void actualizar(){
		cont++;
		if (cont > 10) {
			tiempo = tiempo + 1;
			cont = 0;
		}
	}
	
	public synchronized long getTiempo(){
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
}
