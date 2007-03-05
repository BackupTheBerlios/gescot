package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que matiene una instancia de un mapa.
 * <p>
 * Esta clase mantendra nodos, se�ales y tramos. En esta se podran representar
 * todas las caracter�sticas de un mapa OSM, como nodos y tramos con diferentes
 * caracter�sticas. Adem�s incluye algunas caracter�sticas adiciones como
 * se�ales y otras propiedades en tramos y nodos para llevar a cabo la
 * simulacion.<br>
 * Por �tlimo, cabe destacar que todos los nodos, se�ales y tramos estan
 * interconectados, con lo cual no es escrictamente necesario tenerlos todos en
 * listas (bastar�a con tener los nodos) pero sin embargo hacerlo as� simplifica
 * recorrer los elementos (por ejemplo, cuando se necesita encontrar una se�al
 * con caracter�sticas particulares) y mejora la gestion (facilita verificar si
 * se a�aden y eliminan correctamente las cosas.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Mapa {

	/**
	 * Mantiene la lista de nodos del mapa
	 */
	private ArrayList<Nodo> Nodos;

	/**
	 * Mantiene la lita de se�ales del mapa
	 */
	private ArrayList<Se�al> Se�ales;

	/**
	 * Mantiene la lista de tramos del Mapa
	 */
	private ArrayList<Tramo> Tramos;

	private int maxX;

	private int minX;

	private int minY;

	private int maxY;

	/**
	 * Crea un nuevo mapa, sin ningun nodo, tramo o se�al.
	 * 
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
	 * M�todo para insertar un nodo al mapa.
	 * <p>
	 * Este m�todo se asegura de que el nodo no sea vac�o y de que no este en la
	 * lista. Cuando es as�, agrega el nodo al mapa.<br>
	 * Adem�s, este m�todo actualiza las posiciones m�xima y m�nima de las
	 * coordenadas del mapa, �tiles a la hora de representarlo.
	 * 
	 * @param nodo
	 *            Nodo que se desea agregar al mapa.
	 */
	public void insertar(Nodo nodo) {
		int id = 1;
		if (nodo != null) {
			Iterator<Nodo> it = Nodos.iterator();
			Nodo temp;
			while (it.hasNext()) {
				temp = it.next();
				if (nodo.equals(temp))
					return;
				if (nodo.getID() == id)
					id++;
			}
			Nodos.add(nodo);
			nodo.setID(id);

		}

		if (Nodos.size() == 1) {
			maxY = nodo.getPos().getPosY();
			minY = nodo.getPos().getPosY();
			maxX = nodo.getPos().getPosX();
			minX = nodo.getPos().getPosX();
		} else {
			if (maxY < nodo.getPos().getPosY())
				maxY = nodo.getPos().getPosY();
			if (minY > nodo.getPos().getPosY())
				minY = nodo.getPos().getPosY();
			if (maxX < nodo.getPos().getPosX())
				maxX = nodo.getPos().getPosX();
			if (minX > nodo.getPos().getPosX())
				minX = nodo.getPos().getPosX();
		}
	}

	/**
	 * Metodo para insertar un tramo al mapa.
	 * <p>
	 * Este metodo confirma que el tramo sea v�lido (no es nulo y no empieza y
	 * termina en el mismo nodo) y que no exista ya en el mapa. De cumplirse
	 * estas condiciones, lo agrega a la lista de tramos.
	 * 
	 * @param tramo
	 *            Tramo que se desea a�adir.
	 */
	public void insertar(Tramo tramo) {
		if (tramo != null && !Tramos.contains(tramo))
			if (!tramo.getNodoFinal().equals(tramo.getNodoInicial()))
				if (Nodos.contains(tramo.getNodoInicial())
						&& Nodos.contains(tramo.getNodoFinal())) {
					tramo.getNodoInicial().a�adirTramo(tramo);
					tramo.getNodoFinal().a�adirTramo(tramo);
					Tramos.add(tramo);
				}

	}

	/**
	 * Metodo para insertar una se�al relacionada con un nodo al mapa.
	 * <p>
	 * Este m�todo confirma que la se�al es valida y que el nodo existe, y a�ade
	 * la se�al al mapa relacionandola con el nodo.
	 * 
	 * @param se�al
	 *            Se�al que se desea agregar al mapa
	 * @param nodo
	 *            Nodo al que se desea ligar la se�al
	 */
	public void insertar(Se�al se�al, Nodo nodo) {
		if (se�al != null && nodo != null && Nodos.contains(nodo)) {
			if (!Se�ales.contains(se�al))
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
	 * @param nodo
	 *            Nodo que se desea eliminar
	 * @return Booleano que indica si se elimino correctamente
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

	/**
	 * M�todo par a eliminar un tramo del mapa.
	 * <p>
	 * Este m�todo se encarga de remover el tramo de la lista correspondiente,
	 * as� como actualizar los componentes con los que esta relacionado.
	 * 
	 * @param tramo
	 *            Tramo que se desea quitar de la lista
	 * @return Booelano que inidca si se pudo elimnar el tramo
	 */
	public boolean eliminar(Tramo tramo) {
		if (tramo != null && Tramos.contains(tramo)) {
			Tramos.remove(tramo);
			tramo.getNodoFinal().quitarTramo(tramo);
			tramo.getNodoInicial().quitarTramo(tramo);
			return true;
		}
		return false;
	}

	public void eliminar(Se�al se�al) {
		Se�ales.remove(se�al);
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

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

}
