/**
 * 
 */
package is.SimTraffic.jUnit.Herramientas.DescargarMapa;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase de prueba que llama a todos los tests del paquete CargarMapa
 * 
 */
public class DescargarMapaTestSuite {

	/**
	 * Método suite que añade los tests de todas las clases de prueba
	 * 
	 */
	public static Test suite() {
	    TestSuite suite = new TestSuite();
	    
	    //Añadimos las suites de los subpaquetes
	    
	    //Añadimos las clases del paquete
	    //suite.addTestSuite(Base64Test.class);
	    //suite.addTestSuite(BoundingBoxDownloaderTest.class);
	    //suite.addTestSuite(GBCTest.class);
	    //suite.addTestSuite(IncompleteDownloaderTest.class);
	    //suite.addTestSuite(OsmConnectionTest.class);
	    //suite.addTestSuite(OsmIdReaderTest.class);
	    //suite.addTestSuite(OsmReaderTest.class);
	    //suite.addTestSuite(OsmServerReaderTest.class);
	    //suite.addTestSuite(PleaseWaitDialogTest.class);
	    //suite.addTestSuite(ProgressInputStreamTest.class);
	    //suite.addTestSuite(XmlWriterTest.class);
	    
	    return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}
