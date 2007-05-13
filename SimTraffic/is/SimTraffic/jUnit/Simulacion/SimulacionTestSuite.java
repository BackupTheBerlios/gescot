/**
 * 
 */
package is.SimTraffic.jUnit.Simulacion;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Herramientas
 * 
 */
public class SimulacionTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
	    //Añadimos las suites de los subpaquetes
		
		//Añadimos las clases del paquete
		//suite.addTestSuite(AmbulanciaTest.class);
		//suite.addTestSuite(BusTest.class);
		//suite.addTestSuite(BuscaCaminoTest.class);
		//suite.addTestSuite(CamionTest.class);
		//suite.addTestSuite(ControladorSimTest.class);
		//suite.addTestSuite(GrupoVehiculosTest.class);
		//suite.addTestSuite(InteligenciaTest.class);
		//suite.addTestSuite(MotoTest.class);
		//suite.addTestSuite(ParametrosSimulacionTest.class);
		//suite.addTestSuite(RelojTest.class);
		//suite.addTestSuite(SimulacionTest.class);
		//suite.addTestSuite(TaxiTest.class);
		//suite.addTestSuite(TurismoTest.class);
		//suite.addTestSuite(VehiculoTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
