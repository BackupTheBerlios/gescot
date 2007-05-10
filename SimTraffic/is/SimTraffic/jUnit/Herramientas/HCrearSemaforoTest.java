package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCrearSemaforo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

public class HCrearSemaforoTest extends TestCase {

	Nodo nodo;
	Modelo modelo;
	HCrearSemaforo h;
	
	protected void setUp() throws Exception {
		super.setUp();
		//Creamos un modelo con un nodo para las pruebas
		nodo = new Nodo(new Posicion(90, 60));
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo);
		h = new HCrearSemaforo(nodo);
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCrearSemaforo.hacer(IModelo)'
	 */
	public void testHacer() {
		//antes de llamar a hacer() el nodo no tiene ningun semaforo
		assertNull(nodo.getSeñal());
		//despues de llamar a hacer() el nodo tiene un semaforo
		h.hacer(modelo);
		assertNotNull(nodo.getSeñal());
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCrearSemaforo.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		//despues de llamar a hacer() el nodo tiene un semaforo
		h.hacer(modelo);
		assertNotNull(nodo.getSeñal());
		//despues de llamar a deshacer() el nodo no tiene ningun semaforo
		h.deshacer(modelo);
		assertNull(nodo.getSeñal());
	}

}
