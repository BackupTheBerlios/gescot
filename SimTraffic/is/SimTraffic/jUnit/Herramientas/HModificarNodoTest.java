package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HModificarNodo;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import junit.framework.TestCase;

public class HModificarNodoTest extends TestCase {
	
	Nodo n1;
	Posicion pos1, pos2;
	EntradaSalida es1;
	Señal señ1;
	HModificarNodo h;
	
	public HModificarNodoTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		//Creamos un nodo para pasarselo a la herramienta
		es1 = new EntradaSalida();
		pos1 = new Posicion(10, 10);
		n1 = new Nodo(es1, 1, Messages.getString("HModificarNodoTest.0"), pos1, señ1); //$NON-NLS-1$
		pos2 = new Posicion(20, 20);
		//Vamos a cambiar la posicion del nodo y a borrarle la e/s y la señal
		h = new HModificarNodo(n1, null, pos2, false, false);
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HModificarNodo.hacer(IModelo)'
	 */
	public void testHacer() {
		assertEquals(n1.getEs(), señ1);
		assertEquals(n1.getPos(), pos1);
		assertNotNull(n1.getEs());
		//Llamamos a hacer() y vemos que ahora el nodo no es de e/s ni tiene señal
		//y que la posicion ha cambiado
		h.hacer(null);
		assertNull(n1.getSeñal());
		assertEquals(n1.getPos(), pos2);
		assertNull(n1.getEs());
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HModificarNodo.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		//Llamamos a hacer() y despues a deshacer() y vemos que el nodo vuelve a 
		//estar como antes
		h.hacer(null);
		h.deshacer(null);
		assertEquals(n1.getEs(), señ1);
		assertEquals(n1.getPos(), pos1);
		assertNotNull(n1.getEs());
	}

}
