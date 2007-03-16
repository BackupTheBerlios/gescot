package is.SimTraffic.jUnit.Mapa;

import java.util.ArrayList;

import is.SimTraffic.Mapa.*;
import is.SimTraffic.Mapa.TipoElemento.*;
import junit.framework.TestCase;

public class ViaTest extends TestCase {

	Via via1;
	ArrayList<Tramo> at;
	Tramo t1, t2, t3;
	
	protected void setUp() throws Exception {
		super.setUp();
		via1 = new Via();
		Nodo n1 = new Nodo(new Posicion(10, 20));
		Nodo n2 = new Nodo(new Posicion(30, 40));
		Nodo n3 = new Nodo(new Posicion(50, 60));
		t1 = new Tramo(n1, n2);
		t2 = new Tramo(n2, n3);
		t3 = new Tramo(n3, n1);
		at = new ArrayList<Tramo>();
		at.add(t1);
		at.add(t2);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.Via()'
	 */
	public void testVia() {
		assertTrue(via1.getTramos().isEmpty());
		assertNull(via1.getNombre());
		assertEquals(via1.getID(), 0);
		assertNull(via1.getTipo());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.getID()'
	 */
	public void testGetID() {
		via1.setID(69);
		assertEquals(via1.getID(), 69);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.transformaraOSM()'
	 */
	public void testTransformaraOSM() {
		//falta!!!
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.getTipo()'
	 */
	public void testGetTipo() {
		ITipoElemento te = new TipoViaHighway("Carretera");
		via1.setTipo(te);
		assertEquals(via1.getTipo(), te);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.getNombre()'
	 */
	public void testGetNombre() {
		via1.setNombre("Alcala");
		assertEquals(via1.getNombre(), "Alcala");
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.setNombre(String)'
	 */
	public void testSetNombre() {
		//se comprueba dentro de GetNombre()
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.getTramos()'
	 */
	public void testGetTramos() {
		via1.setTramos(at);
		assertEquals(via1.getTramos(), at);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.setTramos(ArrayList<Tramo>)'
	 */
	public void testSetTramos() {
		//se comprueba dentro de GetTramos
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.addTramo(Tramo)'
	 */
	public void testAddTramo() {
		via1.addTramo(t1);
		via1.addTramo(t2);
		assertTrue(via1.getTramos().contains(t1));
		assertTrue(via1.getTramos().contains(t2));
		assertFalse(via1.getTramos().contains(t3));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.setID(int)'
	 */
	public void testSetID() {
		//se comprueba dentro de GetID()
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Via.setTipo(ITipoElemento)'
	 */
	public void testSetTipo() {
		//se comprueba dentro de getTipo()
	}

}
