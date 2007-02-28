package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Grupo ISTrafico
 * 
 */
public class Mapa {
	private ArrayList<Nodo> Nodos;

	private ArrayList<Señal> Señales;

	private ArrayList<Tramo> Tramos;

	/**
	 * @roseuid 45B8B3A802CA
	 */
	public Mapa() {
		Nodos = new ArrayList<Nodo>();
		Señales = new ArrayList<Señal>();
		Tramos = new ArrayList<Tramo>();

	}

	/**
	 * @roseuid 45B8A9B603B5
	 */
	public void modificar() {

	}

	/**
	 * @param nodo
	 */
	public void insertar(Nodo nodo) {
		// verificar validez del nodo?
		if (nodo != null)
			Nodos.add(nodo);
	}

	/**
	 * @param tramo
	 */
	public void insertar(Tramo tramo) {
		if (tramo != null && !Tramos.contains(tramo)) {
			if (Nodos.contains(tramo.getNodoInicial())
					&& Nodos.contains(tramo.getNodoFinal()))
				Tramos.add(tramo);
		}
	}

	/**
	 * @param señal
	 */
	public void insertar(Señal señal, Nodo nodo) {
		if (señal != null && nodo != null && Nodos.contains(nodo)) {
			Señales.add(señal);
			nodo.setSeñal(señal);
		}
	}
	
	/**
	 * Método para eliminar un nodo del mapa, si es posible.
	 * <p>
	 * Este método intentará eliminar un nodo del mapa, pero fracasará y
	 * devolverá falso si el nodo forma parte de algún tramo.<br>
	 * Si el nodo no existe o no esta en ningún tramo, el método devuelve
	 * cierto.
	 * 
	 * @roseuid 45B8A9E500D6
	 */
	public boolean eliminar(Nodo nodo) {
		if (Nodos.contains(nodo)) {
			Iterator<Tramo> it = Tramos.iterator();
			while (it.hasNext()) {
				if (it.next().tieneNodo(nodo))
					return false;
			}
			Nodos.remove(nodo);
			return true;
		}
		return true;
	}

	public List<Nodo> getNodos() {
		return Nodos;
	}

	public List<Señal> getSeñales() {
		return Señales;
	}

	public List<Tramo> getTramos() {
		return Tramos;
	}
}
