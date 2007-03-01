package is.SimTraffic.Mapa;

import junit.framework.TestCase;

public class NodoTest extends TestCase {

	/*
	 * Test method for 'is.SimTraffic.Mapa.Nodo.hashCode()'
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

	/*
	 * Test method for 'is.SimTraffic.Mapa.Nodo.equals(Object)'
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

}
