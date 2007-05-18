package is.SimTraffic.jUnit.Herramientas;

import java.util.LinkedList;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HComenzar;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Herramientas.HPausar;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

/**
 * Clase de prueba para HPausar
 */
public class HPausarTest extends TestCase{
	
	/**
	 * La herramienta que se va a utilizar
	 */
	private HPausar herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;
	
	/**
	 * 
	 * @param arg0
	 */
	public HPausarTest(String arg0) {
		super(arg0);
	}

	/**
	 * Metodo para inicializar las pruebas
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Nodo nodo1 = new Nodo(new Posicion(100, 100));
		Nodo nodo2 = new Nodo(new Posicion(150, 150));
		Tramo tramo = new Tramo(1, nodo1, nodo2);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(tramo);
		herramienta=new HPausar();		
	}

	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		HComenzar aux = new HComenzar(0,10,5);
		aux.hacer(modelo);
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de hacer no se ha hecho correctamente");
	}
	
	/**
	 * Metodo que compreuaba que sel metodo deshacer funciona correctamente 
	 */
	public void testdeshacer()
	{
		HComenzar aux = new HComenzar(0,10,5);
		aux.hacer(modelo);
		herramienta.hacer(modelo);
		if (herramienta.deshacer(modelo)!=0)
			fail(Messages.getString("HPausarTest.1")); //$NON-NLS-1$
	}
}
