/**
 * 
 */
package is.SimTraffic.jUnit.Vista.PanelesSimulacion;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete PanelesSimulacion
 *
 */
public class PanelesSimulacionTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //A�adimos las suites de los subpaquetes
	    
	    //A�adimos las clases del paquete
	    //suite.addTestSuite(PanelTurismoTest.class);
	    //suite.addTestSuite(PanelVehiculosTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
