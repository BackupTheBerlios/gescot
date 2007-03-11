package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

public class PosicionTest extends TestCase {
	public void testConversion() {
		Posicion prueba = new Posicion(30.0, 45.0);
		System.out.println("" + prueba.getPosX() +" "+ prueba.getPosY());
		assertFalse("mala conversion x", (prueba.getPosX() < 499999 || prueba
				.getPosX() > 500001));

		assertFalse("mala conversion y", (prueba.getPosY() < 3318784 || prueba
				.getPosY() > 3318786));

	}
}
