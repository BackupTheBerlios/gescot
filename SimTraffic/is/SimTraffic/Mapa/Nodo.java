package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private float entrada;

	private float salida;

	private boolean parada;

	public Señal señal;

	private Posicion pos;

	public List<Tramo> tramos;

	/**
	 * @roseuid 45B8B3A80192
	 */
	public Nodo(Posicion pos) {
		this.pos = pos;
		entrada = 0.1f;
		salida = 0.1f;
		parada = false;
		señal = null;
		tramos = new ArrayList<Tramo>();
	}

	/**
	 * @return Posicion
	 * @roseuid 45B8ABD203D4
	 */
	public Posicion getPos() {
		return null;
	}

	/**
	 * @return List
	 */
	public List<Tramo> getTramos() {
		return tramos;
	}

	public boolean equals(Object objeto) {
		if (objeto == null)
			return false;
		if (objeto.getClass() != this.getClass())
			return false;
		Nodo nodo = (Nodo) objeto;
		if (nodo.pos != this.pos)
			return false;
		return true;
	}
	
	public float getEntrada() {
		return entrada;
	}

	public void setEntrada(float entrada) {
		this.entrada = entrada;
	}

	public float getSalida() {
		return salida;
	}

	public void setSalida(float salida) {
		this.salida = salida;
	}
	
	public boolean getParada() {
		return true;
	}

	public void setParada(boolean parada) {
		this.parada = parada;
	}

	public void setSeñal(Señal señal) {
		this.señal = señal;
	}

	public Señal getSeñal() {
		return señal;
	}
}
