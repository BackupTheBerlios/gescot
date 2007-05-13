package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HDetener;
import is.SimTraffic.Herramientas.IHerramienta;


import junit.framework.TestCase;

public class HDetenerTest extends TestCase {
	
	/**
	 * La herramienta que se va a utilizar
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
		modelo = new Modelo();
		herramienta=new HDetener();
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
