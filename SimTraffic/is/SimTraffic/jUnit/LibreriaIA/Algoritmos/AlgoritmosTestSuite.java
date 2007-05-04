package is.SimTraffic.jUnit.LibreriaIA.Algoritmos;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Algoritmos
 * 
 */
public class AlgoritmosTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		//suite.addTestSuite(AEstrellaTest.class);
		//suite.addTestSuite(CosteUniformeTest.class);
		return suite;
	}
}
