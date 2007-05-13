/**
 * 
 */
package is.SimTraffic.jUnit.Vista.Acciones.PanelNodo;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete PanelNodo
 *
 */
public class PanelNodoTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //A�adimos las suites de los subpaquetes
	    
	    //A�adimos las clases del paquete
	    //suite.addTestSuite(AccionAbrirMatrizDePasoTest.class);
	    //suite.addTestSuite(AccionAceptarTest.class);
	    //suite.addTestSuite(AccionCambiarTiempoTotalSemTest.class);
	    //suite.addTestSuite(AccionCrearSemaforoTest.class);
	    //suite.addTestSuite(AccionEliminarIntervaloTest.class);
	    //suite.addTestSuite(AccionEliminarSemaforoTest.class);
	    //suite.addTestSuite(AccionInsertarIntervaloTest.class);
	    //suite.addTestSuite(AccionModificarIntervaloTest.class);
	    //suite.addTestSuite(AccionSeleccionarTipoTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
