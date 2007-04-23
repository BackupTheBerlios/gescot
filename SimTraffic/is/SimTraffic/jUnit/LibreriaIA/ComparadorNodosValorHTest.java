package is.SimTraffic.jUnit.LibreriaIA;

import is.SimTraffic.LibreriaIA.ComparadorNodosValorH;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.EstadoDistanciaNodos;
import junit.framework.TestCase;

/**
 * Unidad JUnit para probar la clase ComparadorNodosValorH.
 */

public class ComparadorNodosValorHTest extends TestCase{
	/**
	 * Método de test para realizar las pruebas sobre el método Compare
	 *
	 */
	public void testCompare(){
		ComparadorNodosValorH comparador = new ComparadorNodosValorH(); 

		EstadoDistanciaNodos e1 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e2 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e3 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e4 = new EstadoDistanciaNodos(null);
		
		e1.setValorHeuristico(1);
		e2.setValorHeuristico(1);
		e3.setValorHeuristico(2);
		e4.setValorHeuristico(0);

		NodoIA nodo1 = new NodoIA(e1);
		NodoIA nodo2 = new NodoIA(e2);
		NodoIA nodo3 = new NodoIA(e3);
		NodoIA nodo4 = new NodoIA(e4);
		
		// El coste de un nodo debe ser igual al de si mismo.
		assertEquals ((comparador.compare(nodo1,nodo1)),0);		
		assertEquals ((comparador.compare(nodo1,nodo2)),0);		
		assertEquals ((comparador.compare(nodo1,nodo3)),-1);
		assertEquals ((comparador.compare(nodo1,nodo4)),1);		
	}
	
}
