package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HBuscarElemento;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Vista.PanelMapa;

import junit.framework.TestCase;

/**
 * Clase JUNIT para testear la clase BuscarElemento
 * @author Nacho
 *
 */
public class HBuscarElementoTest extends TestCase {
	
	/**
	 * Atibuto que indica el nombre del nodo
	 */
	String nombre;
	
	/**
	 * Atributo que indica el tipo a eliminar
	 */
	String eliminar;
	
	/**
	 * Atributo Nodo que se añade al modelo
	 */
	Nodo nodo;
	/**
	 * Atributo que es el modelo
	 */
	Modelo modelo;
	/**
	 * Atributo que es un panel necesario para las pruebas
	 */
	PanelMapa panel;
	
	/**
	 * Atributo con la herramienta que es la clase que vamos a probar
	 */
	HBuscarElemento herramienta;


	/**
	 * Constructor de la clase
	 * @param name
	 */
	public HBuscarElementoTest(String name) {
		super(name);
	}

	/**
	 * Método de setUp de los atributos de prueba
	 * Se construyen: un nodo, un modelo, un jpanel y una herramienta
	 * la EntradaSalida
	 */
	protected void setUp() throws Exception {
		super.setUp();
		nodo = new Nodo(0, "nodo1",new Posicion(100,100),new TipoNodoLeisure("Marina"));
		modelo = new Modelo();
		panel = new PanelMapa(100,100);
		modelo.getMapa().insertar(nodo);
		nombre="nodo1";
		eliminar="Nodo";
		herramienta=new HBuscarElemento(eliminar,nombre,panel);
	}
	
	/**
	 * Metodo que comprueba el constructor de la clase HBuscarElemento
	 */
	public void testHBuscarElemento()
	{
		assertEquals(nombre,((HBuscarElemento)herramienta).getNombre());
		assertEquals(eliminar,((HBuscarElemento)herramienta).getElementoACambiar());
		assertEquals(panel,((HBuscarElemento)herramienta).getPanel());	
	}
	/**
	 * Metodo que comprueba el metodo hacer de la clase HBuscarElemento
	 */
	public void testhacer() {
		if (herramienta.hacer(modelo)==1)
			fail("Deberia encontrar el nodo");
		herramienta=new HBuscarElemento(eliminar,"nodo2",panel);
		if (herramienta.hacer(modelo)!=1)
			fail("No deberia encontrar el nodo");
	}
	/**
	 * Metodo que comprueba el metodo deshacer de la clase HBuscarElemento
	 */
	public void testdeshacer() {
		assertEquals(0,herramienta.deshacer(this.modelo));
	}

}
