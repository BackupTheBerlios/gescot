package is.SimTraffic.jUnit.Herramientas;

import java.util.LinkedList;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class HGuardarMapaTest extends TestCase{
	
	/**
	 * Atributo HGuardarMapa para hacer pruebas
	 */
	private HGuardarMapa herramienta;
	
	/**
	 * Atributo Modelo para hacer pruebas
	 */
	private Modelo modelo;

	/**
	 * Constructor 
	 * @param arg0
	 */
	public HGuardarMapaTest(String arg0) {
		super(arg0);
	}

	/**
	 * Método de setUp de los atributos de prueba
	 */
	
	protected void setUp() throws Exception {
		super.setUp();
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(tramo1);
		herramienta=new HGuardarMapa();
	}
	
	/**
	 * Método que comprueba hacer ()
	 * Se comprueba que se han eliminado correctamente
	 */
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
	}
	
	/**
	 * Método de prueba de deshacer(Modelo)
	 * El metodo no hace nada
	 */
	public void testDeshacer() {
	
	}
}
