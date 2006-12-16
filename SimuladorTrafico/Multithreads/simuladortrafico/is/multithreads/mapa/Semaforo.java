/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads.mapa;

/**
 * @author usuario_local
 *
 */
public class Semaforo {
	private boolean valor;
	
	public Semaforo(boolean valor) {
		this.valor = valor;
	};
	
	public void invertir() {
		valor = !valor;
	}
	
	public synchronized boolean getValor() {
		return valor;
	}
}
