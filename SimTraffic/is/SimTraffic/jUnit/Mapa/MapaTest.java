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
	 * Método de prueba para la constructora de Mapa
	 * Comprueba si se han creado correctamente las listas vacías de Nodo,
	 * Tramo y Señal
	 */
	public void testMapa() {
		assertTrue(mapa.getNodos().isEmpty());
		assertTrue(mapa.getTramos().isEmpty());
		assertTrue(mapa.getSeñales().isEmpty());		
	}

	/**
	 * Método de prueba para modificar
	 */
	public void testModificar() {

	}

	/**
	 * Método de prueba para insertarNodo(Nodo)
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
	 * Método de prueba de insertarTramo(Tramo)
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

	/**
	 * Método que comprueba insertar(Señal,Nodo)
	 * Comprueba que se inserta correctamente una señal en un nodo
	 */
	public void testInsertarSeñalNodo() {
		Señal señal = new Señal ("Prueba");
		mapa.insertar(nodo);
		mapa.insertar(señal, nodo);
		assertTrue(mapa.getSeñales().contains(señal));
		assertEquals(señal,mapa.getNodos().get(0).getSeñal());
	}

	/**
	 * Método que comprueba eliminar(Nodo)
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
	 * Método de prueba para eliminar(Tramo)
	 * Comprueba que se elimina el tramo del mapa, y las referencias que
	 * contenían los nodos inicial y final
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
	 * Método de prueba para getNodos()
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
	 * Método de prueba para getSeñales()
	 * Comprueba que la lista de Señales devuelta es correcta
	 */
	public void testGetSeñales() {
		mapa.insertar(nodo);
		Nodo nodo2 = new Nodo(new Posicion(200,200));
		mapa.insertar(nodo2);
		Señal señal = new Señal ("Prueba");
		Señal señal2 = new Señal ("Prueba2");
		mapa.insertar(señal, nodo);
		mapa.insertar(señal2, nodo2);
		List<Señal> listaEsperada = new ArrayList<Señal>();
		listaEsperada.add(señal);
		listaEsperada.add(señal2);
		List<Señal> listaDevuelta = mapa.getSeñales();
		assertEquals(listaEsperada,listaDevuelta);
		

	}

	/**
	 * Método de prueba para getTramos()
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
