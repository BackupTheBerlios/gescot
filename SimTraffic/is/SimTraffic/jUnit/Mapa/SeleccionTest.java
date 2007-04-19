package is.SimTraffic.jUnit.Mapa;

import java.util.ArrayList;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class SeleccionTest extends TestCase {

	Seleccion sel;
	Nodo n1, n2, n3, n4;
	Se�al s1, s2;
	Tramo t1, t2;
	ArrayList<Nodo> an;
	ArrayList<Se�al> as;
	ArrayList<Tramo> at;
	
	protected void setUp() throws Exception {
		super.setUp();
		sel = new Seleccion();
		n1 = new Nodo(new Posicion(10, 20));
		n2 = new Nodo(new Posicion(30, 40));
		n3 = new Nodo(new Posicion(10, 10));
		n4 = new Nodo(new Posicion(20, 20));
		t1 = new Tramo(n1, n2);
		t2 = new Tramo(n3, n4);
		an = new ArrayList<Nodo>();
		an.add(n1);
		an.add(n3);
		at = new ArrayList<Tramo>();
		at.add(t1);
		
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.Seleccion()'
	 */
	public void testSeleccion() {
		assertTrue(sel.getNodosSeleccionados().isEmpty());
		assertTrue(sel.getSe�alesSeleccionadas().isEmpty());
		assertTrue(sel.getTramosSeleccionados().isEmpty());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.a�adirNodo(Nodo)'
	 */
	public void testA�adirNodo() {
		sel.a�adirNodo(n1);
		sel.a�adirNodo(n2);
		assertTrue(sel.getNodosSeleccionados().contains(n1));
		assertTrue(sel.getNodosSeleccionados().contains(n2));
		assertFalse(sel.getNodosSeleccionados().contains(n3));
		assertFalse(sel.getNodosSeleccionados().contains(n4));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.a�adirTramo(Tramo)'
	 */
	public void testA�adirTramo() {
		sel.a�adirTramo(t1);
		assertTrue(sel.getTramosSeleccionados().contains(t1));
		assertFalse(sel.getTramosSeleccionados().contains(t2));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.getNodosSeleccionados()'
	 */
	public void testGetNodosSeleccionados() {
		sel.setNodosSeleccionados(an);
		assertEquals(sel.getNodosSeleccionados(), an);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.setNodosSeleccionados(ArrayList<Nodo>)'
	 */
	public void testSetNodosSeleccionados() {
		sel.setNodosSeleccionados(an);
		assertTrue(sel.getNodosSeleccionados().contains(n1));
		assertFalse(sel.getNodosSeleccionados().contains(n2));
		assertTrue(sel.getNodosSeleccionados().contains(n3));
		assertFalse(sel.getNodosSeleccionados().contains(n4));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.getSe�alesSeleccionadas()'
	 */
	public void testGetSe�alesSeleccionadas() {
		sel.setSe�alesSeleccionadas(as);
		assertEquals(sel.getSe�alesSeleccionadas(), as);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.setSe�alesSeleccionadas(ArrayList<Se�al>)'
	 */
	public void testSetSe�alesSeleccionadas() {
		sel.setSe�alesSeleccionadas(as);
		assertTrue(sel.getSe�alesSeleccionadas().contains(s1));
		assertTrue(sel.getSe�alesSeleccionadas().contains(s2));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.getTramosSeleccionados()'
	 */
	public void testGetTramosSeleccionados() {
		sel.setTramosSeleccionados(at);
		assertEquals(sel.getTramosSeleccionados(), at);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Seleccion.setTramosSeleccionados(ArrayList<Tramo>)'
	 */
	public void testSetTramosSeleccionados() {
		sel.setTramosSeleccionados(at);
		assertTrue(sel.getTramosSeleccionados().contains(t1));
		assertFalse(sel.getTramosSeleccionados().contains(t2));
	}

}
