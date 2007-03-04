package is.SimTraffic.Mapa;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grupo ISTrafico
 *
 */
public class Nodo implements ElementoMapa {
	
	//Decidimos incluir la clase de entradaSalida e incluir un atributo aqui de ese tipo
	//que represente mejor las proporciones de los coches en franjas horarias
	/**
	 * Variable tipo EntradaSalida que almacena la proporci�n de coches que entran
	 * y salen del nodo en las diferentes franjas horarias.
	 */
	private EntradaSalida es;
	
	/**
	 * Variable booleana que indica si los coches deben parar al llegar a este nodo
	 *
	 *private boolean parada;
	 *Creemos que las paradas de autobus deben residir en la linea de autobus,ya que
	 *cada linea tiene sus paradas y de esta forma tendria el problema de que varias lineas se paran en todos los nodos 
	 *de parada.
	 *
	 */

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
	 * Variable que indicar� la categor�a del nodo (asociado a una carretera, lugar de ocio, 
	 * etc.) y su fin concreto.
	 */
	public ITipoElemento tipoNodo;
	
	/**
	 * Constructor de la clase nodo.<p>
	 * Este constructor solo requiere la posicion donde se ubica el nodo,
	 * el resto de la informaci�n la completa con valores por defecto.
	 * 
	 * @roseuid 45B8B3A80192
	 */
	public Nodo(Posicion pos) {
		this.pos = pos;
		es = null;
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

	public boolean equals(Object objeto) {
		if (objeto == null)
			return false;
		if (objeto.getClass() != this.getClass())
			return false;
		Nodo nodo = (Nodo) objeto;
		if (!nodo.pos.equals(this.pos))
			return false;
		return true;
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public EntradaSalida getEs(){
		return es;
	}
	
	public void setEs(EntradaSalida es){
		this.es=es;
	}


	public void setSe�al(Se�al se�al) {
		this.se�al = se�al;
	}

	public Se�al getSe�al() {
		return se�al;
	}

	public Posicion getPos() {
		return pos;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

}
