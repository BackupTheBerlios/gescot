package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

/**
 * Clase para probar la herramienta Copiar 
 *
 */
public class HCopiarTest extends TestCase {
	
	/**
	 * Atributo que es ista de nodos necesaria para las pruebas
	 */
	private List<Nodo> nodos;
	
	
	/**
	 * Atributo que es ista de tramos necesaria para las pruebas
	 */
	private List<Tramo> tramos;
	
	/**
	 * La herramienta que se va a utilizar
	 */
	private IHerramienta herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;

	/**
	 * Constructor
	 * @param arg0
	 */
	public HCopiarTest(String arg0) {
		super(arg0);
	}
	
	/**
	 * Metodo para inicializar las pruebas
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
		herramienta=new HCopiar(nodos,tramos);
	}
	
	/**
	 * Mwtodo que compreuaba que se crea correctamente
	 */
	public void testHCopiar() {
		assertEquals(nodos,((HCopiar)herramienta).getNodos());
		assertEquals(tramos,((HCopiar)herramienta).getTramos());
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de Copiar no ha hecho correctamente");
	}
	
	/**
	 * Metodo que compreuaba que sel metodo deshacer funciona correctamente -----------------Este metodo falla 
	 */
	public void testdeshacer()
	{
		herramienta.hacer(modelo);
		if (herramienta.deshacer(modelo)!=0)
			fail("La herramienta de Copiar no ha desecho correctamente");
	}
}
