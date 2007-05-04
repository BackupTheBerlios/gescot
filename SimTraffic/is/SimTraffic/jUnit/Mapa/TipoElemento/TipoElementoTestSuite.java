package is.SimTraffic.jUnit.Mapa.TipoElemento;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete TipoElemento
 * 
 */
public class TipoElementoTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(TipoNodoAmenityTest.class);
	    suite.addTestSuite(TipoNodoHigwayTest.class);
	    suite.addTestSuite(TipoNodoLeisureTest.class);
	    suite.addTestSuite(TipoNodoManMadeTest.class);
	    suite.addTestSuite(TipoviaHighwayTest.class);
	    return suite;
	}

}
