package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.EntradaSalida;
import junit.framework.TestCase;

/**
 * Clase de testea de la clase EntradaSalida
 *
 */

public class EntradaSalidaTest extends TestCase {
	
	/**
	 * Metodo para probar que el metodo equals esta bien implementado
	 */
	public void testEquals()
	{
		int entrada1 []={10,34,14};
		int entrada2 []={15,30,57};
		EntradaSalida es1= new EntradaSalida(entrada1,entrada2);
		EntradaSalida es2= new EntradaSalida(entrada1,entrada2);
		if (es1 != es1)
			fail(Messages.getString("EntradaSalidaTest.0")); //$NON-NLS-1$
		if (!es1.equals(es2))
			fail(Messages.getString("EntradaSalidaTest.1")); //$NON-NLS-1$
		EntradaSalida es3= new EntradaSalida(entrada2,entrada2);
		if (es1.equals(es3))
			fail(Messages.getString("EntradaSalidaTest.2"));	 //$NON-NLS-1$
	
	}
	/**
	 * Metodo que comprueba que el metodo transformaOSM esta correctamente implementado
	 *
	 */
		public void testtransformaOSM()
		{
		int entrada1 []={10,34,14};
		int entrada2 []={15,30,57};
		EntradaSalida es1= new EntradaSalida(entrada1,entrada2);
		EntradaSalida es2= new EntradaSalida(entrada1,entrada2);
		String aux =es1.transformaOSM();
		String aux2 =es1.transformaOSM();
		//if (!aux.equals(aux2)) System.out.println(Messages.getString("EntradaSalidaTest.3")); //$NON-NLS-1$
		if (!(es1.transformaOSM()).equals(es1.transformaOSM()))
			fail(Messages.getString("EntradaSalidaTest.4")); //$NON-NLS-1$
		if (!(es1.transformaOSM()).equals(es2.transformaOSM()))
			fail(Messages.getString("EntradaSalidaTest.5")); //$NON-NLS-1$
		EntradaSalida es3= new EntradaSalida(entrada2,entrada2);
		if ((es1.transformaOSM()).equals(es3.transformaOSM()))
			fail(Messages.getString("EntradaSalidaTest.6")); //$NON-NLS-1$
		}
}

