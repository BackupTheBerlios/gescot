package is.SimTraffic.jUnit.Mapa;

import junit.framework.TestCase;
import is.SimTraffic.Mapa.*;


public class TramoTest extends TestCase {
	
	private Nodo nodo1;
	private Nodo nodo2;
	private Tramo tramo1;
		
	protected void setUp() throws Exception {
		super.setUp();
		nodo1=new Nodo(new Posicion(100,100));
		nodo2=new Nodo(new Posicion(200,200));
		tramo1=new Tramo(nodo1,nodo2);
		
		
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.hashCode()'
	 */
	public void testHashCode() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.Tramo(Nodo, Nodo)'
	 */
	public void testTramo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.tieneNodo(Nodo)'
	 */
	public void testTieneNodo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.equals(Object)'
	 */
	public void testEqualsObject() {
		Tramo tramo2 = new Tramo (nodo1,nodo2);
		assertTrue(tramo1.equals(tramo2));
		Nodo nodo3 = new Nodo(new Posicion (300,300));
		Tramo tramo3 = new Tramo (nodo1,nodo3);
		assertFalse(tramo1.equals(tramo3));
		
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setVelMax(float)'
	 */
	public void testSetVelMax() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getVelMax()'
	 */
	public void testGetVelMax() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setNumCarrilesDir1(int)'
	 */
	public void testSetNumCarrilesDir1() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNumCarrilesDir1()'
	 */
	public void testGetNumCarrilesDir1() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setNumCarrilesDir2(int)'
	 */
	public void testSetNumCarrilesDir2() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNumCarrilesDir2()'
	 */
	public void testGetNumCarrilesDir2() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setTipo(int)'
	 */
	public void testSetTipo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getTipo()'
	 */
	public void testGetTipo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNodoInicial()'
	 */
	public void testGetNodoInicial() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNodoFinal()'
	 */
	public void testGetNodoFinal() {

	}

}
