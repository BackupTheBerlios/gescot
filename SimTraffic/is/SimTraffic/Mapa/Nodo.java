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
	 * Variable tipo EntradaSalida que almacena la proporción de coches que entran
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
	 * Señal que regulara el trafico por este nodo
	 */
	public Señal señal;

	/**
	 * Variable que mantiene la posicion del nodo en el mapa
	 */
	private Posicion pos;

	/**
	 * Variable del tipo lista que mantiene todos los tramos que llegan a este nodo
	 */
	private List<Tramo> tramos;
	
	/**
	 * Variable que indicará la categoría del nodo (asociado a una carretera, lugar de ocio, 
	 * etc.) y su fin concreto.
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitirá al usuario dar nombre concreto a un nodo (función 
	 * meramente complementaria, ya que dicho nombre no determina de forma unívoca el nodo).
	 */
	private String nombre;
	
	/**
	 * Identificador en OSM del nodo. Cada nodo tiene un identificador entero único, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Constructor de la clase nodo.<p>
	 * Este constructor solo requiere la posicion donde se ubica el nodo,
	 * el resto de la información la completa con valores por defecto.
	 * 
	 * @roseuid 45B8B3A80192
	 */
	public Nodo(Posicion pos) {
		this.pos = pos;
		es = new EntradaSalida();
		señal = null;
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

	public Nodo(EntradaSalida es, int id, String nombre, Posicion pos, Señal señal) {
		super();
		this.es = es;
		ID = id;
		this.nombre = nombre;
		this.pos = pos;
		this.señal = señal;
		tramos = new ArrayList<Tramo>();
		id++;
	}

	public Nodo(EntradaSalida es, int id, String nombre, Posicion pos, Señal señal, ITipoElemento tipo, List<Tramo> tramos) {
		super();
		this.es = es;
		ID = id;
		this.nombre = nombre;
		this.pos = pos;
		this.señal = señal;
		this.tipo = tipo;
		this.tramos = tramos;
		id++;
	}

	/**
	 * Método para añadir un nuevo tramo al nodo.<p>
	 * Este método se encarga de actualizar la lista de tramos que llegan o salen
	 * del nodo con un nuevo tramo.
	 * @param tramo
	 * Tramo a añadir
	 */
	public void añadirTramo(Tramo tramo) {
		if (tramo!= null && !tramos.contains(tramo)) 
		{			
			Iterator<Tramo> it = tramos.iterator();
			Tramo temp;
			int anterior = 0; // Tramo que irá antes en la lista ordenada del que vamos a insertar.
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
	 * Método para quitar un tramo del nodo.<p>
	 * Este método se encarga de, cuando sea posible, quitar un tramo dado de la
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

	public void setSeñal(Señal señal) {
		this.señal = señal;
	}

	public Señal getSeñal() {
		return señal;
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
	 * Devuelve un string con la traducción del nodo al formato osm(node), necesario 
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
	 * Método auxiliar definido para resolver si dos nodos tienen un tramo
	 * que les comunique. 
	 * @param nodo Nodo al que se quiere llegar a través de un tramo.
	 * @return Si 2 nodos tienen un tramo común (y se puede ir de nodo1 a nodo2) 
	 * devuelve dicho tramo, y sino devuelve null.
	 */
	public Tramo devuelveTramoDirigidoA(Nodo nodo) {
		if (nodo.getPos()==getPos()) return null; //Fallo, pues no hay un tramo entre un nodo y sí mismo.
		else {
			Iterator<Tramo> tram= tramos.iterator();
			Tramo tramoaux;
			while (tram.hasNext()) {
					tramoaux = tram.next();
					//Por depurar (seguramente se deba incluir condición de (carrilesdirX>0))
					if (tramoaux.getNodoInicial()==this && tramoaux.getNodoFinal()==nodo) //Ha encontrado el tramo con la condición requerida.
						return tramoaux;
					if (tramoaux.getNodoInicial()==nodo && tramoaux.getNodoFinal()==this) //Ha encontrado el tramo con la condición requerida.
						return tramoaux;
			}
			return null; //No ha encontrado ningún tramo, luego devuelve null.
		}
	}


	
	/**
	 * Este clone() sirve unicamente para utilizarlo en la herramienta de mover, ya que sólo necesitaremos conocer
	 * los valores ID y de las posiciones del nodo.
	 * @return
	 */
	public Nodo cloneParaMover() {
		Nodo nodoClon = new Nodo(this.es, this.ID, this.nombre, new Posicion(pos.getLat(),pos.getLon()), 
				this.señal, this.tipo, this.tramos);
		return nodoClon;

	}
	
	public Nodo pseudoClone() {
		Nodo clon = new Nodo(this.pos.clone());
		return clon;
	}




}
