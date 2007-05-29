package is.SimTraffic.Mapa;

import is.SimTraffic.Messages;
import is.SimTraffic.Vista.Representaciones.Representacion;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

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
	 */
	private ArrayList<LineaBus> LineasAutobuses;

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
	
	private boolean cambios_en_mapa;


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
		LineasAutobuses = new ArrayList<LineaBus>();
		seleccion = new Seleccion();
		portapapeles = new Seleccion();
		cambios_en_mapa=false;
		
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
		LineasAutobuses = new ArrayList<LineaBus>();

		seleccion = new Seleccion();
		portapapeles = new Seleccion();
		cambios_en_mapa=false;
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
			cambios_en_mapa=true;
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
        cambios_en_mapa=true;
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
			Iterator<LineaBus> it2 = LineasAutobuses.iterator();
			LineaBus temp2;
			while (it2.hasNext()) {
				temp2 = it2.next();
				//if (via.equals(temp2))
					//return;
				if (temp2.getID() >= idMax)
					idMax = temp2.getID();
			}

			Vias.add(via);
			via.setID(idMax + 1);
			cambios_en_mapa=true;
		}

	}

	/**
	 * Metodo que inserta lineas de autobus.Comprueba q su id no pertenece a ninguna via ni otra linea de 
	 * bus ya presente
	 * @param LineaBus
	 *            Itinerario que seguirá un autobus.
	 */
	public void insertarLineaAutobus(LineaBus linea) {
		int idMax = 1;
		if (linea != null) {
			
			
			 
			 // busca si la vía no esta ya en el mapa, y el id de via mas grande
			// para no repetir
			Iterator<Via> it = Vias.iterator();
			Via temp;
			while (it.hasNext()) {
				temp = it.next();
				//if (linea.equals(temp))
				//	return;
				if (temp.getID() >= idMax)
					idMax = temp.getID();
			}
			 
			
			LineaBus temp2=null;
			// Debe buscar en la lista de lineas de autobuses para poder asignar un id de linea de bus único.
			Iterator<LineaBus> it2 = LineasAutobuses.iterator();
			while (it.hasNext()) {
				temp2 = it2.next();
				if (linea.equals(temp2))
					return;
				if (temp2.getID() >= idMax)
					idMax = temp2.getID();
			}

			LineasAutobuses.add(linea);
			linea.setID(idMax + 1);
			cambios_en_mapa=true;
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
			cambios_en_mapa=true;
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
			cambios_en_mapa=true;
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
		cambios_en_mapa=false;
		if (tramo != null && Tramos.contains(tramo)) {
			if(esDeLineasBus(tramo)){
			 // Preguntar si desea eliminar las lineas de Bus que contienen el tramo
				int n=JOptionPane.showConfirmDialog(null, 
			                Messages.getString("MLEliminarTramo.1"), //$NON-NLS-1$
			                Messages.getString("MLEliminarTramo.2"), //$NON-NLS-1$
				 	 		JOptionPane.OK_CANCEL_OPTION);
				if (n==0){
					//Eliminar las lineas de bus que continen al tramo
					eliminarLineasAutobus(tramo);
				}
				else 
					//El usuario aborta la operacion
					return false;
			}
			if(esDeVia(tramo)){
				//Eliminar la via que contiene al tramo
				int n=JOptionPane.showConfirmDialog(null, 
		                Messages.getString("Mapa.1") + //$NON-NLS-1$
		                Messages.getString("Mapa.2") + //$NON-NLS-1$
		                Messages.getString("Mapa.3"),Messages.getString("Mapa.4"),	JOptionPane.OK_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
				if(n==0){
					//Eliminar las vias que contienen al tramo
					cambios_en_mapa=true;
					return eliminarVias(tramo);
				}
				else
					//el usuario aborta la operacion
					return false;				
			}
			Tramos.remove(tramo);
			tramo.getNodoFinal().quitarTramo(tramo);
			tramo.getNodoInicial().quitarTramo(tramo);
			cambios_en_mapa=true;
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
	 * @param via
	 *            Via que se desea quitar de la lista
	 * @return Booelano que inidca si se pudo elimnar el tramo
	 */
	public boolean eliminar(Via via) {
		if (via != null && Vias.contains(via)) {

			// Borrar segmentos que aparezcan en la lista de tramos de la vía
			// concreta
			// Ese borrar segmentos ya borrará los nodos correspondientes
			// (recursivo)
			Vias.remove(via);
			Iterator<Tramo> tram = via.getTramos().iterator();
			while (tram.hasNext()) {
				Tramo aux = tram.next();
				this.eliminar(aux);
				eliminar(aux.getNodoInicial());
				eliminar(aux.getNodoFinal());
			}
			cambios_en_mapa=true;
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
			cambios_en_mapa=true;
			return true;
		}
		return false;
	}

	public void eliminar(Señal señal) {
		Señales.remove(señal);
		cambios_en_mapa=true;
	}
	
	/**
	 * Método que elimina todas las lineas de autobus que contengan al tramo pasado 
	 * como parametro
	 * 
	 * @param tramo
	 * 				tramo que pertenece a la/s linea/s de autobus que deben ser eliminadas
	 */
	public void eliminarLineasAutobus(Tramo tramo){
		Iterator<LineaBus> it =this.LineasAutobuses.iterator();
		ArrayList<LineaBus> lineasAeliminar = new ArrayList<LineaBus>();
		while(it.hasNext()){
			LineaBus linea =it.next();
			if(linea.getTramos().contains(tramo))
				lineasAeliminar.add(linea);
		}
		it=lineasAeliminar.iterator();
		while(it.hasNext()){
			eliminarLineaAutobus(it.next());
		}
	}

	/**
	 * Método que elimina todas las vias que contengan al tramo pasado 
	 * como parametro
	 * 
	 * @param tramo
	 * 				tramo que pertenece a la/s via/s que deben ser eliminadas
	 */
	public boolean eliminarVias(Tramo tramo){
		boolean baux = true;
		Iterator<Via> it =Vias.iterator();
		ArrayList<Via> viasAeliminar = new ArrayList<Via>();
		while(it.hasNext()){
			Via via =it.next();
			if(via.getTramos().contains(tramo))
				viasAeliminar.add(via);
		}
		it=viasAeliminar.iterator();
		while(it.hasNext()){
			baux &= eliminar(it.next()); 
		}
		return baux;
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

	public ArrayList<LineaBus> getLineasAutobuses() {
		return LineasAutobuses;
	}

	public void setLineasAutobuses(ArrayList<LineaBus> lineasAutobuses) {
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
	
	public boolean isCambios_en_mapa() {
		return cambios_en_mapa;
	}

	public void setCambios_en_mapa(boolean cambios_en_mapa) {
		this.cambios_en_mapa = cambios_en_mapa;
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
		if (encontrado)
			return nodoaux;
		else
			return null;
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
		if (encontrado)
			return tramoaux;
		else
			return null;
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
		if (encontrado)
			return viaaux;
		else
			return null;
	}

	/**
	 * Devuelve la primera línea de bus (no desambigua si hay varios) que tiene
	 * como nombre el parámetro.
	 * 
	 * @param nombre
	 * @return
	 */
	public Via buscarLineaBus(String nombre) {
		Iterator<LineaBus> v = LineasAutobuses.iterator();
		LineaBus lineaAux = null;
		String nomaux;
		boolean encontrado = false;
		while (v.hasNext() && !encontrado) {
			lineaAux = v.next();
			nomaux = lineaAux.getNombre();
			if (nomaux != null && nomaux.equals(nombre))
				encontrado = true;
		}
		if (encontrado)
			return lineaAux;
		else
			return null;
	}
	
	public boolean tieneHospitales() {
		Iterator<Nodo> nodos = Nodos.iterator();
		while(nodos.hasNext()) {
			Nodo nodo = nodos.next();
			if (nodo.getTipo() != null) {
				if (nodo.getTipo().getValorTipo().compareToIgnoreCase("") == 0) //$NON-NLS-1$
					return true;
			}
		}
		return false;
	}
	
	public boolean esDeLineasBus(Tramo tramo){
		Iterator<LineaBus> LineasBus = LineasAutobuses.iterator();
		while (LineasBus.hasNext()){
			if (LineasBus.next().getTramos().contains(tramo))return true;
		}
		return false;
	}
	
	public boolean esDeVia(Tramo tramo){
		Iterator<Via> vias = Vias.iterator();
		while (vias.hasNext()){
			if(vias.next().getTramos().contains(tramo)) return true;
		}
		return false;
	}
	
	public boolean tieneLineasBus() {
		return !LineasAutobuses.isEmpty();
	}

}
