package is.SimTraffic.Vista.VentanaMatrizPaso;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class BotonDeConexion extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int tramoOrigen;
	int tramoDestino;
	
	/**
	 * Informar� del estado en que se encuentra el bot�n:
	 * 1: Rojo
	 * 0: Verde 
	 */
	int estado;
	
	public BotonDeConexion (int tramoOrigen, int tramoDestino, int numIntervalo, Nodo nodo){
		super();
		this.setSize(new Dimension(10,10));
		estado = 0;
		
		if (((Semaforo)nodo.getSe�al()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().getMatrizDePaso()[tramoOrigen][tramoDestino] == 0){
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.RED);
		}
		
		this.tramoOrigen = tramoOrigen;
		this.tramoDestino = tramoDestino;
	}

	public int getTramoDestino() {
		return tramoDestino;
	}

	public void setTramoDestino(int tramoDestino) {
		this.tramoDestino = tramoDestino;
	}

	public int getTramoOrigen() {
		return tramoOrigen;
	}

	public void setTramoOrigen(int tramoOrigen) {
		this.tramoOrigen = tramoOrigen;
	}
}
