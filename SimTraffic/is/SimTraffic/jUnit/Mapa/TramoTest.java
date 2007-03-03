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
		int hash = tramo1.hashCode();
		int hashEsperada = 11;
		hashEsperada = 211 * hashEsperada + tramo1.getNodoInicial().hashCode() + 
							tramo1.getNodoFinal().hashCode();
		assertEquals(hashEsperada,hash);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.Tramo(Nodo, Nodo)'
	 */
	public void testTramo() {
		assertEquals(tramo1.getNodoInicial(),nodo1);
		assertEquals(tramo1.getNodoFinal(),nodo2);
		assertEquals(tramo1.getNumCarrilesDir1(),1);	
		assertEquals(tramo1.getNumCarrilesDir2(),1);
		float velMax = 40;
		assertEquals(tramo1.getVelMax(),velMax);
		assertEquals(tramo1.getTipo(),0);
		
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.tieneNodo(Nodo)'
	 */
	public void testTieneNodo() {
		assertTrue(tramo1.tieneNodo(nodo1));
		assertTrue(tramo1.tieneNodo(nodo2));
		Nodo nodo3 = new Nodo(new Posicion(10,10));
		assertFalse(tramo1.tieneNodo(nodo3));
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
		float velmax = 150;
		tramo1.setVelMax(velmax);
		assertEquals(tramo1.getVelMax(),velmax);
		
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getVelMax()'
	 */
	public void testGetVelMax() {
		float velmax = 150;
		tramo1.setVelMax(velmax);
		assertEquals(tramo1.getVelMax(),velmax);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setNumCarrilesDir1(int)'
	 */
	public void testSetNumCarrilesDir1() {
		int numDir1 = 2;
		tramo1.setNumCarrilesDir1(numDir1);
		assertEquals (numDir1,tramo1.getNumCarrilesDir1());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNumCarrilesDir1()'
	 */
	public void testGetNumCarrilesDir1() {
		int numDir1 = 2;
		tramo1.setNumCarrilesDir1(numDir1);
		assertEquals (numDir1,tramo1.getNumCarrilesDir1());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setNumCarrilesDir2(int)'
	 */
	public void testSetNumCarrilesDir2() {
		int numDir2 = 2;
		tramo1.setNumCarrilesDir2(numDir2);
		assertEquals (numDir2,tramo1.getNumCarrilesDir2());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNumCarrilesDir2()'
	 */
	public void testGetNumCarrilesDir2() {
		int numDir2 = 2;
		tramo1.setNumCarrilesDir2(numDir2);
		assertEquals (numDir2,tramo1.getNumCarrilesDir2());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.setTipo(int)'
	 */
	public void testSetTipo() {
		int tipo = 5;
		tramo1.setTipo(tipo);
		assertEquals(tipo,tramo1.getTipo());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getTipo()'
	 */
	public void testGetTipo() {
		int tipo = 5;
		tramo1.setTipo(tipo);
		assertEquals(tipo,tramo1.getTipo());
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNodoInicial()'
	 */
	public void testGetNodoInicial() {
		Nodo nodoIni = tramo1.getNodoInicial();
		assertEquals(nodo1,nodoIni);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Tramo.getNodoFinal()'
	 */
	public void testGetNodoFinal() {
		Nodo nodoFin = tramo1.getNodoFinal();
		assertEquals(nodo2,nodoFin);
	}

}
