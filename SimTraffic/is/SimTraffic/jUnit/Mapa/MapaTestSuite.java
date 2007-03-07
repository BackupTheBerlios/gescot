package is.SimTraffic.jUnit.Mapa;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Mapa
 * 
 */
public class MapaTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(MapaTest.class);
	    suite.addTestSuite(NodoTest.class);
	    suite.addTestSuite(TramoTest.class);
	    suite.addTestSuite(Se�alTest.class);
	    suite.addTestSuite(EntradaSalidaTest.class);	    
	    suite.addTestSuite(ConversorUTMTest.class);
	    return suite;
	}

}