package is.SimTraffic.Mapa;

import java.awt.Point;
import java.awt.Rectangle;
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
	
	/**
	 * Mantiene la lista de v�as del mapa. 
	 * Aclaraci�n: Uso actual para lineas de autobuses �nicamente.
	 */
	private ArrayList<Via> Vias;
	
	/**
	 * Indica la seleccion actual del mapa (nodos, tramos y se�ales).
	 */
	private Seleccion seleccion;

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
		Vias = new ArrayList<Via>();
		
		seleccion = new Seleccion();
	}

	/**
	 * Utilizado para cargar el mapa.
	 * @param nodos
	 * @param tramos
	 */
	public Mapa(ArrayList<Nodo> nodos, ArrayList<Tramo> tramos) {
		// TODO Auto-generated constructor stub
		Nodos = nodos;
		Tramos = tramos;
		Se�ales = new ArrayList<Se�al>();
		Vias = new ArrayList<Via>();
		
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
				if (temp.getID() >= idMax) //Antes nodo.getID()
					idMax = temp.getID();
			}
			
			//a�ade el nodo
			Nodos.add(nodo);
			nodo.setID(idMax + 1);

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
		int idMax = 1;
		if (tramo != null) {
			// busca si el tramo no esta ya en el mapa, y el id de tramo mas grande
			//  para no repetir
			Iterator<Tramo> it = Tramos.iterator();
			Tramo temp;
			while (it.hasNext()) {
				temp = it.next();
				if (tramo.equals(temp))
					return;
				if (tramo.getID() >= idMax)
					idMax = tramo.getID();
			}

			// a�ade el tramo, salvo que comienze y termie en el mismo nodo
			if (!tramo.getNodoFinal().equals(tramo.getNodoInicial()))
				if (Nodos.contains(tramo.getNodoInicial())
						&& Nodos.contains(tramo.getNodoFinal())) {
					tramo.getNodoInicial().a�adirTramo(tramo);
					tramo.getNodoFinal().a�adirTramo(tramo);
					Tramos.add(tramo);
					tramo.setID(idMax+1);
					//Cambio en el m�todo de asignaci�n de ID
					tramo.asignarIDunico();
				}
		
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
	
	/**
	 * En funci�n de un rectangulo pasado como par�metro, modifica la seleccion con todos los elementos
	 * que est�n contenido en dicho rect�ngulo.
	 * @param rectanguloSeleccion
	 */
	public void seleccionaEnRectangulo(Rectangle rectanguloSeleccion,int tipoDeSeleccion){
		
		if (tipoDeSeleccion==0) { //se elige seleccionar nodos
			Nodo nodoTemp;
			Point punto;
			for (int i=0;i<Nodos.size();i++){
				nodoTemp = Nodos.get(i);
				punto = new Point(nodoTemp.getPos().getPosX(),nodoTemp.getPos().getPosY());
				if (rectanguloSeleccion.contains(punto)){
					this.seleccion.a�adirNodo(nodoTemp);
				}			
			}
		}
		if (tipoDeSeleccion==1) { //se elige seleccionar elementos
			Nodo nodoTemp;
			Point punto;
			for (int i=0;i<Nodos.size();i++){
				nodoTemp = Nodos.get(i);
				punto = new Point(nodoTemp.getPos().getPosX(),nodoTemp.getPos().getPosY());
				if (rectanguloSeleccion.contains(punto)){
					this.seleccion.a�adirNodo(nodoTemp);
				}			
			}
			Tramo tramoTemp;
			for (int i=0;i<Tramos.size();i++) {
				tramoTemp = Tramos.get(i);
				boolean encontradoInicial=false;
				boolean encontradoFinal=false;
				int j=0;
				Nodo posibleNodoInicial;
				while (j<seleccion.getNodosSeleccionados().size() && !encontradoInicial) {
					posibleNodoInicial = seleccion.getNodosSeleccionados().get(j);
					if (posibleNodoInicial.equals(tramoTemp.getNodoInicial()))
						encontradoInicial=true;
					j++;
				}
				j=0;
				Nodo posibleNodoFinal;
				while (j<seleccion.getNodosSeleccionados().size() && !encontradoFinal) {
					posibleNodoFinal = seleccion.getNodosSeleccionados().get(j);
					if (posibleNodoFinal.equals(tramoTemp.getNodoFinal()))
						encontradoFinal=true;
					j++;
				}
				if (encontradoInicial && encontradoFinal) {
					this.seleccion.a�adirTramo(tramoTemp);
				}
			}
			
		}
		if (tipoDeSeleccion==2) { //se elige seleccionar tramos
			Nodo nodoTemp;
			Point punto;
			ArrayList<Nodo> nodosSeleccionados = new ArrayList();
			for (int i=0;i<Nodos.size();i++){
				nodoTemp = Nodos.get(i);
				punto = new Point(nodoTemp.getPos().getPosX(),nodoTemp.getPos().getPosY());
				if (rectanguloSeleccion.contains(punto)){
					nodosSeleccionados.add(nodoTemp);
				}			
			}
			Tramo tramoTemp;
			for (int i=0;i<Tramos.size();i++) {
				tramoTemp = Tramos.get(i);
				boolean encontradoInicial=false;
				boolean encontradoFinal=false;
				int j=0;
				Nodo posibleNodoInicial;
				while (j<nodosSeleccionados.size() && !encontradoInicial) {
					posibleNodoInicial = nodosSeleccionados.get(j);
					if (posibleNodoInicial.equals(tramoTemp.getNodoInicial()))
						encontradoInicial=true;
					j++;
				}
				j=0;
				Nodo posibleNodoFinal;
				while (j<nodosSeleccionados.size() && !encontradoFinal) {
					posibleNodoFinal = nodosSeleccionados.get(j);
					if (posibleNodoFinal.equals(tramoTemp.getNodoFinal()))
						encontradoFinal=true;
					j++;
				}
				if (encontradoInicial && encontradoFinal) {
					this.seleccion.a�adirTramo(tramoTemp);
				}
			}
		
		}
		
	//int a=5;
	}
	
	/**
	 * Deselecciona todos los elementos que estaban seleccionados. 
	 */
	public void limpiaSeleccion(){
		this.seleccion = null;
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

}
