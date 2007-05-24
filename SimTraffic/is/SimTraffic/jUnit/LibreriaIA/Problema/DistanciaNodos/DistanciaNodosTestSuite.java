/**
 * 
 */
package is.SimTraffic.jUnit.LibreriaIA.Problema.DistanciaNodos;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete DistanciaNodos
 * 
 */
public class DistanciaNodosTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
	    
	    //A�adimos las suites de los subpaquetes
		
	    //A�adimos las clases del paquete
		suite.addTestSuite(EstadoDistanciaNodosTest.class);
		//suite.addTestSuite(ExploraNodoTest.class);
		suite.addTestSuite(HeuristicaDistanciaLineaRectaTest.class);
		//suite.addTestSuite(PrincipalDistanciaNodosTest.class);
		return suite;
	}
}
