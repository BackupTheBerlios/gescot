package is.SimTraffic.Vista.Sugerencias;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class Flecha 
{

	private Nodo nodo;
	private Tramo tramoOrigen;
	private Tramo tramoDestino;

	public Flecha(Nodo nodo, Tramo tramoOrigen, Tramo tramoDestino) 
	{
		this.nodo = nodo;
		this.tramoOrigen = tramoOrigen;
		this.tramoDestino = tramoDestino;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public Tramo getTramoDestino() {
		return tramoDestino;
	}

	public void setTramoDestino(Tramo tramoDestino) {
		this.tramoDestino = tramoDestino;
	}

	public Tramo getTramoOrigen() {
		return tramoOrigen;
	}

	public void setTramoOrigen(Tramo tramoOrigen) {
		this.tramoOrigen = tramoOrigen;
	}

}
