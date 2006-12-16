package Multithreads.simuladortrafico.is.multithreads.mapa;

import java.util.List;

public class Mapa {
	List<Nodo> nodos;
	List<Tramo> tramos;
	
	public Mapa(List<Nodo> nodos, List<Tramo>tramos) {
		this.nodos = nodos;
		this.tramos = tramos;
		
	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

	public void setTramos(List<Tramo> tramos) {
		this.tramos = tramos;
	}
}
