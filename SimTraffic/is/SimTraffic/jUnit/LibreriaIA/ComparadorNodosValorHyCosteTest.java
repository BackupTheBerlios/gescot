package is.SimTraffic.jUnit.LibreriaIA;

import is.SimTraffic.LibreriaIA.ComparadorNodosValorHyCoste;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.EstadoDistanciaNodos;
import junit.framework.TestCase;

/**
 * Unidad JUnit para probar la clase ComparadorNodosValorHyCoste.
 */

public class ComparadorNodosValorHyCosteTest extends TestCase{
	/**
	 * Método de test para realizar las pruebas sobre el método Compare
	 *
	 */
	public void testCompare(){
		ComparadorNodosValorHyCoste comparador = new ComparadorNodosValorHyCoste(); 

		EstadoDistanciaNodos e1 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e2 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e3 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e4 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e5 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e6 = new EstadoDistanciaNodos(null);
		EstadoDistanciaNodos e7 = new EstadoDistanciaNodos(null);
		
		e1.setValorHeuristico(5);
		e2.setValorHeuristico(5);
		e3.setValorHeuristico(5);
		e4.setValorHeuristico(4);
		e5.setValorHeuristico(2);
		e6.setValorHeuristico(2);
		e7.setValorHeuristico(8);
				

		NodoIA nodo1 = new NodoIA(e1);
		NodoIA nodo2 = new NodoIA(e2);
		NodoIA nodo3 = new NodoIA(e3);
		NodoIA nodo4 = new NodoIA(e4);
		NodoIA nodo5 = new NodoIA(e5);
		NodoIA nodo6 = new NodoIA(e6);
		NodoIA nodo7 = new NodoIA(e7);
		
		nodo1.setCoste_camino(5);
		nodo2.setCoste_camino(5);
		nodo3.setCoste_camino(2);
		nodo4.setCoste_camino(8);
		nodo5.setCoste_camino(5);
		nodo6.setCoste_camino(7);
		nodo7.setCoste_camino(3);
		
		// El coste de un nodo debe ser igual al de si mismo.
		assertEquals ((comparador.compare(nodo1,nodo1)),0);		
		assertEquals ((comparador.compare(nodo1,nodo2)),0);		
		assertEquals ((comparador.compare(nodo1,nodo3)),1);
		assertEquals ((comparador.compare(nodo1,nodo4)),-1);
		assertEquals ((comparador.compare(nodo1,nodo5)),1);
		assertEquals ((comparador.compare(nodo1,nodo6)),1);
		assertEquals ((comparador.compare(nodo1,nodo7)),-1);
	}
}	