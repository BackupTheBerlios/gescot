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
	 * Mantiene la lita de señales seleccionadas del mapa
	 */
	private ArrayList<Señal> señalesSeleccionadas;

	/**
	 * Mantiene la lista de tramos seleccionados del Mapa
	 */
	private ArrayList<Tramo> tramosSeleccionados;
	
	public Seleccion (){
		nodosSeleccionados = new ArrayList<Nodo>();
		señalesSeleccionadas = new ArrayList<Señal>();
		tramosSeleccionados = new ArrayList<Tramo>(); 
	}

	public void añadirNodo(Nodo nodo){
		this.nodosSeleccionados.add(nodo);
	}
	
	public void añadirTramo(Tramo tramo){
		this.tramosSeleccionados.add(tramo);
	}
	
	
	public ArrayList<Nodo> getNodosSeleccionados() {
		return nodosSeleccionados;
	}

	public void setNodosSeleccionados(ArrayList<Nodo> nodosSeleccionados) {
		this.nodosSeleccionados = nodosSeleccionados;
	}

	public ArrayList<Señal> getSeñalesSeleccionadas() {
		return señalesSeleccionadas;
	}

	public void setSeñalesSeleccionadas(ArrayList<Señal> señalesSeleccionadas) {
		this.señalesSeleccionadas = señalesSeleccionadas;
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
	
	public Tramo existeTramo (Tramo tramo) {
		for (int i=0; i<tramosSeleccionados.size(); i++){
			if (tramosSeleccionados.get(i).getNodoInicial().getPos().equals(tramo.getNodoInicial().getPos()) &&
					tramosSeleccionados.get(i).getNodoFinal().getPos().equals(tramo.getNodoFinal().getPos())
					)
				return tramosSeleccionados.get(i);
		}
		return null;
	}
	
	public boolean esVacia () {
		return (nodosSeleccionados.isEmpty() && tramosSeleccionados.isEmpty() && señalesSeleccionadas.isEmpty());
	}
	
}
