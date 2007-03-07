/**
 * 
 */
package is.SimTraffic.jUnit;


import junit.framework.Test;
import junit.framework.TestSuite;
import is.SimTraffic.jUnit.Mapa.*;

/**
 * @author usuario_local
 *
 */
public class SimTrafficTestSuite {
	  
	
	public static Test suite() {
		    TestSuite suite = new TestSuite();
		    MapaTestSuite suiteMapa = new MapaTestSuite();		   
		    suite.addTest(MapaTestSuite.suite());		    
		    return suite;
		  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 	//SimTrafficTestSuite SimTrafficTestSuite1 = new SimTrafficTestSuite();
		    junit.textui.TestRunner.run(suite());

	}

}
