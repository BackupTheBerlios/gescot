/**
 * 
 */
package is.SimTraffic.jUnit.Vista.Representaciones;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Representaciones
 *
 */
public class RepresentacionesTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(RepresentacionTest.class);
	    //suite.addTestSuite(RepresentacionAvanzadaTest.class);
	    //suite.addTestSuite(RepresentacionSimpleTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
