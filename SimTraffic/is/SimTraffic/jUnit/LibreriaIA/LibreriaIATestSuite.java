package is.SimTraffic.jUnit.LibreriaIA;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete LibreriaIA
 * 
 */
public class LibreriaIATestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ComparadorNodosCosteTest.class);
		suite.addTestSuite(ComparadorNodosValorHTest.class);
		suite.addTestSuite(ComparadorNodosValorHyCosteTest.class);
		return suite;
	}
}
