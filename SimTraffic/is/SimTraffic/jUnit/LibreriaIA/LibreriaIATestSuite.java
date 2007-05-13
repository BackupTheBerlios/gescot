package is.SimTraffic.jUnit.LibreriaIA;

import is.SimTraffic.jUnit.LibreriaIA.Algoritmos.AlgoritmosTestSuite;
import is.SimTraffic.jUnit.LibreriaIA.Problema.ProblemaTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete LibreriaIA
 * 
 */
public class LibreriaIATestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    suite.addTest(AlgoritmosTestSuite.suite());
	    suite.addTest(ProblemaTestSuite.suite());
		
	    //Añadimos las clases del paquete
		suite.addTestSuite(ComparadorNodosCosteTest.class);
		suite.addTestSuite(ComparadorNodosValorHTest.class);
		suite.addTestSuite(ComparadorNodosValorHyCosteTest.class);
		suite.addTestSuite(NodoIATest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
