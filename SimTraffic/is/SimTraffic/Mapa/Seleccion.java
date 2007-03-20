package is.SimTraffic.Mapa;

import java.util.ArrayList;

/**
 * Clase que permite almacenar los nodos y tramos que se encuentran
 * seleccionados.
 * @author Grupo IS trafico
 *
 */
public class Seleccion {
	/**
	 * Mantiene la lista de nodos seleccionados del mapa
	 */
	private ArrayList<Nodo> nodosSeleccionados;

	/**
	 * Mantiene la lita de se�ales seleccionadas del mapa
	 */
	private ArrayList<Se�al> se�alesSeleccionadas;

	/**
	 * Mantiene la lista de tramos seleccionados del Mapa
	 */
	private ArrayList<Tramo> tramosSeleccionados;
	
	public Seleccion (){
		nodosSeleccionados = new ArrayList<Nodo>();
		se�alesSeleccionadas = new ArrayList<Se�al>();
		tramosSeleccionados = new ArrayList<Tramo>(); 
	}

	public void a�adirNodo(Nodo nodo){
		this.nodosSeleccionados.add(nodo);
	}
	
	public void a�adirTramo(Tramo tramo){
		this.tramosSeleccionados.add(tramo);
	}
	
	
	public ArrayList<Nodo> getNodosSeleccionados() {
		return nodosSeleccionados;
	}

	public void setNodosSeleccionados(ArrayList<Nodo> nodosSeleccionados) {
		this.nodosSeleccionados = nodosSeleccionados;
	}

	public ArrayList<Se�al> getSe�alesSeleccionadas() {
		return se�alesSeleccionadas;
	}

	public void setSe�alesSeleccionadas(ArrayList<Se�al> se�alesSeleccionadas) {
		this.se�alesSeleccionadas = se�alesSeleccionadas;
	}

	public ArrayList<Tramo> getTramosSeleccionados() {
		return tramosSeleccionados;
	}

	public void setTramosSeleccionados(ArrayList<Tramo> tramosSeleccionados) {
		this.tramosSeleccionados = tramosSeleccionados;
	}
	
	public void modificaNodo (Nodo nodo) {
		for (int i=0; i<nodosSeleccionados.size(); i++) {
			Nodo nodoTemp = nodosSeleccionados.get(i);
			if (nodoTemp.equals(nodo)) {
				nodoTemp = nodo;
			}
		}
	}
	
	public Nodo existeNodo (Nodo nodo) {
		Posicion pos = nodo.getPos();
		for (int i=0; i<nodosSeleccionados.size(); i++){
			if (nodosSeleccionados.get(i).getPos().equals(pos))
				return nodosSeleccionados.get(i);
		}
		return null;
	}
	
}
