package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

/**
 * Clase que prueba la herramienta EliminarNodo
 */
public class HEliminarNodoTest extends TestCase {
	
	/**
	 * Atributo Nodo para hacer pruebas
	 */
	private Nodo nodo;
	
	/**
	 * Atributo HAñadirNodo para hacer pruebas
	 */
	private HEliminarNodo herramienta;
	
	/**
	 * Atributo Modelo para hacer pruebas
	 */
	private Modelo modelo;
	
	/**
	 * Constructor 
	 * @param arg0
	 */
	public HEliminarNodoTest(String arg0) {
		super(arg0);
	}

	/**
	 * Método de setUp de los atributos de prueba
	 * creamos un nodo y lo añadimos
	 */
	
	protected void setUp() throws Exception {
		super.setUp();
		nodo = new Nodo(new Posicion(100,100));
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo);
		herramienta = new HEliminarNodo(nodo);
	}
	
	/**
	 * Método que comprueba la constructora HEliminarNodo(nodo)
	 * Se comprueba que los valores de posicion y es son correctos
	 */
	
	public void testHEliminarNodo() {
		assertEquals(nodo,herramienta.getNodo());
	}

	/**
	 * Método que comprueba hacer ()
	 * Se comprueba que el nodo se ha insertado correctamente
	 */
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
		assertTrue(modelo.getMapa().getNodos().isEmpty());
	}
	
	/**
	 * Método que comprueba deshacer()
	 * Se comprueba que tras insertar un nodo, al deshacer, se elimina
	 * correctamente 
	 *
	 */
	public void testDeshacer() {		
		herramienta.hacer(modelo);
		int resultado = herramienta.deshacer(modelo);
		assertEquals(0,resultado);
		assertEquals(nodo,modelo.getMapa().getNodos().get(0));
	}
	
}
