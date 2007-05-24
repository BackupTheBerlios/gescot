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
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
		
	    //Añadimos las clases del paquete
		suite.addTestSuite(EstadoDistanciaNodosTest.class);
		//suite.addTestSuite(ExploraNodoTest.class);
		suite.addTestSuite(HeuristicaDistanciaLineaRectaTest.class);
		//suite.addTestSuite(PrincipalDistanciaNodosTest.class);
		return suite;
	}
}
