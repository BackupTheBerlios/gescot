package is.SimTraffic.Mapa;

import is.SimTraffic.Vista.Representaciones.Representacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

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

	/**
	 * Mantiene la lista de vías del mapa.
	 */
	private ArrayList<Via> Vias;

	/**
	 * Guarda la información sobre los itinerarios de las lineas de autobuses
	 * (en vías).
	 */
	private ArrayList<Via> LineasAutobuses;

	/**
	 * Indica la seleccion actual del mapa (nodos, tramos y señales).
	 */
	private Seleccion seleccion;

	/**
	 * Contiene los nodos y tramos que están temporalmente en el portapapeles
	 * con el fin de reutilizarlos posteriormente.
	 */
	private Seleccion portapapeles;

	/**
	 * Nodo que sirve de referencia para saber donde pegar el contenido del
	 * portapapeles
	 */
	private Nodo nodoReferenciaPortapapeles;

	private double maxLon;

	private double minLon;

	private double minLat;

	private double maxLat;

	/**
	 * Crea un nuevo mapa, sin ningun nodo, tramo o señal.
	 * 
	 * @roseuid 45B8B3A802CA
	 */
	public Mapa() {
		Nodos = new ArrayList<Nodo>();
		Señales = new ArrayList<Señal>();
		Tramos = new ArrayList<Tramo>();
		Vias = new ArrayList<Via>();
		LineasAutobuses = new ArrayList<Via>();

		seleccion = new Seleccion();
		portapapeles = new Seleccion();
	}

	/**
	 * Utilizado para cargar el mapa.
	 * 
	 * @param nodos
	 * @param tramos
	 */
	public Mapa(ArrayList<Nodo> nodos, ArrayList<Tramo> tramos) {
		// TODO Auto-generated constructor stub
		Nodos = nodos;
		Tramos = tramos;
		Señales = new ArrayList<Señal>();
		Vias = new ArrayList<Via>();
		LineasAutobuses = new ArrayList<Via>();

		seleccion = new Seleccion();
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
		int idMax = 1;
		if (nodo != null) {
			// controla que el nodo no este ya en la lista, a la vez que
			// determina el maximo id para que no se repita
			Iterator<Nodo> it = Nodos.iterator();
			Nodo temp;
			while (it.hasNext()) {
				temp = it.next();
				if (nodo.equals(temp))
					return;
				if (temp.getID() >= idMax) // Antes nodo.getID()
					idMax = temp.getID();
			}

			// añade el nodo
			Nodos.add(nodo);
			nodo.setID(idMax + 1);

		}

		if (Nodos.size() == 1) {
			maxLat = nodo.getPos().getLat();
			minLat = nodo.getPos().getLat();
			maxLon = nodo.getPos().getLon();
			minLon = nodo.getPos().getLon();
		} else {
			if (maxLat < nodo.getPos().getLat())
				maxLat = nodo.getPos().getLat();
			if (minLat > nodo.getPos().getLat())
				minLat = nodo.getPos().getLat();
			if (maxLon < nodo.getPos().getLon())
				maxLon = nodo.getPos().getLon();
			if (minLon > nodo.getPos().getLon())
				minLon = nodo.getPos().getLon();
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
		int idMax = 1;
		if (tramo != null) {
			// busca si el tramo no esta ya en el mapa, y el id de tramo mas
			// grande
			// para no repetir
			Iterator<Tramo> it = Tramos.iterator();
			Tramo temp;
			while (it.hasNext()) 
			{
				temp = it.next();
				if (tramo.equals(temp))
					return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}

			// añade el tramo, salvo que comienze y termine en el mismo nodo
			if (!tramo.getNodoFinal().equals(tramo.getNodoInicial()))
				if (Nodos.contains(tramo.getNodoInicial())
						&& Nodos.contains(tramo.getNodoFinal())) {
					tramo.getNodoInicial().añadirTramo(tramo);
					tramo.getNodoFinal().añadirTramo(tramo);
					Tramos.add(tramo);
					tramo.setID(idMax + 1);
					// Cambio en el método de asignación de ID
					// tramo.asignarIDunico();
				}

		}

	}

	public void insertar(Via via) {
		int idMax = 1;
		if (via != null) {
			// busca si la vía no esta ya en el mapa, y el id de via mas grande
			// para no repetir
			Iterator<Via> it = Vias.iterator();
			Via temp;
			while (it.hasNext()) {
				temp = it.next();
				if (via.equals(temp))
					return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}

			// Debe buscar también en la lista de lineas de autobuses
			// para poder asignar un id de vía único.
			it = LineasAutobuses.iterator();
			while (it.hasNext()) {
				temp = it.next();
				if (via.equals(temp))
					return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}

			Vias.add(via);
			via.setID(idMax + 1);

		}

	}

	/**
	 * Método que inserta una vía dada como parámetro como un itinerario que
	 * seguirá un autobus. Estos itinerarios se guardarán como vías, pero en un
	 * array de vias diferente a Vias, por eso se crea este otro método aparte
	 * de insertar(Via via).
	 * 
	 * @param via
	 *            Itinerario que seguirá un autobus.
	 */
	public void insertarLineaAutobus(Via via) {
		int idMax = 1;
		if (via != null) {
			// busca si la vía no esta ya en el mapa, y el id de via mas grande
			// para no repetir
			Iterator<Via> it = Vias.iterator();
			Via temp;
			while (it.hasNext()) {
				temp = it.next();
				if (via.equals(temp))
					return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}

			// Debe buscar también en la lista de lineas de autobuses
			// para poder asignar un id de vía único.
			it = LineasAutobuses.iterator();
			while (it.hasNext()) {
				temp = it.next();
				if (via.equals(temp))
					return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}

			LineasAutobuses.add(via);
			via.setID(idMax + 1);
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

	/**
	 * Método para eliminar una vía del mapa.
	 * <p>
	 * Este método se encarga de eliminar la vía creada, junto a los segmentos y
	 * nodos creados con ella. (la operación de isnertar y eliminar una vía es
	 * atómica))
	 * 
	 * @param tramo
	 *            Tramo que se desea quitar de la lista
	 * @return Booelano que inidca si se pudo elimnar el tramo
	 */
	public boolean eliminar(Via via) {
		if (via != null && Vias.contains(via)) {

			// Borrar segmentos que aparezcan en la lista de tramos de la vía
			// concreta
			// Ese borrar segmentos ya borrará los nodos correspondientes
			// (recursivo)
			Iterator<Tramo> tram = via.getTramos().iterator();
			while (tram.hasNext()) {
				Tramo aux = tram.next();
				this.eliminar(aux);
				eliminar(aux.getNodoInicial());
				eliminar(aux.getNodoFinal());
			}
			Vias.remove(via);
			return true;
		}
		return false;
	}

	/**
	 * Método que elimina una linea de autobus. Deberá ampliarse cuando cada
	 * nodo posea información sobre los autobuses que paran en si mismo, para
	 * actualizar la información de esos nodos. Al contrario que eliminar(Via
	 * via) no elimina del mapa los tramos y nodos que pertenecen a la via.
	 * 
	 * @param via
	 *            Itinerario de un autobus que se pretende eliminar.
	 * @return
	 */
	public boolean eliminarLineaAutobus(Via via) {
		if (via != null && LineasAutobuses.contains(via)) {
			LineasAutobuses.remove(via);
			return true;
		}
		return false;
	}

	public void eliminar(Señal señal) {
		Señales.remove(señal);
	}

	/**
	 * En función de un rectangulo pasado como parámetro, modifica la seleccion
	 * con todos los elementos que estén contenido en dicho rectángulo.
	 * 
	 * @param rectanguloSeleccion
	 */
	public void seleccionaEnRectangulo(Rectangle rectanguloSeleccion,
			int tipoDeSeleccion, Representacion rep) {

		if (tipoDeSeleccion == 0) { // se elige seleccionar nodos
			Nodo nodoTemp;
			Point punto;
			for (int i = 0; i < Nodos.size(); i++) {
				nodoTemp = Nodos.get(i);
				punto = new Point(rep.x_MapaARep(nodoTemp.getPos().getLon()),
						rep.y_MapaARep(nodoTemp.getPos().getLat()));
				if (rectanguloSeleccion.contains(punto)) {
					if (!seleccion.getNodosSeleccionados().contains(nodoTemp))
						this.seleccion.añadirNodo(nodoTemp);
				}
			}
		}
		if (tipoDeSeleccion == 1) { // se elige seleccionar elementos
			Nodo nodoTemp;
			Point punto;
			for (int i = 0; i < Nodos.size(); i++) {
				nodoTemp = Nodos.get(i);
				punto = new Point(rep.x_MapaARep(nodoTemp.getPos().getLon()),
						rep.y_MapaARep(nodoTemp.getPos().getLat()));
				if (rectanguloSeleccion.contains(punto)) {
					if (!seleccion.getNodosSeleccionados().contains(nodoTemp))
						this.seleccion.añadirNodo(nodoTemp);
				}
			}
			Tramo tramoTemp;
			for (int i = 0; i < Tramos.size(); i++) {
				tramoTemp = Tramos.get(i);
				boolean encontradoInicial = false;
				boolean encontradoFinal = false;
				int j = 0;
				Nodo posibleNodoInicial;
				while (j < seleccion.getNodosSeleccionados().size()
						&& !encontradoInicial) {
					posibleNodoInicial = seleccion.getNodosSeleccionados().get(
							j);
					if (posibleNodoInicial.equals(tramoTemp.getNodoInicial()))
						encontradoInicial = true;
					j++;
				}
				j = 0;
				Nodo posibleNodoFinal;
				while (j < seleccion.getNodosSeleccionados().size()
						&& !encontradoFinal) {
					posibleNodoFinal = seleccion.getNodosSeleccionados().get(j);
					if (posibleNodoFinal.equals(tramoTemp.getNodoFinal()))
						encontradoFinal = true;
					j++;
				}
				if (encontradoInicial && encontradoFinal) {
					if (!seleccion.getTramosSeleccionados().contains(tramoTemp))
						this.seleccion.añadirTramo(tramoTemp);
				}
			}

		}
		if (tipoDeSeleccion == 2) { // se elige seleccionar tramos
			Nodo nodoTemp;
			Point punto;
			ArrayList<Nodo> nodosSeleccionados = new ArrayList<Nodo>();
			for (int i = 0; i < Nodos.size(); i++) {
				nodoTemp = Nodos.get(i);
				punto = new Point(rep.x_MapaARep(nodoTemp.getPos().getLon()),
						rep.y_MapaARep(nodoTemp.getPos().getLat()));
				if (rectanguloSeleccion.contains(punto)) {
					if (!seleccion.getNodosSeleccionados().contains(nodoTemp))
						nodosSeleccionados.add(nodoTemp);
				}
			}
			Tramo tramoTemp;
			for (int i = 0; i < Tramos.size(); i++) {
				tramoTemp = Tramos.get(i);
				boolean encontradoInicial = false;
				boolean encontradoFinal = false;
				int j = 0;
				Nodo posibleNodoInicial;
				while (j < nodosSeleccionados.size() && !encontradoInicial) {
					posibleNodoInicial = nodosSeleccionados.get(j);
					if (posibleNodoInicial.equals(tramoTemp.getNodoInicial()))
						encontradoInicial = true;
					j++;
				}
				j = 0;
				Nodo posibleNodoFinal;
				while (j < nodosSeleccionados.size() && !encontradoFinal) {
					posibleNodoFinal = nodosSeleccionados.get(j);
					if (posibleNodoFinal.equals(tramoTemp.getNodoFinal()))
						encontradoFinal = true;
					j++;
				}
				if (encontradoInicial && encontradoFinal) {
					if (!seleccion.getTramosSeleccionados().contains(tramoTemp))
						this.seleccion.añadirTramo(tramoTemp);
				}
			}
		}
	}

	/**
	 * Deselecciona todos los elementos que estaban seleccionados.
	 */
	public void limpiaSeleccion() {
		if (this.seleccion != null)
			this.seleccion = null;
		this.seleccion = new Seleccion();
	}

	public String getTipoVia(Tramo tramo) {
		try {
			Iterator<Via> it = Vias.iterator();
			Via temp;
			while (it.hasNext()) {
				temp = it.next();
				if (temp.tieneTramo(tramo))
					return temp.getTipo().getTipo();
			}
		} catch (NullPointerException e) {
			return null;
		}
		return null;
	}

	public ArrayList<Nodo> getNodos() {
		return Nodos;
	}

	public ArrayList<Señal> getSeñales() {
		return Señales;
	}

	public ArrayList<Tramo> getTramos() {
		return Tramos;
	}

	public double getMaxLon() {
		return maxLon;
	}

	public double getMaxLat() {
		return maxLat;
	}

	public double getMinLon() {
		return minLon;
	}

	public double getMinLat() {
		return minLat;
	}

	public ArrayList<Via> getVias() {
		return Vias;
	}

	public void setVias(ArrayList<Via> vias) {
		Vias = vias;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	public ArrayList<Via> getLineasAutobuses() {
		return LineasAutobuses;
	}

	public void setLineasAutobuses(ArrayList<Via> lineasAutobuses) {
		LineasAutobuses = lineasAutobuses;
	}

	public void setTramos(ArrayList<Tramo> tramos) {
		Tramos = tramos;
	}

	public Seleccion getPortapapeles() {
		return portapapeles;
	}

	public void setPortapapeles(Seleccion portapapeles) {
		this.portapapeles = portapapeles;
	}

	public Nodo getNodoReferenciaPortapapeles() {
		return nodoReferenciaPortapapeles;
	}

	public void setNodoReferenciaPortapapeles(Nodo nodoReferenciaPortapapeles) {
		this.nodoReferenciaPortapapeles = nodoReferenciaPortapapeles;
	}

	public Nodo existeNodo(Nodo nodo) {
		for (int i = 0; i < Nodos.size(); i++) {
			if (nodo.getPos().equals(Nodos.get(i).getPos()))
				return Nodos.get(i);
		}
		return null;
	}

	/**
	 * Devuelve el primer nodo (no desambigua si hay varios) que tiene como
	 * nombre el parámetro.
	 * 
	 * @param nombre
	 * @return
	 */
	public Nodo buscarNodo(String nombre) {
		Iterator<Nodo> nod = Nodos.iterator();
		Nodo nodoaux = null;
		String nomaux;
		boolean encontrado = false;
		while (nod.hasNext() && !encontrado) {
			nodoaux = nod.next();
			nomaux = nodoaux.getNombre();
			if (nomaux != null && nomaux.equals(nombre))
				encontrado = true;
		}
		return nodoaux;
	}

	/**
	 * Devuelve el primer tramo (no desambigua si hay varios) que tiene como
	 * nombre el parámetro.
	 * 
	 * @param nombre
	 * @return
	 */
	public Tramo buscarTramo(String nombre) {
		Iterator<Tramo> tram = Tramos.iterator();
		Tramo tramoaux = null;
		String nomaux;
		boolean encontrado = false;
		while (tram.hasNext() && !encontrado) {
			tramoaux = tram.next();
			nomaux = tramoaux.getNombre();
			if (nomaux != null && nomaux.equals(nombre))
				encontrado = true;
		}
		return tramoaux;
	}

	/**
	 * Devuelve la primera vía (no desambigua si hay varias) que tiene como
	 * nombre el parámetro.
	 * 
	 * @param nombre
	 * @return
	 */
	public Via buscarVia(String nombre) {
		Iterator<Via> v = Vias.iterator();
		Via viaaux = null;
		String nomaux;
		boolean encontrado = false;
		while (v.hasNext() && !encontrado) {
			viaaux = v.next();
			nomaux = viaaux.getNombre();
			if (nomaux != null && nomaux.equals(nombre))
				encontrado = true;
		}
		return viaaux;
	}

	/**
	 * Devuelve la primera línea de bus (no desambigua si hay varios) que tiene
	 * como nombre el parámetro.
	 * 
	 * @param nombre
	 * @return
	 */
	public Via buscarLineaBus(String nombre) {
		Iterator<Via> v = LineasAutobuses.iterator();
		Via viaaux = null;
		String nomaux;
		boolean encontrado = false;
		while (v.hasNext() && !encontrado) {
			viaaux = v.next();
			nomaux = viaaux.getNombre();
			if (nomaux != null && nomaux.equals(nombre))
				encontrado = true;
		}
		return viaaux;
	}

}
