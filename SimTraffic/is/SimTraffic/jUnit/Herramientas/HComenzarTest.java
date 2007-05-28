package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Controlador;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HComenzar;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Ventana;
import junit.framework.TestCase;

public class HComenzarTest extends TestCase{

	Modelo modelo;
	
	HComenzar herramienta;
	
	/**
	 * Constructor 
	 * @param arg0
	 */
	public HComenzarTest(String arg0) {
		super(arg0);
	}
	
	/**
	 * metodo que inicializa los valores
	 */
	protected void setUp() throws Exception {
		super.setUp();
		modelo = new Modelo();
		Controlador controlador = new Controlador();
		Ventana ventana = new Ventana(modelo,controlador);
		herramienta= new HComenzar(ventana, 0, 1, 3);
		Nodo nodo = new Nodo(new Posicion(100,100));
		Nodo nodo2 = new Nodo(new Posicion(125,150));
		Tramo tramo = new Tramo(nodo,nodo2);
		modelo.getMapa().insertar(nodo);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(tramo);
	}
	
	
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
	}
}
