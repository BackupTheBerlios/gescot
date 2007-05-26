package is.SimTraffic.Vista.VentanaMatrizPaso;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;

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
	 * Informará del estado en que se encuentra el botón:
	 * 1: Rojo
	 * 0: Verde 
	 */
	int estado;
	
	public BotonDeConexion (int tramoOrigen, int tramoDestino, int numIntervalo, Nodo nodo){
		super();
		this.setSize(new Dimension(10,10));
		
		
		if (((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().getMatrizDePaso()[tramoOrigen][tramoDestino] == 0){
			setBackground(Color.GREEN);
			estado = 1;
		} else {
			setBackground(Color.RED);
			estado = 0;
		}
		
		this.tramoOrigen = tramoOrigen;
		this.tramoDestino = tramoDestino;
	}

	public void cambiaEstado(){
		if (estado == 0){
			estado = 1;
		} else {
			estado = 0;
		}
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
