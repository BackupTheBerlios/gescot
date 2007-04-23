package is.SimTraffic.jUnit.LibreriaIA;

import is.SimTraffic.LibreriaIA.ComparadorNodosCoste;
import is.SimTraffic.LibreriaIA.NodoIA;
import junit.framework.TestCase;

/**
 * Unidad JUnit para probar la clase ComparadorNodosCoste.
 */
public class ComparadorNodosCosteTest extends TestCase{
	/**
	 * Método de test para realizar las pruebas sobre el método Compare
	 *
	 */
	public void testCompare(){
		ComparadorNodosCoste comparador = new ComparadorNodosCoste(); 
		NodoIA nodo1 = new NodoIA(null);
		NodoIA nodo2 = new NodoIA(null);
		NodoIA nodo3 = new NodoIA(null);
		NodoIA nodo4 = new NodoIA(null);
		
		nodo1.setCoste_camino(1);
		nodo2.setCoste_camino(1);
		nodo3.setCoste_camino(2);
		nodo4.setCoste_camino(0);
		
		// El coste de un nodo debe ser igual al de si mismo.
		assertEquals ((comparador.compare(nodo1,nodo1)),0);		
		assertEquals ((comparador.compare(nodo1,nodo2)),0);		
		assertEquals ((comparador.compare(nodo1,nodo3)),-1);
		assertEquals ((comparador.compare(nodo1,nodo4)),1);		
	}
	
}
