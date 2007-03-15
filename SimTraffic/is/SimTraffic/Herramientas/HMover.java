package is.SimTraffic.Herramientas;

import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class HMover implements IHerramienta{

	/**
	 * Lista de nodos que se quieren desplazar.
	 */
	private List<Nodo> nodos;
	/**
	 * Lista de tramos que se quieren desplazar.
	 */
	private List<Tramo> tramos;
	
	/**
	 * Constructora de la herramienta
	 * Se inicializan las listas de nodos y tramos
	 *
	 */
	public HMover(List<Nodo> nodos, List<Tramo> tramos) {
		this.nodos = nodos;
		this.tramos = tramos;
	}
	
	public int hacer(IModelo modelo) {
		return 0;
	}

	public int deshacer(IModelo modelo) {
		return 0;
	}

}
