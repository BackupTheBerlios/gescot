package is.SimTraffic.jUnit.Herramientas;


import java.io.File;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.ExtFilter;
import is.SimTraffic.Herramientas.HAñadirNodo;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

/**
 * Clase que comprueba el funcionamiento de ExtFilter
 */
public class ExtFilterTest extends TestCase {
	
	/**
	 * Atributo que es la clase que se va a probar
	 */
	ExtFilter filtro;

	/**
	 * Metodo de creacion de ExtFilter para las pruebas
	 */
	protected void setUp() throws Exception {
		filtro = new ExtFilter(Messages.getString("ExtFilterTest.0")); //$NON-NLS-1$
	}
	
	/**
	 * Metodo que comprueba que se crea correctamente
	 */
	public void testExtFilter() {
		String aux=filtro.getExtensions()[0];
		if (!(aux.equalsIgnoreCase(Messages.getString("ExtFilterTest.1")))) //$NON-NLS-1$
		fail(Messages.getString("ExtFilterTest.2")); //$NON-NLS-1$
	}
	
	/**
	 * Metodo que compruaba que funciona correcatemente el metodo accept
	 */
	public void testaccept()
	{
		File f = new File(Messages.getString("ExtFilterTest.3")); //$NON-NLS-1$
		if (!(filtro.accept(f)))
			fail(Messages.getString("ExtFilterTest.4")); //$NON-NLS-1$
	}
}
