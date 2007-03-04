package is.SimTraffic.jUnit.Mapa;

import junit.framework.TestCase;
import is.SimTraffic.Mapa.*;


/**
 * Clase de prueba para probar la clase Tramo
 * 
 */

public class TramoTest extends TestCase {
	
	/**
	 * Nodo para hacer pruebas
	 */
	private Nodo nodo1;
	/**
	 * Nodo para hacer pruebas
	 */
	private Nodo nodo2;
	/**
	 * Tramo para hacer pruebas
	 */
	private Tramo tramo1;
	
	/**
	 * Método para hacer la inicialización de los atributos
	 * de prueba
	 */
	protected void setUp() throws Exception {
		super.setUp();
		nodo1=new Nodo(new Posicion(100,100));
		nodo2=new Nodo(new Posicion(200,200));
		tramo1=new Tramo(nodo1,nodo2);
		
		
	}

	/**
	 * Método de prueba para hashCode()
	 * Se comprueba que el valor del hashCode calculado es el esperado
	 */
	public void testHashCode() {
		int hash = tramo1.hashCode();
		int hashEsperada = 11;
		hashEsperada = 211 * hashEsperada + tramo1.getNodoInicial().hashCode() + 
							tramo1.getNodoFinal().hashCode();
		assertEquals(hashEsperada,hash);
	}

	/**
	 * Método de prueba para la constructora de Tramo
	 * Se comprueba que los nodos inicial y final se actualizacn correctamente, 
	 * y que los valores de número de carriles y los demás atributos son correctos
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

	/**
	 * Método de prueba para tieneNodo(Nodo)
	 * Se comprueba que devuelve el valor correcto cuando un tramo contiene el
	 * nodo o no 
	 */
	public void testTieneNodo() {
		assertTrue(tramo1.tieneNodo(nodo1));
		assertTrue(tramo1.tieneNodo(nodo2));
		Nodo nodo3 = new Nodo(new Posicion(10,10));
		assertFalse(tramo1.tieneNodo(nodo3));
	}

	/**
	 * Método de prueba para equals(Object)
	 * Se comprueba que los nodos inicial y final son iguales
	 */
	public void testEqualsObject() {
		Tramo tramo2 = new Tramo (nodo1,nodo2);
		assertTrue(tramo1.equals(tramo2));
		Nodo nodo3 = new Nodo(new Posicion (300,300));
		Tramo tramo3 = new Tramo (nodo1,nodo3);
		assertFalse(tramo1.equals(tramo3));
		
	}

	/**
	 * Método de prueba para setVelMax(float)
	 * Se comprueba que se modifica el valor de velMax correctamente
	 */
	public void testSetVelMax() {
		float velmax = 150;
		tramo1.setVelMax(velmax);
		assertEquals(tramo1.getVelMax(),velmax);
		
	}

	/**
	 * Método de prueba para getVelMax()
	 * Se comprueba que el valor devuelto es el correcto
	 */
	public void testGetVelMax() {
		float velmax = 150;
		tramo1.setVelMax(velmax);
		assertEquals(tramo1.getVelMax(),velmax);
	}

	/**
	 * Método de prueba para setNumCarrilesDir1(int)
	 * Se comprueba que se modifica correctamente el atributo
	 */
	public void testSetNumCarrilesDir1() {
		int numDir1 = 2;
		tramo1.setNumCarrilesDir1(numDir1);
		assertEquals (numDir1,tramo1.getNumCarrilesDir1());
	}

	/**
	 * Método de prueba para getNumCarrilesDir1(int)
	 * Se comprueba que el valor devuelto es correcto
	 */
	public void testGetNumCarrilesDir1() {
		int numDir1 = 2;
		tramo1.setNumCarrilesDir1(numDir1);
		assertEquals (numDir1,tramo1.getNumCarrilesDir1());
	}

	/**
	 * Método de prueba para setNumCarrilesDir2(int)
	 * Se comprueba que se modifica correctamente el atributo
	 */
	public void testSetNumCarrilesDir2() {
		int numDir2 = 2;
		tramo1.setNumCarrilesDir2(numDir2);
		assertEquals (numDir2,tramo1.getNumCarrilesDir2());
	}

	/**
	 * Método de prueba para getNumCarrilesDir2(int)
	 * Se comprueba que el valor devuelto es correcto
	 */
	public void testGetNumCarrilesDir2() {
		int numDir2 = 2;
		tramo1.setNumCarrilesDir2(numDir2);
		assertEquals (numDir2,tramo1.getNumCarrilesDir2());
	}

	/**
	 * Método de prueba para setTipo(String)
	 * Se comprueba que el valor de tipo se modifica correctamente
	 */
	public void testSetTipo() {
		int tipo = 5;
		tramo1.setTipo(tipo);
		assertEquals(tipo,tramo1.getTipo());
	}

	/**
	 * Método de prueba para getTipo()
	 * Se comprueba que el valor devuelto de tipo es correcto
	 */
	public void testGetTipo() {
		int tipo = 5;
		tramo1.setTipo(tipo);
		assertEquals(tipo,tramo1.getTipo());
	}

	/**
	 * Método de prueba para getNodoInicial()
	 * Se comprueba que el nodo devuelto tiene los valores correctos
	 */
	public void testGetNodoInicial() {
		Nodo nodoIni = tramo1.getNodoInicial();
		assertEquals(nodo1,nodoIni);
	}

	/**
	 * Método de prueba para getNodoFinal()
	 * Se comprueba que el nodo devuelto tiene los valores correctos
	 */
	public void testGetNodoFinal() {
		Nodo nodoFin = tramo1.getNodoFinal();
		assertEquals(nodo2,nodoFin);
	}

}
