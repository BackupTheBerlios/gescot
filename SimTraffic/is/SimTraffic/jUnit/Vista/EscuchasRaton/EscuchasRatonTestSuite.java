/**
 * 
 */
package is.SimTraffic.jUnit.Vista.EscuchasRaton;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete EscuchasRaton
 *
 */
public class EscuchasRatonTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(EscuchaAyudaTest.class);
	    //suite.addTestSuite(EscuchaCambioSpinnerTest.class);
	    //suite.addTestSuite(EscuchaRatonTest.class);
	    //suite.addTestSuite(EscuchaTecladoTest.class);
	    //suite.addTestSuite(MLAñadirLineaAutobusTest.class);
	    //suite.addTestSuite(MLAñadirNodoTest.class);
	    //suite.addTestSuite(MLAñadirTramoTest.class);
	    //suite.addTestSuite(MLAñadirViaTest.class);
	    //suite.addTestSuite(MLCopiarTest.class);
	    //suite.addTestSuite(MLDesplazarTest.class);
	    //suite.addTestSuite(MLEliminarNodoTest.class);
	    //suite.addTestSuite(MLEliminarTramoTest.class);
	    //suite.addTestSuite(MLEscuchaItinerarioTest.class);
	    //suite.addTestSuite(MLEscuchaSiempreTest.class);
	    //suite.addTestSuite(MLMapaBDerechoTest.class);
	    //suite.addTestSuite(MLMoverTest.class);
	    //suite.addTestSuite(MLPegarTest.class);
	    //suite.addTestSuite(MLSeleccionarNodoBDerechoTest.class);
	    //suite.addTestSuite(MLSeleccionarElementosTest.class);
	    //suite.addTestSuite(MLSeleccionarImagenTest.class);
	    //suite.addTestSuite(MLSeleccionarNodosTest.class);
	    //suite.addTestSuite(MLSeleccionarTramosTest.class);
	    //suite.addTestSuite(MLSeleccionarViaTest.class);
	    //suite.addTestSuite(MLMLSeleccionarYMoverTest.class);
	    //suite.addTestSuite(MLSeleccionaTramoBDerechoTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
