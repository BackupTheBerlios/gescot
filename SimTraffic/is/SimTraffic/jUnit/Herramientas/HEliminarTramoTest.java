package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HA�adirTramo;
import is.SimTraffic.Herramientas.HEliminarTramo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class HEliminarTramoTest extends TestCase {

	/**
	 * Atributo de prueba de HA�adirTramo
	 */
	private HEliminarTramo herramienta;
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;
	/**
	 * Nodo inicial del tramo
	 */
	private Nodo nodoInicial;
	/**
	 * Nodo final del tramo
	 */
	private Nodo nodoFinal;
	/**
	 * Tramo a eliminar
	 */
	private Tramo tramo;
	
	public HEliminarTramoTest(String arg0) {
		super(arg0);
	}

	/**
	 * M�todo setUp de los atributos de prueba
	 * Cramos los nodos y el tramo a eliminar
	 */
	protected void setUp() throws Exception {
		super.setUp();		
		nodoInicial = new Nodo (new Posicion(100,100));
		nodoFinal = new Nodo (new Posicion(200,200));
		tramo = new Tramo(nodoInicial,nodoFinal);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodoInicial);
		modelo.getMapa().insertar(nodoFinal);
		modelo.getMapa().insertar(tramo);
		herramienta= new HEliminarTramo(tramo);
	}

	/**
	 * M�todo de prueba para la constructora HEliminarTramo(tramo)
	 */
	
	public void testHEliminarTramo() {
		assertEquals(tramo,herramienta.getTramo());
	}
	/**
	 * M�todo de prueba para hacer(Modelo)
	 * Se comprueba que el m�todo devuelve 0 y que el tramo se ha borrado
	 */
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
		assertTrue(modelo.getMapa().getTramos().isEmpty());
	}

	/**
	 * M�todo de prueba de deshacer(Modelo)
	 * Despu�s de llamar a hacer, se llama a deshacer. Se comprueba que el
	 * resultado devuelto es 0, y que se ha vuelto a introducir el tramo
	 */
	public void testDeshacer() {
		herramienta.hacer(modelo);
		int resultado = herramienta.deshacer(modelo);
		assertEquals(0,resultado);
		assertFalse(modelo.getMapa().getTramos().isEmpty());		
	}

}
