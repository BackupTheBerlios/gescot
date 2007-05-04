package is.SimTraffic.jUnit.LibreriaIA;

import is.SimTraffic.LibreriaIA.NodoIA;
import junit.framework.TestCase;

public class NodoIATest extends TestCase {
	
	NodoIA n1, n2, n3, n4;
	
	public NodoIATest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		NodoIA n1 = new NodoIA(null);
		NodoIA n2 = new NodoIA(null);
		NodoIA n3 = new NodoIA(null);
		NodoIA n4 = new NodoIA(null);
		n1.setCoste_camino(1);
		n2.setCoste_camino(2);
		n3.setCoste_camino(1);
		n4.setCoste_camino(3);
		n1.setProfundidad(1);
		n2.setProfundidad(3);
		n3.setProfundidad(1);
		n4.setProfundidad(3);
		n1.setNodoPadre(n2);
		n3.setNodoPadre(n2);
		n4.setNodoPadre(n3);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.NodoIA(IEstado)'
	 */
	public void testNodoIA() {

	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.getCoste_camino()'
	 */
	public void testGetCoste_camino() {
		assertEquals(n1.getCoste_camino(), n3.getCoste_camino());
		assertEquals(n2.getCoste_camino(), 2);
		assertEquals(n4.getCoste_camino(), 3);
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.setCoste_camino(float)'
	 */
	public void testSetCoste_camino() {
		
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.getEstado()'
	 */
	public void testGetEstado() {
		// TODO
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.setEstado(IEstado)'
	 */
	public void testSetEstado() {
		
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.getNodoPadre()'
	 */
	public void testGetNodoPadre() {
		assertEquals(n1.getNodoPadre(), n2);
		assertEquals(n2.getNodoPadre(), null);
		assertEquals(n3.getNodoPadre(), n2);
		assertEquals(n4.getNodoPadre(), n3);
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.setNodoPadre(NodoIA)'
	 */
	public void testSetNodoPadre() {

	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.getOperador()'
	 */
	public void testGetOperador() {

	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.setOperador(IOperador)'
	 */
	public void testSetOperador() {
		// TODO
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.getProfundidad()'
	 */
	public void testGetProfundidad() {
		assertEquals(n1.getProfundidad(), n3.getProfundidad());
		assertEquals(n1.getProfundidad(), 1);
		assertEquals(n2.getProfundidad(), n4.getProfundidad());
		assertEquals(n4.getProfundidad(), 3);
	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.setProfundidad(int)'
	 */
	public void testSetProfundidad() {

	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.mostrarInfo()'
	 */
	public void testMostrarInfo() {

	}

	/*
	 * Test method for 'is.SimTraffic.LibreriaIA.NodoIA.main(String[])'
	 */
	public void testMain() {

	}

}
