/**
 * 
 */
package is.SimTraffic.jUnit.Utils;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Herramientas
 * 
 */
public class UtilsTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
		//suite.addTestSuite(CheqeuoInputVentanasTest.class);
		//suite.addTestSuite(PrintUtilitiesTest.class);
		//suite.addTestSuite(TiempoTest.class);
		
		return suite;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
