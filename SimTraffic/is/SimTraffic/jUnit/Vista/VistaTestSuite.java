/**
 * 
 */
package is.SimTraffic.jUnit.Vista;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Vista
 *
 */
public class VistaTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    //suite.addTest(AccionesTestSuite.suite());
	    //suite.addTest(BarrasHerramientasTestSuite.suite());
	    //suite.addTest(EscuchasRatonTestSuite.suite());
	    //suite.addTest(PanelesSimulacionTestSuite.suite());
	    //suite.addTest(RepresentacionesTestSuite.suite());
	    //suite.addTest(SugerenciasTestSuite.suite());
	    //suite.addTest(VentanaMatrizPasoTestSuite.suite());
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(AcercaDeTest.class);
	    //suite.addTestSuite(AyudaTest.class);
	    //suite.addTestSuite(BarraCrearTramoTest.class);
	    //suite.addTestSuite(LogTest.class);
	    //suite.addTestSuite(PanelBuscarTest.class);
	    //suite.addTestSuite(PanelDescargarTest.class);
	    //suite.addTestSuite(PanelEsperaCargandoTest.class);
	    //suite.addTestSuite(PanelMapaTest.class);
	    //suite.addTestSuite(PanelNodoTest.class);
	    //suite.addTestSuite(PanelTramoTest.class);
	    //suite.addTestSuite(VentanaTest.class);
	    //suite.addTestSuite(VistaTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
