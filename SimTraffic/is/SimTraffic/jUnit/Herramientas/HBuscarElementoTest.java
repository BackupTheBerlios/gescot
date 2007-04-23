package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HAsignarTramosAVia;
import is.SimTraffic.Herramientas.HAñadirLineaAutobus;
import is.SimTraffic.Herramientas.HBuscarElemento;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoViaHighway;
import is.SimTraffic.Vista.PanelMapa;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HBuscarElementoTest extends TestCase {
	
	HAsignarTramosAVia t;
	Via v;
	
	String nombre;
	
	String eliminar;
	
	Nodo nodo;
	
	Modelo modelo;
	
	PanelMapa panel;
	
	HBuscarElemento herramienta;

	public HBuscarElementoTest(String name) {
		super(name);
	}

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
	
	public void testHBuscarElemento()
	{
		assertEquals(nombre,((HBuscarElemento)herramienta).getNombre());
		assertEquals(eliminar,((HBuscarElemento)herramienta).getElementoACambiar());
		assertEquals(panel,((HBuscarElemento)herramienta).getPanel());	
	}
	
	public void testHacer() {
		if (herramienta.hacer(modelo)==1)
			fail("Deberia encontrar el nodo");
		herramienta=new HBuscarElemento(eliminar,"nodo2",panel);
		if (herramienta.hacer(modelo)!=1)
			fail("No deberia encontrar el nodo");
	}

}
