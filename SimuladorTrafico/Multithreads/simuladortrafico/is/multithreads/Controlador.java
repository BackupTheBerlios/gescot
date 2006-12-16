/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import Multithreads.simuladortrafico.is.multithreads.mapa.Semaforo;

/**
 * @author Grupo IS Tráfico
 * 
 */
public class Controlador {
	/**
	 * 
	 */
	private int cuentaCoches;

	/**
	 * 
	 */
	private int numeroCoches;

	/**
	 * 
	 */
	private int frame;

	private Ventana ventana;
	
	private Semaforo paso;
	/**
	 * @param numeroCoches
	 */
	Controlador(int numeroCoches, Ventana ventana, Semaforo paso) {
		this.numeroCoches = numeroCoches;
		cuentaCoches = 0;
		frame = 0;
		this.ventana = ventana;
		this.paso = paso;
	}

	/**
	 * 
	 */
	public synchronized void incCuentaChoches() {
		cuentaCoches++;
		if (cuentaCoches == numeroCoches) {
			frame++;
			cuentaCoches = 0;
			
			if (frame % 800 == 0) paso.invertir();
			//System.out.print("frame: " + frame + ", tiempo: "
			//		+ System.currentTimeMillis() + "\n");
			ventana.repaint();
		}
	}

	/**
	 * @param frame
	 * @return
	 */
	public synchronized boolean getBlock(int frame) {
		if (this.frame == frame)
			return true;
		return false;
	}
}
