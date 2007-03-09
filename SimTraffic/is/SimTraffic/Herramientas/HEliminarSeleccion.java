package is.SimTraffic.Herramientas;

import java.util.List;
import java.util.ArrayList;
import is.SimTraffic.Mapa.*;
import is.SimTraffic.IModelo;

/**
 * Herramienta que permite realizar la operaci�n de hacer/deshacer
 * la eliminaci�n de una selecci�n de elementos del mapa
 * A�N NO EST� PROBADA
 */

public class HEliminarSeleccion implements IHerramienta {
	
	/**
	 * Lista de nodos seleccionados
	 */
	private List<Nodo> nodos;
	/**
	 * Lista de tramos seleccionados
	 */
	private List<Tramo> tramos;
	
	/**
	 * Constructora de la herramienta
	 * Se inicializan las listas de nodos y tramos
	 *
	 */
	public HEliminarSeleccion (List<Nodo> nodos, List<Tramo> tramos) {
		this.nodos = nodos;
		this.tramos = tramos;
	}
	/**
	 * M�todo hacer de la herramienta
	 * Se recorren los nodos y se eliminan del mapa
	 * Se recorren los tramos y se eliminan del mapa
	 */
	public int hacer (IModelo modelo) {
		for (int i=0; i<tramos.size(); i++)
			modelo.getMapa().eliminar(tramos.get(i));
		for (int i=0; i<nodos.size(); i++)
			modelo.getMapa().eliminar(nodos.get(i));	
		return 0;
	}
	
	/**
	 * Operacion deshacer de la herramienta
	 * Se recorren los nodos y tramos y se insertan de nuevo en el mapa 
	 */
	public int deshacer (IModelo modelo) {
		for (int i=0; i<nodos.size(); i++)
			modelo.getMapa().insertar(nodos.get(i));	
		
		for (int i=0; i<tramos.size(); i++)
			modelo.getMapa().insertar(tramos.get(i));
		return 0;
	}

}
