package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Herramientas.HConfigurarEntradaSalida;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import junit.framework.TestCase;

public class HConfigurarEntradaSalidaTest extends TestCase {

	HConfigurarEntradaSalida h;
	Nodo n1, n2, n3;
	EntradaSalida es1, es2, es3;
	int [] a1 = {1, 2, 3};
	int [] a2 = {3, 2, 1};
	int [] a3 = {1, 0, 1};
	
	protected void setUp() throws Exception {
		super.setUp();
		es1 = new EntradaSalida(a1, a2);
		es2 = new EntradaSalida(a1, a3);
		//creamos dos nodos n1 y n2 iguales. Despues cambiamos 
		//los porcentajes y los hacemos iguales a los de n3. Si 
		//despues deshacemos, n1 y n2 tienen que volver a ser iguales.
		n1 = new Nodo(es1, 1, "nodo1", null, null);
		n2 = new Nodo(es1, 1, "nodo1", null, null);
		n3 = new Nodo(es2, 1, "nodo1", null, null);
		h = new HConfigurarEntradaSalida(n1, a1, a3);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HConfigurarEntradaSalida.HConfigurarEntradaSalida(Nodo, int[], int[])'
	 */
	public void testHConfigurarEntradaSalida() {

	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HConfigurarEntradaSalida.hacer(IModelo)'
	 */
	public void testHacer() {
		h.hacer(null);
		assertTrue(n1.getEs().equals(n3.getEs()));
		assertFalse(n1.getEs().equals(n2.getEs()));
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HConfigurarEntradaSalida.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		h.hacer(null);
		h.deshacer(null);
		assertTrue(n1.getEs().equals(n2.getEs()));
		assertFalse(n1.getEs().equals(n3.getEs()));
	}

}
