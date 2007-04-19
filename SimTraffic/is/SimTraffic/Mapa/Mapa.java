package is.SimTraffic.Mapa;

import is.SimTraffic.Vista.Representaciones.Representacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

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

	/**
	 * Mantiene la lista de v�as del mapa.
	 */
	private ArrayList<Via> Vias;

	/**
	 * Guarda la informaci�n sobre los itinerarios de las lineas de autobuses
	 * (en v�as).
	 */
	private ArrayList<Via> LineasAutobuses;

	/**
	 * Indica la seleccion actual del mapa (nodos, tramos y se�ales).
	 */
	private Seleccion seleccion;

	/**
	 * Contiene los nodos y tramos que est�n temporalmente en el portapapeles
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
	 * Crea un nuevo mapa, sin ningun nodo, tramo o se�al.
	 * 
	 * @roseuid 45B8B3A802CA
	 */
	public Mapa() {
		Nodos = new ArrayList<Nodo>();
		Se�ales = new ArrayList<Se�al>();
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
		Se�ales = new ArrayList<Se�al>();
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

			// a�ade el nodo
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
	 * Este metodo confirma que el tramo sea v�lido (no es nulo y no empieza y
	 * termina en el mismo nodo) y que no exista ya en el mapa. De cumplirse
	 * estas condiciones, lo agrega a la lista de tramos.
	 * 
	 * @param tramo
	 *            Tramo que se desea a�adir.
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

			// a�ade el tramo, salvo que comienze y termine en el mismo nodo
			if (!tramo.getNodoFinal().equals(tramo.getNodoInicial()))
				if (Nodos.contains(tramo.getNodoInicial())
						&& Nodos.contains(tramo.getNodoFinal())) {
					tramo.getNodoInicial().a�adirTramo(tramo);
					tramo.getNodoFinal().a�adirTramo(tramo);
					Tramos.add(tramo);
					tramo.setID(idMax + 1);
					// Cambio en el m�todo de asignaci�n de ID
					// tramo.asignarIDunico();
				}

		}

	}

	public void insertar(Via via) {
		int idMax = 1;
		if (via != null) {
			// busca si la v�a no esta ya en el mapa, y el id de via mas grande
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

			// Debe buscar tambi�n en la lista de lineas de autobuses
			// para poder asignar un id de v�a �nico.
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
	 * M�todo que inserta una v�a dada como par�metro como un itinerario que
	 * seguir� un autobus. Estos itinerarios se guardar�n como v�as, pero en un
	 * array de vias diferente a Vias, por eso se crea este otro m�todo aparte
	 * de insertar(Via via).
	 * 
	 * @param via
	 *            Itinerario que seguir� un autobus.
	 */
	public void insertarLineaAutobus(Via via) {
		int idMax = 1;
		if (via != null) {
			// busca si la v�a no esta ya en el mapa, y el id de via mas grande
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

			// Debe buscar tambi�n en la lista de lineas de autobuses
			// para poder asignar un id de v�a �nico.
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

	/**
	 * M�todo para eliminar una v�a del mapa.
	 * <p>
	 * Este m�todo se encarga de eliminar la v�a creada, junto a los segmentos y
	 * nodos creados con ella. (la operaci�n de isnertar y eliminar una v�a es
	 * at�mica))
	 * 
	 * @param tramo
	 *            Tramo que se desea quitar de la lista
	 * @return Booelano que inidca si se pudo elimnar el tramo
	 */
	public boolean eliminar(Via via) {
		if (via != null && Vias.contains(via)) {

			// Borrar segmentos que aparezcan en la lista de tramos de la v�a
			// concreta
			// Ese borrar segmentos ya borrar� los nodos correspondientes
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
	 * M�todo que elimina una linea de autobus. Deber� ampliarse cuando cada
	 * nodo posea informaci�n sobre los autobuses que paran en si mismo, para
	 * actualizar la informaci�n de esos nodos. Al contrario que eliminar(Via
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

	public void eliminar(Se�al se�al) {
		Se�ales.remove(se�al);
	}

	/**
	 * En funci�n de un rectangulo pasado como par�metro, modifica la seleccion
	 * con todos los elementos que est�n contenido en dicho rect�ngulo.
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
						this.seleccion.a�adirNodo(nodoTemp);
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
						this.seleccion.a�adirNodo(nodoTemp);
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
						this.seleccion.a�adirTramo(tramoTemp);
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
						this.seleccion.a�adirTramo(tramoTemp);
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

	public ArrayList<Se�al> getSe�ales() {
		return Se�ales;
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
	 * nombre el par�metro.
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
	 * nombre el par�metro.
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
	 * Devuelve la primera v�a (no desambigua si hay varias) que tiene como
	 * nombre el par�metro.
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
	 * Devuelve la primera l�nea de bus (no desambigua si hay varios) que tiene
	 * como nombre el par�metro.
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
