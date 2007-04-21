package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

/**
 * Clase de testo de la clase EntradaSalida
 * @author Nacho
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
			fail("EntradaSalida deberia ser igual a si mismo");
		if (!es1.equals(es2))
			fail("EntradaSalida con los mismos valores deberian ser iguales");
		EntradaSalida es3= new EntradaSalida(entrada2,entrada2);
		if (es1.equals(es3))
			fail("EntradaSalida tendrian que ser diferentes");	
	
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
		if (!aux.equals(aux2)) System.out.println("HOLA");
		if (!(es1.transformaOSM()).equals(es1.transformaOSM()))
			fail("La traducion OSM  de si mi mismo deberia ser igual");
		if (!(es1.transformaOSM()).equals(es2.transformaOSM()))
			fail("La traducion OSM de los mismos datos deberia ser igual");
		EntradaSalida es3= new EntradaSalida(entrada2,entrada2);
		if ((es1.transformaOSM()).equals(es3.transformaOSM()))
			fail("La traducion OSM de distintos datos deberia ser distinta");
		}
}

