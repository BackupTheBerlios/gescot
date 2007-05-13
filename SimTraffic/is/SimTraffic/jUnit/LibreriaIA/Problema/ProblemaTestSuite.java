/**
 * 
 */
package is.SimTraffic.jUnit.LibreriaIA.Problema;

import is.SimTraffic.jUnit.LibreriaIA.Problema.DistanciaNodos.DistanciaNodosTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Vista
 *
 */
public class ProblemaTestSuite {
	
	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
		suite.addTest(DistanciaNodosTestSuite.suite());
		
	    //Añadimos las clases del paquete
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
