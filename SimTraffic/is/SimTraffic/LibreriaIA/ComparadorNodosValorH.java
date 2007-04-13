package is.SimTraffic.LibreriaIA;

import java.util.Comparator;

/**
 * Implementa el interfaz Comparator para comparar 2 nodos y ordenarlos de acuerdo a su 
 * valor heuristico. Esto es necesario para poder utilizar la estructura de datos 
 * PriorityQueue de la librería java.util, y así poder establecer el orden indicado para 
 * la estructura abiertos en el algoritmo de búsqueda voraz(greedy), lo que resultará
 * en un algoritmo mucho más eficiente que con una estructura de tipo Vector.
 */
public class ComparadorNodosValorH implements Comparator {

	/**Info API: Compares its two arguments for order. Returns a negative integer, zero, 
	 * or a positive integer as the first argument is less than, equal to, or greater than the second.
	 * Método necesario para establecer el orden de la cola con prioridad.
	 */
	public int compare(Object arg0, Object arg1) {
		float valorh1 = ((NodoIA)arg0).getEstado().getValorHeuristico();
		float valorh2 = ((NodoIA)arg1).getEstado().getValorHeuristico();
		if ( valorh1 < valorh2 )
			return -1;
		else if ( valorh1 > valorh2 )
			return +1;
		else //Iguales
			return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
