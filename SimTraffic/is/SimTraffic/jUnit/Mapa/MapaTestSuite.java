package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.jUnit.Herramientas.CargarMapa.CargarMapaTestSuite;
import is.SimTraffic.jUnit.Mapa.Señales.SeñalesTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Mapa
 * 
 */
public class MapaTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    suite.addTestSuite(SeñalesTestSuite.class);
	    suite.addTestSuite(CargarMapaTestSuite.class);
	    suite.addTestSuite(MapaTest.class);
	    suite.addTestSuite(NodoTest.class);
	    suite.addTestSuite(TramoTest.class);
	    suite.addTestSuite(SeñalTest.class);	    
	    suite.addTestSuite(ConversorUTMTest.class);
	    suite.addTestSuite(PosicionTest.class);
	    suite.addTestSuite(SeleccionTest.class);
	    suite.addTestSuite(ViaTest.class);
	    return suite;
	}

}