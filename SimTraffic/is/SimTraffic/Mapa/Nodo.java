package is.SimTraffic.Mapa;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Grupo ISTrafico
 *
 */
public class Nodo implements ElementoMapa {
	
	public static int id = 0;

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
	private List<Tramo> tramos;
	
	/**
	 * Variable que indicar� la categor�a del nodo (asociado a una carretera, lugar de ocio, 
	 * etc.) y su fin concreto.
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitir� al usuario dar nombre concreto a un nodo (funci�n 
	 * meramente complementaria, ya que dicho nombre no determina de forma un�voca el nodo).
	 */
	private String nombre;
	
	/**
	 * Identificador en OSM del nodo. Cada nodo tiene un identificador entero �nico, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Constructor de la clase nodo.<p>
	 * Este constructor solo requiere la posicion donde se ubica el nodo,
	 * el resto de la informaci�n la completa con valores por defecto.
	 * 
	 * @roseuid 45B8B3A80192
	 */
	public Nodo(Posicion pos) {
		this.pos = pos;
		es = new EntradaSalida();
		se�al = null;
		tramos = new ArrayList<Tramo>();
		id++;
	}

	public Nodo(int id, String nombre, Posicion pos, ITipoElemento tipo) {
		ID = id;
		this.nombre = nombre;
		this.pos = pos;
		this.tipo = tipo;
		this.es = new EntradaSalida();
		tramos = new ArrayList<Tramo>();
		id++;
	}

	public Nodo(EntradaSalida es, int id, String nombre, Posicion pos, Se�al se�al) {
		super();
		this.es = es;
		ID = id;
		this.nombre = nombre;
		this.pos = pos;
		this.se�al = se�al;
		tramos = new ArrayList<Tramo>();
		id++;
	}

	public Nodo(EntradaSalida es, int id, String nombre, Posicion pos, Se�al se�al, ITipoElemento tipo, List<Tramo> tramos) {
		super();
		this.es = es;
		ID = id;
		this.nombre = nombre;
		this.pos = pos;
		this.se�al = se�al;
		this.tipo = tipo;
		this.tramos = tramos;
		id++;
	}

	/**
	 * M�todo para a�adir un nuevo tramo al nodo.<p>
	 * Este m�todo se encarga de actualizar la lista de tramos que llegan o salen
	 * del nodo con un nuevo tramo.
	 * @param tramo
	 * Tramo a a�adir
	 */
	public void a�adirTramo(Tramo tramo) {
		if (tramo!= null && !tramos.contains(tramo)) 
		{			
			Iterator<Tramo> it = tramos.iterator();
			Tramo temp;
			int anterior = 0; // Tramo que ir� antes en la lista ordenada del que vamos a insertar.
			double angulo = tramo.getAngulo();
			System.out.println(angulo);
			while (it.hasNext()) 
			{
				temp = it.next();
				if (angulo > temp.getAngulo())
					anterior++;
			}
			tramos.add(anterior,tramo);
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
	
	public int getID() {
		return ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve un string con la traducci�n del nodo al formato osm(node), necesario 
	 * para el proceso de guardar el mapa.
	 */
	public String transformaraOSM() {
		String s=new String();
		//if (tipo==null && nombre==null)
		//	s=s.concat(("<node id='"+ID+"' lat='" + pos.getLat() + "' lon='" + pos.getLon() + "' />"));
		//else {
			s=s.concat("<node id='"+ID+"' lat='" + pos.getLat() + "' lon='" + pos.getLon() + "' >\n");
			if (tipo!=null) s=s.concat("<tag k='"+tipo.getTipo()+"' v='"+tipo.getValorTipo()+"' />\n");
			if (nombre!=null) s=s.concat("<tag k='nombre' v='"+getNombre()+"' />\n");
			if (es!=null) s=s.concat(es.transformaOSM());
			s=s.concat("</node>");
		//}
		return s;
	}

	public ITipoElemento getTipo() {
		return tipo;
	}

	public void setTipo(ITipoElemento tipo) {
		this.tipo = tipo;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	public void setTramos(List<Tramo> tramos) {
		this.tramos = tramos;
	}
	
	/**
	 * M�todo auxiliar definido para resolver si dos nodos tienen un tramo
	 * que les comunique. 
	 * @param nodo Nodo al que se quiere llegar a trav�s de un tramo.
	 * @return Si 2 nodos tienen un tramo com�n (y se puede ir de nodo1 a nodo2) 
	 * devuelve dicho tramo, y sino devuelve null.
	 */
	public Tramo devuelveTramoDirigidoA(Nodo nodo) {
		if (nodo.getPos()==getPos()) return null; //Fallo, pues no hay un tramo entre un nodo y s� mismo.
		else {
			Iterator<Tramo> tram= tramos.iterator();
			Tramo tramoaux;
			while (tram.hasNext()) {
					tramoaux = tram.next();
					//Por depurar (seguramente se deba incluir condici�n de (carrilesdirX>0))
					if (tramoaux.getNodoInicial()==this && tramoaux.getNodoFinal()==nodo) //Ha encontrado el tramo con la condici�n requerida.
						return tramoaux;
					if (tramoaux.getNodoInicial()==nodo && tramoaux.getNodoFinal()==this) //Ha encontrado el tramo con la condici�n requerida.
						return tramoaux;
			}
			return null; //No ha encontrado ning�n tramo, luego devuelve null.
		}
	}


	
	/**
	 * Este clone() sirve unicamente para utilizarlo en la herramienta de mover, ya que s�lo necesitaremos conocer
	 * los valores ID y de las posiciones del nodo.
	 * @return
	 */
	public Nodo cloneParaMover() {
		Nodo nodoClon = new Nodo(this.es, this.ID, this.nombre, new Posicion(pos.getLat(),pos.getLon()), 
				this.se�al, this.tipo, this.tramos);
		return nodoClon;

	}
	
	public Nodo pseudoClone() {
		Nodo clon = new Nodo(this.pos.clone());
		return clon;
	}




}
