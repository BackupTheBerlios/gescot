package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	/**
	 * Variable que indica la proporci�n de coches que entran por este nodo al mapa
	 */
	private float entrada;

	/**
	 * Variable que indica la proporci�n de coches que sale del mapa por este nodo
	 */
	private float salida;

	/**
	 * Variable booleana que indica si los coches deben parar al llegar a este nodo
	 */
	private boolean parada;

	/**
	 * Se�al que regulara el trafico por este nodo
	 */
	public Se�al se�al;

	/**
	 * Variable que mantiene la posicion del nodo en el mapa
	 */
	private Posicion pos;

	/**
	 * Variable del tipo lista que mantiene todos los tramos que llegan a este nodo
	 */
	public List<Tramo> tramos;

	/**
	 * Consturtor de la clase nodo.<p>
	 * Este constructor solo requiere la posicion donde se ubica el nodo,
	 * el resto de la informaci�n la completa con valores por defecto.
	 * 
	 * @roseuid 45B8B3A80192
	 */
	public Nodo(Posicion pos) {
		this.pos = pos;
		entrada = 0.1f;
		salida = 0.1f;
		parada = false;
		se�al = null;
		tramos = new ArrayList<Tramo>();
	}

	/**
	 * M�todo para a�adir un nuevo tramo al nodo.<p>
	 * Este m�todo se encarga de actualizar la lista de tramos que llegan o salen
	 * del nodo con un nuevo tramo.
	 * @param tramo
	 * Tramo a a�adir
	 */
	public void a�adirTramo(Tramo tramo) {
		if (tramo!= null && !tramos.contains(tramo)) {
			tramos.add(tramo);
		}
	}
	
	/**
	 * M�todo para quitar un tramo del nodo.<p>
	 * Este m�todo se encarga de, cuando sea posible, quitar un tramo dado de la
	 * lista de tramos que salen o llegan a nodo.
	 * @param tramo
	 * Tramo a quitar.
	 */
	public void quitarTramo(Tramo tramo) {
		if (tramo != null & tramos.contains(tramo)){
			tramos.remove(tramo);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	public int hashCode() {
		return pos.hashCode();
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

	public void setSe�al(Se�al se�al) {
		this.se�al = se�al;
	}

	public Se�al getSe�al() {
		return se�al;
	}

	public Posicion getPos() {
		return null;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

}
