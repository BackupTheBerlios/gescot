/**
 * 
 */
package is.SimTraffic.jUnit.Vista.Acciones;

import is.SimTraffic.jUnit.Vista.Acciones.PanelNodo.PanelNodoTestSuite;
import is.SimTraffic.jUnit.Vista.Acciones.PanelTramo.PanelTramoTestSuite;
import is.SimTraffic.jUnit.Vista.Acciones.VentanaMatrizDePaso.VentanaMatrizDePasoTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Acciones
 *
 */
public class AccionesTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    suite.addTest(PanelNodoTestSuite.suite());
	    suite.addTest(PanelTramoTestSuite.suite());
	    suite.addTest(VentanaMatrizDePasoTestSuite.suite());
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(AccionBarraTest.class);
	    //suite.addTestSuite(AccionBuscarTest.class);
	    //suite.addTestSuite(AccionCambiarRepTest.class);
	    //suite.addTestSuite(AccionCargarTest.class);
	    //suite.addTestSuite(AccionCargarImagenTest.class);
	    //suite.addTestSuite(AccionComenzarSimulacionTest.class);
	    //suite.addTestSuite(AccionCopiarTest.class);
	    //suite.addTestSuite(AccionCortarTest.class);
	    //suite.addTestSuite(AccionDescargarTest.class);
	    //suite.addTestSuite(AccionDeshacerTest.class);
	    //suite.addTestSuite(AccionDetenerSimulacionTest.class);
	    //suite.addTestSuite(AccionEliminarNodoTest.class);
	    //suite.addTestSuite(AccionEliminarSeleccionTest.class);
	    //suite.addTestSuite(AccionEliminarTramoTest.class);
	    //suite.addTestSuite(AccionesPopUpMenuTest.class);
	    //suite.addTestSuite(AccionGuardarTest.class);
	    //suite.addTestSuite(AccionMoverNodoTest.class);
	    //suite.addTestSuite(AccionNuevoTest.class);
	    //suite.addTestSuite(AccionPausarSimulacionTest.class);
	    //suite.addTestSuite(AccionPegarTest.class);
	    //suite.addTestSuite(AccionPropiedadesNodoTest.class);
	    //suite.addTestSuite(AccionPropiedadesTramoTest.class);
	    //suite.addTestSuite(AccionScrollXTest.class);
	    //suite.addTestSuite(AccionScrollYTest.class);
	    //suite.addTestSuite(TestAccionSobreMapa.class);
	    //suite.addTestSuite(AccionVerLineaBusTest.class);
	    //suite.addTestSuite(AccionZoomTest.class);
	    //suite.addTestSuite(AuxScrollXTest.class);
	    //suite.addTestSuite(AuxScrollYTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
