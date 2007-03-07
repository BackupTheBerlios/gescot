/**
 * 
 */
package is.SimTraffic.jUnit;


import junit.framework.Test;
import junit.framework.TestSuite;
import is.SimTraffic.jUnit.Mapa.*;
import is.SimTraffic.jUnit.Herramientas.*;

/**
 * Clase de test de todo el proyecto
 * @author Grupo ISTrafico
 *
 */
public class SimTrafficTestSuite {
	  
	/**
	 * Método que añade los suite de las clases de prueba de cada paquete
	 * de la aplicación, y ejecuta todas las pruebas
	 * 
	 */
	public static Test suite() {
		    TestSuite suite = new TestSuite();
		    suite.addTestSuite(ControladorTest.class);
		    suite.addTest(MapaTestSuite.suite());
		    suite.addTest(HerramientasTestSuite.suite());		    
		    return suite;
		  }
	
	public static void main(String[] args) {
		    junit.textui.TestRunner.run(suite());
	}

}
