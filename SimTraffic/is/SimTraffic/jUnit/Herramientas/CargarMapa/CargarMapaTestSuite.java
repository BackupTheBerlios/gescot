package is.SimTraffic.jUnit.Herramientas.CargarMapa;

import is.SimTraffic.Herramientas.CargarMapa.CargadorMapa;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete CargarMapa
 * 
 */
public class CargarMapaTestSuite {
	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    //suite.addTestSuite(CargadorMapa.class);
	    return suite;
	}
}
