/**
 * 
 */
package is.SimTraffic.jUnit.Vista.BarrasHerramientas;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete BarrasHerramientas
 *
 */
public class BarrasHerramientasTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(BarraTest.class);
	    //suite.addTestSuite(BarraCrearNodoTest.class);
	    //suite.addTestSuite(BarraHerramientasTest.class);
	    //suite.addTestSuite(BarraRedimensionarImagenTest.class);
	    //suite.addTestSuite(BarraSeleccionarTest.class);
	    //suite.addTestSuite(BarraSuperiorTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
