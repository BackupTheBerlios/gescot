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

	private ArrayList<Se�al> Se�ales;

	private ArrayList<Tramo> Tramos;

	/**
	 * @roseuid 45B8B3A802CA
	 */
	public Mapa() {
		Nodos = new ArrayList<Nodo>();
		Se�ales = new ArrayList<Se�al>();
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
	 * @param se�al
	 */
	public void insertar(Se�al se�al, Nodo nodo) {
		if (se�al != null && nodo != null && Nodos.contains(nodo)) {
			Se�ales.add(se�al);
			nodo.setSe�al(se�al);
		}
	}
	
	/**
	 * M�todo para eliminar un nodo del mapa, si es posible.
	 * <p>
	 * Este m�todo intentar� eliminar un nodo del mapa, pero fracasar� y
	 * devolver� falso si el nodo forma parte de alg�n tramo.<br>
	 * Si el nodo no existe o no esta en ning�n tramo, el m�todo devuelve
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

	public List<Se�al> getSe�ales() {
		return Se�ales;
	}

	public List<Tramo> getTramos() {
		return Tramos;
	}
}
