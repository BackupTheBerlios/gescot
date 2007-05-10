package is.SimTraffic.jUnit.Herramientas;


import java.io.File;

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
		filtro = new ExtFilter("osm");
	}
	
	/**
	 * Metodo que comprueba que se crea correctamente
	 */
	public void testExtFilter() {
		String aux=filtro.getExtensions()[0];
		if (!(aux.equalsIgnoreCase("osm")))
		fail("No se ha creado correctamente la calse ExtFilter");
	}
	
	/**
	 * Metodo que compruaba que funciona correcatemente el metodo accept
	 */
	public void testaccept()
	{
		File f = new File("madrid_centro.osm");
		if (!(filtro.accept(f)))
			fail("ExtFilter no lee el archivo correctamente");
	}
}
