package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Mapa.*;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase Nodo
 */

public class NodoTest extends TestCase {
	
	/**
	 * Test method for 'is.SimTraffic.Mapa.Nodo.hashCode()'
	 * Se comprueba que el hash coincide en dos nodos iguales, y que 
	 * dos nodos distintos no tengan el mismo hashCode
	 */
	public void testHashCode() {
		Posicion pos = new Posicion(1.0f,1.0f);
		Nodo nodoprueba = new Nodo(pos);
		Nodo nodoprueba2 = new Nodo(pos);
		if (nodoprueba.hashCode() != nodoprueba.hashCode())
			fail("Hash no debe cambiar");
		if (nodoprueba.hashCode() != nodoprueba2.hashCode())
			fail("Nodos iguales deben tener igual hashCode");
		Nodo nodoprueba3 = new Nodo(new Posicion(0.5f,0.5f));
		if (nodoprueba.hashCode() == nodoprueba3.hashCode())
			fail("Nodos tendrian que tener hashcodes diferentes");
	}

	/**
	 * Test method for 'is.SimTraffic.Mapa.Nodo.equals(Object)'
	 * Se comprueba que las posiciones de dos nodos son iguales
	 */
	public void testEqualsObject() {
		Posicion pos = new Posicion(1.0f,1.0f);
		Nodo nodoprueba = new Nodo(pos);
		Nodo nodoprueba2 = new Nodo(pos);
		if (nodoprueba != nodoprueba)
			fail("Nodo deberia ser igual a si mismo");
		if (!nodoprueba.equals(nodoprueba2))
			fail("Nodos en la misma posicion deberian ser iguales");		
		Nodo nodoprueba3 = new Nodo(new Posicion(0.5f,0.5f));
		if (nodoprueba.equals(nodoprueba3))
			fail("Nodos tendrian que ser diferentes");	
	}
	
	/**
	 * M�todo de prueba para a�adirTramo(Tramo)
	 * Se comprueba que al a�adir un tramo a un nodo, se ha insertado
	 * correctamente
	 */
	public void testA�adirTramo() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		nodo1.a�adirTramo(tramo1);
		assertTrue(nodo1.getTramos().contains(tramo1));			
	}
	
	/**
	 * M�todo de prueba para quitarTramo(Tramo)
	 * Se comprueba que cuando se ha insertado un tramo en un nodo,
	 * se elimina correctamente
	 *
	 */
	public void testQuitarTramo() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		nodo1.a�adirTramo(tramo1);
		nodo1.quitarTramo(tramo1);
		assertTrue(nodo1.getTramos().isEmpty());
		
	}
	

	/**
	 * M�todo de prueba para setEs(EntradaSalida)
	 * Se comprueba que se puede modificar correctamente el atributo
	 * de EntradaSalida
	 *
	 */
	public void testSetEsEntradaSalida (){
		Nodo nodo = new Nodo (new Posicion(100,100));
		int entran = 100;
		int salen = 100;
		int[] entradas = {1,2,3,4,5,6,7,8,9,10,11,12};
		int[] salidas = {1,2,3,4,5,6,7,8,9,10,11,12};
		EntradaSalida es = new EntradaSalida(entran, salen, entradas, salidas);
		nodo.setEs(es);
		assertEquals(nodo.getEs(),es);
			
	}
	/**
	 * M�todo de prueba para setSe�al(Se�al)
	 * Se comprueba que al hacer un setSe�al, se modifica correctamente
	 */
	public void testSetSe�alSe�al() {
		// TODO
	}
	/**
	 * M�todo de prueba para getSe�al()
	 * Se comprueba que el valor de la se�al devuelto es el correcto
	 */
	public void testGetSe�al() {
		//  TODO
	}
	/**
	 * M�todo de prueba para getPos()
	 * Se comprueba que la posici�n obtenida
	 */
	public void testGetPos() {
		Posicion pos = new Posicion (100,100);
		Nodo nodo1 = new Nodo (pos);
		assertEquals(nodo1.getPos(),pos);
	}
	/**
	 * M�todo de prueba para getTramos()
	 * Se comprueba que se devuelven correctamente los tramos
	 * de un nodo
	 */
	public void testGetTramos() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Nodo nodo3 = new Nodo (new Posicion(300,300));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		Tramo tramo2 = new Tramo (nodo1,nodo3);
		nodo1.a�adirTramo(tramo1);
		nodo1.a�adirTramo(tramo2);
		assertEquals(nodo1.getTramos().get(0),tramo1);
		assertEquals(nodo1.getTramos().get(1),tramo2);		
	}
	

}
