package is.SimTraffic.jUnit.Mapa;

import java.util.List;
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
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Nodo nodo3 = new Nodo (new Posicion(300,300));
		Nodo nodo4 = new Nodo (new Posicion(400,400));
		Tramo tramo1 = new Tramo (nodo,nodo2);
		Tramo tramo2 = new Tramo (nodo3,nodo4);
		mapa.insertar(tramo1);
		/*si insertamos directamente el tramo, sin que estén los nodos insertados,
		  no se debe insertar el tramo*/
		assertTrue (mapa.getTramos().size()==0);
		mapa.insertar(nodo);
		mapa.insertar(nodo2);
		mapa.insertar(tramo1);
		assertEquals (mapa.getTramos().get(0),tramo1);
		mapa.insertar(tramo1);
		//si insertamos el mismo tramo dos veces, no se debe insertar la segunda
		assertTrue (mapa.getTramos().size()==1);
		mapa.insertar(nodo3);
		mapa.insertar(nodo4);
		mapa.insertar(tramo2);
		assertEquals (mapa.getTramos().get(0),tramo1);
		assertEquals (mapa.getTramos().get(1),tramo2);		
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
