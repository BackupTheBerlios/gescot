package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Controlador;
import is.SimTraffic.IControlador;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Herramientas.HCortar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class HCargarMapaTest extends TestCase {
	
	/**
	 * Atributo que es un controlador y necesario para las pruebas
	 */
	
	private IControlador controlador;
	
	/**
	 * Atributo que es un panelMapa
	 */
	private PanelMapa panel;
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
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Nodo nodo3 = new Nodo(new Posicion(200,250));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		Tramo tramo2 = new Tramo(nodo1,nodo2);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(nodo3);
		modelo.getMapa().insertar(tramo1);
		modelo.getMapa().insertar(tramo2);
		controlador= new Controlador();
		panel = new PanelMapa(200, 200);
		herramienta=new HCargarMapa(controlador,panel);
	}
	
	public void testHCopiar() {
		assertEquals(controlador,((HCargarMapa)herramienta).getcontrolador());
		assertEquals(panel,((HCargarMapa)herramienta).getpanel());
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de HCargarMapa no ha hecho correctamente");
	}
	/**
	 * Mwtodo que compreuaba que sel metodo deshacer funciona correctamente 
	 */
	public void testdeshacer()
	{
		if (herramienta.deshacer(modelo)!=0)
			fail("La herramienta de Copiar no ha desecho correctamente");
	}
	
	/**
	 * Metodo que prueba el metodo cargarSeñales
	 */
	public void testcargarSeñales()
	{
		
	}

	
}
