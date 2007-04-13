package is.SimTraffic.LibreriaIA;

import java.util.Comparator;

/**
 * Implementa el interfaz Comparator para comparar 2 nodos y ordenarlos de acuerdo a su 
 * coste. Esto es necesario para poder utilizar la estructura de datos 
 * PriorityQueue de la librer�a java.util, y as� poder establecer el orden indicado para 
 * la estructura abiertos en el algoritmo de b�squeda de coste uniforme, lo que resultar� 
 * en un algoritmo mucho m�s eficiente que con una estructura de tipo Vector.
 */
public class ComparadorNodosCoste implements Comparator {

	/**Info API: Compares its two arguments for order. Returns a negative integer, zero, 
	 * or a positive integer as the first argument is less than, equal to, or greater than the second.
	 * M�todo necesario para establecer el orden de la cola con prioridad.
	 */
	public int compare(Object arg0, Object arg1) {
		float valorc1 = ((NodoIA)arg0).getCoste_camino();
		float valorc2 = ((NodoIA)arg1).getCoste_camino();
		if ( valorc1 < valorc2 )
			return -1;
		else if ( valorc1 > valorc2 )
			return +1;
		else //Iguales
			return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
