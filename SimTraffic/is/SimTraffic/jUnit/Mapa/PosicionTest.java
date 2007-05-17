package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase Posicion
 */

public class PosicionTest extends TestCase {
	/**
	 * Test method for 'is.SimTraffic.Mapa.Posicion.equals(Object)'
	 * Se comprueba que dos posiciones iguales sean realmente iguales
	 */
	public void testEqualsObject() {
		Posicion pos = new Posicion(1.0f,1.0f);
		Posicion pos2 = new Posicion(1.0f,1.0f);
		Posicion pos3 = new Posicion(0.5f,0.5f);
		if (pos != pos)
			fail(Messages.getString("PosicionTest.0")); //$NON-NLS-1$
		if (!pos.equals(pos2))
			fail(Messages.getString("PosicionTest.1"));			 //$NON-NLS-1$
		if (pos.equals(pos3))
			fail(Messages.getString("PosicionTest.2"));	 //$NON-NLS-1$
	}
	
	/**
	 * Test method for 'is.SimTraffic.Mapa.Posicion.clone()'
	 * Se comprueba que el método clone funcione de manera correcta.
	 */	
	public void testClone(){
		Posicion pos1 = new Posicion(1.0f,1.0f);
		Posicion pos2 = new Posicion(0.5f,0.5f);
		Posicion pos3 = pos1.clone();
		assertNotSame(pos3,pos1);
		assertTrue(pos1.equals(pos3));
		assertTrue(!pos2.equals(pos3));
	}
	
	/**
	 * Test method for 'is.SimTraffic.Mapa.Posicion.clone()'
	 * Se comprueba que el método clone funcione de manera correcta.
	 */	
	public void testHashCode(){
		Posicion pos1 = new Posicion(1.0f,1.0f);
		Posicion pos2 = new Posicion(0.5f,0.5f);
		assertEquals(pos1.hashCode(),pos1.hashCode());
		assertTrue(!(pos1.hashCode()==pos2.hashCode()));
	}	
}
