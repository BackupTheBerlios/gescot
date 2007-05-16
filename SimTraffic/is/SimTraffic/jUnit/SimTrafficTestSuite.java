/**
 * 
 */
package is.SimTraffic.jUnit;

import junit.framework.Test;
import junit.framework.TestSuite;
import is.SimTraffic.jUnit.LibreriaIA.LibreriaIATestSuite;
import is.SimTraffic.jUnit.Mapa.*;
import is.SimTraffic.jUnit.Simulacion.SimulacionTestSuite;
import is.SimTraffic.jUnit.Utils.UtilsTestSuite;
import is.SimTraffic.jUnit.Vista.VistaTestSuite;
import is.SimTraffic.jUnit.Herramientas.*;

/**
 * Clase de test de todo el proyecto
 * 
 * @author Grupo ISTrafico
 * 
 */
public class SimTrafficTestSuite {

	/**
	 * M�todo que a�ade los suite de las clases de prueba de cada paquete de la
	 * aplicaci�n, y ejecuta todas las pruebas
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		//A�adimos las suites de los subpaquetes
		suite.addTest(HerramientasTestSuite.suite());
		suite.addTest(LibreriaIATestSuite.suite());
		suite.addTest(MapaTestSuite.suite());
		suite.addTest(SimulacionTestSuite.suite());
		suite.addTest(UtilsTestSuite.suite());
		suite.addTest(VistaTestSuite.suite());
		
		//A�adimos las clases del paquete
		suite.addTestSuite(ControladorTest.class);
		suite.addTestSuite(ModeloTest.class);
		//suite.addTestSuite(PrincipalTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
