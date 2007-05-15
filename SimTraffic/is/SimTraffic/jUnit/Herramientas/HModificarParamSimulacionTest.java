package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HModificarNodo;
import is.SimTraffic.Herramientas.HModificarParamSimulacion;
import is.SimTraffic.Herramientas.HPegar;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import junit.framework.TestCase;

/**
 * Clase que comprueba el funcionamiento correcto de la clase HModificarParamSimulacion
 */
public class HModificarParamSimulacionTest extends TestCase{
	
	ParametrosSimulacion parametros;
	
	ParametrosSimulacion aux;
	
	HModificarParamSimulacion herramienta;
	
	Modelo modelo;

	/**
	 * Constructora
	 * @param name
	 */
	public HModificarParamSimulacionTest(String name) {
		super(name);
	}

	/**
	 * Metodo de inicializacion de los datos
	 */
	protected void setUp() throws Exception {
		super.setUp();
		int nuevos[]={1,2,3};
		int antiguos[]={4,5,6};
		parametros = new ParametrosSimulacion();
		aux = new ParametrosSimulacion();
		parametros.setPorcentajeTipo(nuevos);
		aux.setPorcentajeTipo(antiguos);
		modelo = new Modelo();
		modelo.getSimulacion().modificaParametros(parametros);
		herramienta = new HModificarParamSimulacion(aux);
	}
	
	/**
	 * Metodo que compreuaba que se crea correctamente
	 */
	public void testHModificarParamSimulacion() {
		assertEquals(herramienta.getParametros(),aux);
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
		assertEquals(modelo.getSimulacion().getParam().getPorcentajeTipo()[0],4);
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testdeshacer()
	{
		herramienta.hacer(modelo);
		int resultado = herramienta.deshacer(modelo);
		assertEquals(0,resultado);
		assertEquals(modelo.getSimulacion().getParam().getPorcentajeTipo()[0],1);
	}
}
