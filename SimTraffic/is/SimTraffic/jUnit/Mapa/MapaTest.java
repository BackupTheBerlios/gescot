package is.SimTraffic.jUnit.Mapa;

import java.util.List;
import java.util.ArrayList;
import is.SimTraffic.Mapa.*;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase Mapa
 */

public class MapaTest extends TestCase {
	
	/**
	 * Atributo mapa de prueba
	 */
	private Mapa mapa;
	/**
	 * Atributo nodo de prueba
	 */
	private Nodo nodo;
	

	public MapaTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mapa = new Mapa();
		nodo = new Nodo(new Posicion(100,100));
	}

	/**
	 * M�todo de prueba para la constructora de Mapa
	 * Comprueba si se han creado correctamente las listas vac�as de Nodo,
	 * Tramo y Se�al
	 */
	public void testMapa() {
		assertTrue(mapa.getNodos().isEmpty());
		assertTrue(mapa.getTramos().isEmpty());
		assertTrue(mapa.getSe�ales().isEmpty());		
	}

	/**
	 * M�todo de prueba para modificar
	 */
	public void testModificar() {

	}

	/**
	 * M�todo de prueba para insertarNodo(Nodo)
	 * Comprueba que se insertan varios nodos correctamente
	 */
	public void testInsertarNodo() {
		mapa.insertar(nodo);
		List listaNodos = mapa.getNodos(); 
		assertEquals(nodo,listaNodos.get(listaNodos.size()-1));
		for(int i = 0; i<100; i++){
			double lat = Math.random() * 180 -90;
			double lon = Math.random() * 360 -180;
			Nodo nodoi= new Nodo(new Posicion(lat, lon));
			mapa.insertar(nodoi);
		}
		assertEquals(mapa.getNodos().size(),101);
	}

	/**
	 * M�todo de prueba de insertarTramo(Tramo)
	 * Comprueba que el tramo se crea correctamente, y que los nodos inicial
	 * y final son los correctos
	 */
	public void testInsertarTramo() {
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Nodo nodo3 = new Nodo (new Posicion(300,300));
		Nodo nodo4 = new Nodo (new Posicion(400,400));
		Tramo tramo1 = new Tramo (nodo,nodo2);
		Tramo tramo2 = new Tramo (nodo3,nodo4);
		mapa.insertar(tramo1);
		/*si insertamos directamente el tramo, sin que est�n los nodos insertados,
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

	/**
	 * M�todo que comprueba insertar(Se�al,Nodo)
	 * Comprueba que se inserta correctamente una se�al en un nodo
	 */
	public void testInsertarSe�alNodo() {
		Se�al se�al = new Se�al ("Prueba");
		mapa.insertar(nodo);
		mapa.insertar(se�al, nodo);
		assertTrue(mapa.getSe�ales().contains(se�al));
		assertEquals(se�al,mapa.getNodos().get(0).getSe�al());
	}

	/**
	 * M�todo que comprueba eliminar(Nodo)
	 * Comprueba que se elimina un nodo correctamente
	 */
	public void testEliminarNodo() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		Tramo tramo1 = new Tramo(nodo,nodo2);
		mapa.insertar(tramo1);
		Nodo nodo3 = new Nodo(new Posicion(300,300));
		mapa.insertar(nodo3);
		Nodo nodo4 = new Nodo(new Posicion(400,400));
		//el nodo4 no se inserta en el mapa
		boolean eliminadoNodo = mapa.eliminar(nodo);
		assertFalse(eliminadoNodo);
		boolean eliminadoNodo3 = mapa.eliminar(nodo3);
		assertTrue(eliminadoNodo3);
		boolean eliminadoNodo4 = mapa.eliminar(nodo4);
		assertTrue(eliminadoNodo4);
	}

	/**
	 * M�todo de prueba para eliminar(Tramo)
	 * Comprueba que se elimina el tramo del mapa, y las referencias que
	 * conten�an los nodos inicial y final
	 */
	public void testEliminarTramo() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		Tramo tramo1 = new Tramo(nodo,nodo2);
		mapa.insertar(tramo1);
		mapa.eliminar(tramo1);
		assertTrue(tramo1.getNodoInicial().getTramos().isEmpty());
		assertTrue(tramo1.getNodoFinal().getTramos().isEmpty());
		assertTrue(nodo.getTramos().isEmpty());
		assertTrue(nodo2.getTramos().isEmpty());
		
	}

	/**
	 * M�todo de prueba para getNodos()
	 * Comprueba que la lista de Nodos devuelta es correcta
	 */
	public void testGetNodos() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		List<Nodo> listaEsperada = new ArrayList<Nodo>();
		listaEsperada.add(nodo);
		listaEsperada.add(nodo2);		
		List<Nodo> listaDevuelta = mapa.getNodos();
		assertEquals(nodo,listaDevuelta.get(0));
		assertEquals(nodo2,listaDevuelta.get(1));
		assertEquals(listaEsperada,listaDevuelta);
		
	}

	/**
	 * M�todo de prueba para getSe�ales()
	 * Comprueba que la lista de Se�ales devuelta es correcta
	 */
	public void testGetSe�ales() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		Se�al se�al = new Se�al ("Prueba");
		Se�al se�al2 = new Se�al ("Prueba2");
		mapa.insertar(se�al, nodo);
		mapa.insertar(se�al2, nodo2);
		List<Se�al> listaEsperada = new ArrayList<Se�al>();
		listaEsperada.add(se�al);
		listaEsperada.add(se�al2);
		List<Se�al> listaDevuelta = mapa.getSe�ales();
		assertEquals(listaEsperada,listaDevuelta);
		

	}

	/**
	 * M�todo de prueba para getTramos()
	 * Comprueba que la lista de Tramos devuelta es correcta
	 */
	public void testGetTramos() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		Nodo nodo3 = new Nodo(new Posicion(300,300));
		mapa.insertar(nodo3);
		Tramo tramo1 = new Tramo(nodo,nodo2);
		Tramo tramo2 = new Tramo(nodo2,nodo3);
		mapa.insertar(tramo1);
		mapa.insertar(tramo2);
		List<Tramo> listaEsperada = new ArrayList<Tramo>();
		listaEsperada.add(tramo1);
		listaEsperada.add(tramo2);
		List<Tramo> listaDevuelta = mapa.getTramos();
		assertEquals(listaEsperada,listaDevuelta);
				
	}

}
