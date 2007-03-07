package is.SimTraffic.jUnit;

import junit.framework.TestCase;
import is.SimTraffic.*;
import is.SimTraffic.Herramientas.*;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Posicion;

/**
 * Clase de prueba para Controlador
 *
 */
public class ControladorTest extends TestCase {

	private Controlador controlador;
	private HAñadirNodo herramienta;
	
	public ControladorTest(String arg0) {
		super(arg0);
	}
	/**
	 * Método de setUp
	 * Se crea un controlador de prueba con todos sus elementos 
	 * necesarios: modelo y herramienta
	 */
	protected void setUp() throws Exception {
		super.setUp();
		controlador= new Controlador();
		Posicion pos= new Posicion(100,100);
		int cut=5;
		int [] franjas=new int[3];
		franjas[0]=20;
		franjas[1]=30;
		franjas[2]=50;
		EntradaSalida es = new EntradaSalida(cut,franjas);
		herramienta = new HAñadirNodo(pos,es);
		controlador.modelo = new Modelo();		
	}
	
	/**
	 * Método de prueba para herramienta(IHerramienta)
	 * Se comprueba que tras llamar a herramienta(), se ha añadido
	 * la herramienta a la lista de herramientas del controlador
	 */

	public void testHerramienta() {
		controlador.herramienta(herramienta);
		assertEquals(herramienta,controlador.herramientas.get(0));
		
	}

	/**
	 * Método de prueba para  deshacer
	 * Se comprueba que tras llamar al método herramienta y a deshacer,
	 * se devuelve el resultado 0, y el atributo herramientas se queda
	 * vacio
	 */	
	public void testDeshacer() {
		controlador.herramienta(herramienta);
		int resultado = controlador.deshacer();
		assertEquals(0,resultado);
		assertTrue(controlador.herramientas.isEmpty());
	}
}
