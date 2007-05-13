package is.SimTraffic.jUnit.Vista.VentanaMatrizPaso;

import junit.framework.Test;
import junit.framework.TestSuite;

public class VentanaMatrizPasoTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
		
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(BotonDeConexionTest.class);
	    //suite.addTestSuite(EscuchaBotonInterconexionTest.class);
	    //suite.addTestSuite(OyenteVentanaMatrizDePasoTest.class);
	    //suite.addTestSuite(VentanaMatrizDePasoTest.class);
		
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
