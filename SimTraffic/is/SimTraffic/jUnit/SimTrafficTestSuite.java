/**
 * 
 */
package is.SimTraffic.jUnit;


import junit.framework.Test;
import junit.framework.TestSuite;
import is.SimTraffic.jUnit.Mapa.*;

/**
 * @author Grupo ISTrafico
 *
 */
public class SimTrafficTestSuite {
	  
	public static Test suite() {
		    TestSuite suite = new TestSuite();
		    suite.addTest(MapaTestSuite.suite());		    
		    return suite;
		  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		    junit.textui.TestRunner.run(suite());
	}

}
