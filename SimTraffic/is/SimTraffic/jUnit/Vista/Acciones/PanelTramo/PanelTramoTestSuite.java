/**
 * 
 */
package is.SimTraffic.jUnit.Vista.Acciones.PanelTramo;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete PanelTramo
 *
 */
public class PanelTramoTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(AccionAceptarTramoTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
