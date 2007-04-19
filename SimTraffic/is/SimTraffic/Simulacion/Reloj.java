package is.SimTraffic.Simulacion;


/**
 * Controla el tiempo global de la simulaci�n. S�lo existir� un
 * �nico reloj por simulaci�n.
 * @author grupo IS Tr�fico
 */
public class Reloj {
	private long tiempo;
	
	public Reloj(long tiempo) {
		this.tiempo = tiempo;
	}

	public Reloj() {
		tiempo = 0;
	}

	/**
	 * Pasar un ciclo de reloj.
	 */
	public void actualizar(){
		tiempo = tiempo + 1;
	}
	
	public long getTiempo(){
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
}
