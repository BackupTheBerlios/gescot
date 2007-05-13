package is.SimTraffic.jUnit.Mapa.TipoElemento;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete TipoElemento
 * 
 */
public class TipoElementoTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //A�adimos las suites de los subpaquetes
	    
	    //A�adimos las clases del paquete
	    //suite.addTestSuite(TipoElementoTest.class);
	    suite.addTestSuite(TipoNodoAmenityTest.class);
	    suite.addTestSuite(TipoNodoHigwayTest.class);
	    suite.addTestSuite(TipoNodoLeisureTest.class);
	    suite.addTestSuite(TipoNodoManMadeTest.class);
	    suite.addTestSuite(TipoViaHighwayTest.class);
	    
	    return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
