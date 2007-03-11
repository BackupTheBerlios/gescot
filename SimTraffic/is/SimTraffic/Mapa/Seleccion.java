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
		nodosSeleccionados = new ArrayList();
		señalesSeleccionadas = new ArrayList();
		tramosSeleccionados = new ArrayList(); 
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
	
}
