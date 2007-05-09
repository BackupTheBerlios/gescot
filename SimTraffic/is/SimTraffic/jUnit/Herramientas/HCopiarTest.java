package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

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
	 * La herrameinta que se va a utilizar
	 */
	private IHerramienta herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;

	/**
	 * Metodo para inicializar las pruebas
	 */
	protected void setUp() throws Exception {
		super.setUp();
		int [] a1 = {1, 2, 3};
		int [] a2 = {3, 2, 1};
		int [] a3 = {1, 0, 1};
		EntradaSalida  es1 = new EntradaSalida(a1, a2);
		EntradaSalida  es2 = new EntradaSalida(a1, a3);
		Nodo n1 = new Nodo(es1, 1, "nodo1", new Posicion(50,100), null);
		Nodo n2 = new Nodo(es1, 1, "nodo1", new Posicion(150,150), null);
		Nodo n3 = new Nodo(es2, 1, "nodo1", null, null);
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Nodo nodo3 = new Nodo(new Posicion(200,250));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		Tramo tramo2 = new Tramo(nodo1,nodo2);
		//nodos.add(n1);
		//nodos.add(n2);
		//nodos.add(nodo3);
		tramos.add(tramo1);
		tramos.add(tramo2);
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
	 * Mwtodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de Copiar no ha hecho correctamente");
	}
	
	/**
	 * Mwtodo que compreuaba que sel metodo deshacer funciona correctamente
	 */
	public void testdeshacer()
	{
		if (herramienta.deshacer(modelo)!=0)
			fail("La herramienta de Copiar no ha desecho correctamente");
	}
}
