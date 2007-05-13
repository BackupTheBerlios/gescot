package is.SimTraffic.jUnit.Herramientas;

import java.util.LinkedList;
import java.util.List;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class HEliminarSeleccionTest extends TestCase {
	
	/**
	 * Atributo List<Nodo> que sera la lista de nodos a eliminar
	 */
	private List<Nodo> nodos;
	/**
	 * Atributo List<Tramo> que sera la lista de tramos a eliminar
	 */
	private List<Tramo> tramos;
	
	/**
	 * Atributo HAñadirNodo para hacer pruebas
	 */
	private HEliminarSeleccion herramienta;
	
	/**
	 * Atributo Modelo para hacer pruebas
	 */
	private Modelo modelo;
	
	/**
	 * Constructor 
	 * @param arg0
	 */
	public HEliminarSeleccionTest(String arg0) {
		super(arg0);
	}

	/**
	 * Método de setUp de los atributos de prueba
	 * creamos un nodo y lo añadimos
	 */
	
	protected void setUp() throws Exception {
		super.setUp();
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Nodo nodo3 = new Nodo(new Posicion(200,250));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		Tramo tramo2 = new Tramo(nodo1,nodo2);
		nodos = new LinkedList<Nodo> ();
		nodos.add(nodo1);
		nodos.add(nodo2);
		nodos.add(nodo3);
		tramos = new LinkedList<Tramo> ();
		tramos.add(tramo1);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(nodo3);
		modelo.getMapa().insertar(tramo1);
		modelo.getMapa().insertar(tramo2);
		herramienta=new HEliminarSeleccion(nodos,tramos);
	}
	
	/**
	 * Método que comprueba la constructora HEliminarSeleccion(nodos,tramos)
	 * Se comprueba que la lista de nodos y tramos son correctas
	 */
	
	public void testHEliminarSeleccion() {
		assertEquals(nodos,herramienta.getNodos());
		assertEquals(tramos,herramienta.getTramos());
	}

	/**
	 * Método que comprueba hacer ()
	 * Se comprueba que se han eliminado correctamente
	 */
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
		assertTrue(modelo.getMapa().getNodos().isEmpty());
		assertTrue(modelo.getMapa().getTramos().isEmpty());
	}
	
	/**
	 * Método que comprueba deshacer()
	 * Se comprueba que siguen estando een el mapa despues de hacer y deshacer
	 *
	 */
	public void testDeshacer() {		
		herramienta.hacer(modelo);
		int resultado = herramienta.deshacer(modelo);
		assertEquals(0,resultado);
		assertFalse(modelo.getMapa().getNodos().isEmpty());
		assertFalse(modelo.getMapa().getTramos().isEmpty());
	}
	
}