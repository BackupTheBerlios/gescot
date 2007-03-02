package is.SimTraffic.jUnit.Mapa;

import java.util.ArrayList;
import java.util.List;
import java.math.*;
import is.SimTraffic.Mapa.*;
import junit.framework.TestCase;

public class MapaTest extends TestCase {
	
	private Mapa mapa;
	private Nodo nodo;
	

	public MapaTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mapa = new Mapa();
		nodo = new Nodo(new Posicion(100,100));
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.Mapa()'
	 */
	public void testMapa() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.modificar()'
	 */
	public void testModificar() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.insertar(Nodo)'
	 */
	public void testInsertarNodo() {
		mapa.insertar(nodo);
		List listaNodos = mapa.getNodos(); 
		assertEquals(nodo,listaNodos.get(listaNodos.size()-1));
		for(int i = 0; i<100; i++){
			int x = (int)(Math.random() * 1000);
			int y = (int)(Math.random() * 1000);
			Nodo nodoi= new Nodo(new Posicion(x,y));
			mapa.insertar(nodoi);
		}
		assertEquals(mapa.getNodos().size(),101);
	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.insertar(Tramo)'
	 */
	public void testInsertarTramo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.insertar(Señal, Nodo)'
	 */
	public void testInsertarSeñalNodo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.eliminar(Nodo)'
	 */
	public void testEliminarNodo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.eliminar(Tramo)'
	 */
	public void testEliminarTramo() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.getNodos()'
	 */
	public void testGetNodos() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.getSeñales()'
	 */
	public void testGetSeñales() {

	}

	/*
	 * Test method for 'is.SimTraffic.Mapa.Mapa.getTramos()'
	 */
	public void testGetTramos() {

	}

}
