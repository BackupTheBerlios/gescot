package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCreaItinerarioEntreDosNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class HCreaItinerarioEntreDosNodosTest extends TestCase {

	HCreaItinerarioEntreDosNodos h;
	Nodo n1, n2, n3, n4;
	Tramo t12, t13, t34;
	Modelo m;
	
	
	public HCreaItinerarioEntreDosNodosTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		//Añadimos 4 nodos al modelo
		n1 = new Nodo(1, Messages.getString("HCreaItinerarioEntreDosNodosTest.0"), new Posicion(10, 10), null); //$NON-NLS-1$
		n2 = new Nodo(2, Messages.getString("HCreaItinerarioEntreDosNodosTest.1"), new Posicion(100, 100), null); //$NON-NLS-1$
		n3 = new Nodo(3, Messages.getString("HCreaItinerarioEntreDosNodosTest.2"), new Posicion(50, 20), null); //$NON-NLS-1$
		n4 = new Nodo(4, Messages.getString("HCreaItinerarioEntreDosNodosTest.3"), new Posicion(70, 40), null); //$NON-NLS-1$
		t12 = new Tramo(n1, n2);
		t13 = new Tramo(n1, n3);
		t34 = new Tramo(n3, n4);
		m.getMapa().insertar(n1);
		m.getMapa().insertar(n2);
		m.getMapa().insertar(n3);
		m.getMapa().insertar(n4);
		m.getMapa().insertar(t12);
		m.getMapa().insertar(t13);
		m.getMapa().insertar(t34);
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCreaItinerarioEntreDosNodos.hacer(IModelo)'
	 */
	public void testHacer() {
		//Comprobamos que el itinerario generado es correcto
		h = new HCreaItinerarioEntreDosNodos(n1, n4);
		h.hacer(m);
		assertNotNull(m.getMapa().getSeleccion().existeNodo(n1));
		assertNull(m.getMapa().getSeleccion().existeNodo(n2));
		assertNotNull(m.getMapa().getSeleccion().existeNodo(n3));
		assertNotNull(m.getMapa().getSeleccion().existeNodo(n4));
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCreaItinerarioEntreDosNodos.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		//TODO: Por ahora, deshacer no hace nada
	}

}
