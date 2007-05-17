/**
 * 
 */
package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.jUnit.Herramientas.CargarMapa.CargarMapaTestSuite;
import is.SimTraffic.jUnit.Mapa.Se�ales.Se�alesTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete Mapa
 * 
 */
public class MapaTestSuite {

	/**
	 * M�todo suite que a�ade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //A�adimos las suites de los subpaquetes
	    suite.addTestSuite(Se�alesTestSuite.class);
	    suite.addTestSuite(CargarMapaTestSuite.class);
	    
	    //A�adimos las clases del paquete
	    suite.addTestSuite(ConversorUTMTest.class);
	    suite.addTestSuite(EntradaSalidaTest.class);
	    //suite.addTestSuite(LineaBusTest.class);
	    suite.addTestSuite(MapaTest.class);
	    suite.addTestSuite(NodoTest.class);
	    suite.addTestSuite(PosicionTest.class);
	    suite.addTestSuite(RepresentacionTest.class);
	    suite.addTestSuite(SeleccionTest.class);
	    suite.addTestSuite(Se�alTest.class);	    
	    suite.addTestSuite(TramoTest.class);
	    suite.addTestSuite(ViaTest.class);
	    
	    return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}