/**
 * 
 */
package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.jUnit.Herramientas.CargarMapa.CargarMapaTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * Clase de prueba que llama a todos los tests del paquete Herramientas
 * 
 */
public class HerramientasTestSuite {
	
	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    suite.addTest(CargarMapaTestSuite.suite());
	    
	    //Añadimos las clases del paquete
	    suite.addTestSuite(HAñadirLineaAutobusTest.class);
	    suite.addTestSuite(HAñadirNodoTest.class);
	    suite.addTestSuite(HAñadirTramoTest.class);
	    suite.addTestSuite(HAsignarTramosAViaTest.class);
	    suite.addTestSuite(HBuscarElementoTest.class);
	    //suite.addTestSuite(HCargarMapaTest.class);
	    suite.addTestSuite(HComenzarTest.class);
	    suite.addTestSuite(HConfigurarEntradaSalidaTest.class);
	    suite.addTestSuite(HCopiarTest.class);
	    suite.addTestSuite(HCortarTest.class);
	    suite.addTestSuite(HCreaItinerarioEntreDosNodosTest.class);
	    suite.addTestSuite(HCrearNuevoMapaTest.class);
	    suite.addTestSuite(HCrearSemaforoTest.class);
	    suite.addTestSuite(HCrearViaTest.class);
	    //suite.addTestSuite(HDescargarMapaTest.class);
	    //suite.addTestSuite(HDetenerTest.class);
	    //suite.addTestSuite(HEliminarNodoTest.class);
	    //suite.addTestSuite(HEliminarSeleccionTest.class);
	    //suite.addTestSuite(HEliminarTramoTest.class);
	    //suite.addTestSuite(HGuardarMapaTest.class);
	    //suite.addTestSuite(HModificarNodoTest.class);
	    //suite.addTestSuite(HModificarParamSimulacionTest.class);
	    suite.addTestSuite(HModificarSeñalTest.class);
	    //suite.addTestSuite(HModificarTramoTest.class);
	    //suite.addTestSuite(HMoverTest.class);
	    //suite.addTestSuite(HPartirTramoTest.class);
	    //suite.addTestSuite(HPausarTest.class);
	    //suite.addTestSuite(HPegarTest.class);
	    
	    return suite;
	}
}
