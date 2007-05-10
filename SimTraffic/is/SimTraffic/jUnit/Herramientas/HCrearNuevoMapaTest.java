package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCrearNuevoMapa;
import is.SimTraffic.Mapa.Mapa;
import junit.framework.TestCase;

public class HCrearNuevoMapaTest extends TestCase {

	HCrearNuevoMapa h;
	Modelo modelo;
	
	protected void setUp() throws Exception {
		super.setUp();
		//Creamos un modelo con el mapa
		modelo = new Modelo();
		h = new HCrearNuevoMapa();
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCrearNuevoMapa.hacer(IModelo)'
	 */
	public void testHacer() {
		Mapa mapa = modelo.getMapa();
		//Al llamar a hacer() se genera un mapa nuevo
		h.hacer(modelo);
		assertFalse(modelo.getMapa() == mapa);
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HCrearNuevoMapa.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		//En esta clase, deshacer no hace nada
	}

}
