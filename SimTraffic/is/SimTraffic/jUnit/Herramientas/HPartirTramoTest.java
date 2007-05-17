package is.SimTraffic.jUnit.Herramientas;


import junit.framework.TestCase;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HPartirTramo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

public class HPartirTramoTest extends TestCase {

	/**
	 * Atrbuto necesario 
	 */
	public Nodo nodo1;
	
	/**
	 * Atrbuto necesario
	 */
	public Nodo nodo2;
	
	/**
	 * Atrbiuto necesario para la prueba que es el nuevo nodo
	 */
	public Nodo nuevoNodo;
	/**
	 * Atrbiuto necesario para la prueba que Tramo antiguo del mapa
	 */
	public Tramo antiguoTramo;
	/**
	 * Atrbiuto necesario para la prueba que Nuevo tramo que corresponde a la primera mitad del antiguo tramo
	 */
	public Tramo tramo1Nuevo;
	/**
	 * Atrbiuto necesario para la prueba que Nuevo tramo que corresponde a la segunda mitad del antiguo tramo
	 */
	public Tramo tramo2Nuevo;
	
	/**
	 * La herramienta que se va a utilizar
	 */
	private HPartirTramo herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;
	
	
	public HPartirTramoTest(String arg0) {
		super(arg0);
	}
	/**
	 * Metodo para inicializar las pruebas
	 */
	protected void setUp() throws Exception {
		super.setUp();
		nuevoNodo = new Nodo(new Posicion(50,100));
		nodo1 = new Nodo(new Posicion(100,100));
		nodo2 = new Nodo(new Posicion(150,150));
		antiguoTramo = new Tramo(1, nodo1, nodo2);
		tramo1Nuevo=new Tramo(1, nodo1, nuevoNodo);
		tramo2Nuevo=new Tramo(1,nuevoNodo,nodo1);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(antiguoTramo);
		herramienta=new HPartirTramo(nuevoNodo, antiguoTramo, tramo1Nuevo, tramo2Nuevo);		
	}
	
	/**
	 * Metodo que comprueba que se crea correctamente
	 */
	public void testHPartirTramo() {
		assertEquals(nuevoNodo,herramienta.getNuevoNodo());
		assertEquals(antiguoTramo,herramienta.getAntiguoTramo());
		assertEquals(tramo1Nuevo,herramienta.getTramo1Nuevo());
		assertEquals(tramo2Nuevo,herramienta.getTramo2Nuevo());
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		System.out.println(modelo.getMapa().getTramos().size());
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de hacer no se ha hecho correctamente");
		System.out.println(modelo.getMapa().getTramos().size());
		assertEquals(3,modelo.getMapa().getNodos().size());
		assertEquals(2,modelo.getMapa().getTramos().size());
	}
	
	/**
	 * Metodo que compreuaba que sel metodo deshacer funciona correctamente 
	 */
	public void testdeshacer()
	{
		herramienta.hacer(modelo);
		if (herramienta.deshacer(modelo)!=0)
			fail(Messages.getString("HPartirTramoTest.1")); //$NON-NLS-1$
			assertEquals(2,modelo.getMapa().getNodos().size());
			assertEquals(1,modelo.getMapa().getTramos().size());
	}
	
}
