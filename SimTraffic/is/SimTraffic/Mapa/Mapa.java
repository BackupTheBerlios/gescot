package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que matiene una instancia de un mapa.
 * <p>
 * Esta clase mantendra nodos, señales y tramos. En esta se podran representar
 * todas las características de un mapa OSM, como nodos y tramos con diferentes
 * características. Además incluye algunas características adiciones como
 * señales y otras propiedades en tramos y nodos para llevar a cabo la
 * simulacion.<br>
 * Por útlimo, cabe destacar que todos los nodos, señales y tramos estan
 * interconectados, con lo cual no es escrictamente necesario tenerlos todos en
 * listas (bastaría con tener los nodos) pero sin embargo hacerlo así simplifica
 * recorrer los elementos (por ejemplo, cuando se necesita encontrar una señal
 * con características particulares) y mejora la gestion (facilita verificar si
 * se añaden y eliminan correctamente las cosas.
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
	 * Mantiene la lita de señales del mapa
	 */
	private ArrayList<Señal> Señales;

	/**
	 * Mantiene la lista de tramos del Mapa
	 */
	private ArrayList<Tramo> Tramos;

	private int maxX;

	private int minX;

	private int minY;

	private int maxY;

	/**
	 * Crea un nuevo mapa, sin ningun nodo, tramo o señal.
	 * 
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
	 * Método para insertar un nodo al mapa.
	 * <p>
	 * Este método se asegura de que el nodo no sea vacío y de que no este en la
	 * lista. Cuando es así, agrega el nodo al mapa.<br>
	 * Además, este método actualiza las posiciones máxima y mínima de las
	 * coordenadas del mapa, útiles a la hora de representarlo.
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
	 * Este metodo confirma que el tramo sea válido (no es nulo y no empieza y
	 * termina en el mismo nodo) y que no exista ya en el mapa. De cumplirse
	 * estas condiciones, lo agrega a la lista de tramos.
	 * 
	 * @param tramo
	 *            Tramo que se desea añadir.
	 */
	public void insertar(Tramo tramo) {
		if (tramo != null && !Tramos.contains(tramo))
			if (!tramo.getNodoFinal().equals(tramo.getNodoInicial()))
				if (Nodos.contains(tramo.getNodoInicial())
						&& Nodos.contains(tramo.getNodoFinal())) {
					tramo.getNodoInicial().añadirTramo(tramo);
					tramo.getNodoFinal().añadirTramo(tramo);
					Tramos.add(tramo);
				}

	}

	/**
	 * Metodo para insertar una señal relacionada con un nodo al mapa.
	 * <p>
	 * Este método confirma que la señal es valida y que el nodo existe, y añade
	 * la señal al mapa relacionandola con el nodo.
	 * 
	 * @param señal
	 *            Señal que se desea agregar al mapa
	 * @param nodo
	 *            Nodo al que se desea ligar la señal
	 */
	public void insertar(Señal señal, Nodo nodo) {
		if (señal != null && nodo != null && Nodos.contains(nodo)) {
			if (!Señales.contains(señal))
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
	 * Método par a eliminar un tramo del mapa.
	 * <p>
	 * Este método se encarga de remover el tramo de la lista correspondiente,
	 * así como actualizar los componentes con los que esta relacionado.
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

	public void eliminar(Señal señal) {
		Señales.remove(señal);
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
