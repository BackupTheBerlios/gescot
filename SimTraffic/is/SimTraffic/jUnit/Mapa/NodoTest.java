package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;
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
	
	public void testAñadirTramo() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		nodo1.añadirTramo(tramo1);
		assertTrue(nodo1.getTramos().contains(tramo1));
					
	}
	
	public void testQuitarTramo() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		nodo1.añadirTramo(tramo1);
		nodo1.quitarTramo(tramo1);
		assertTrue(nodo1.getTramos().isEmpty());
		
	}
	
	public void testGetEs(){
		Nodo nodo = new Nodo (new Posicion(100,100));
		int[] franjas = new int [3];
		franjas[0]=60;
		franjas[1]=10;
		franjas[2]=30;
		int cut = 5;
		nodo.setEs(new EntradaSalida(cut,franjas));
		EntradaSalida es = nodo.getEs();
		assertEquals(es.getCochesUnidadTiempo(),cut);
		assertEquals(es.getValoresFranjaHoraria(),franjas);
		
	}
	
	
	
	public void testSetEsEntradaSalida (){
		Nodo nodo = new Nodo (new Posicion(100,100));
		int[] franjas = new int [3];
		franjas[0]=60;
		franjas[1]=10;
		franjas[2]=30;
		int cut = 5;
		EntradaSalida es = new EntradaSalida(cut,franjas);
		nodo.setEs(es);
		assertEquals(nodo.getEs(),es);
			
	}

	public void testSetSeñalSeñal() {
		Señal s = new Señal ("Prueba");
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		nodo1.setSeñal(s);
		assertEquals(nodo1.getSeñal(),s);
	}

	public void testGetSeñal() {
		Señal s = new Señal ("Prueba");
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		nodo1.setSeñal(s);
		assertEquals(nodo1.getSeñal(),s);
	}

	public void testGetPos() {
		Posicion pos = new Posicion (100,100);
		Nodo nodo1 = new Nodo (pos);
		assertEquals(nodo1.getPos(),pos);
	}

	public void testGetTramos() {
		Nodo nodo1 = new Nodo (new Posicion(100,100));
		Nodo nodo2 = new Nodo (new Posicion(200,200));
		Nodo nodo3 = new Nodo (new Posicion(300,300));
		Tramo tramo1 = new Tramo (nodo1,nodo2);
		Tramo tramo2 = new Tramo (nodo1,nodo3);
		nodo1.añadirTramo(tramo1);
		nodo1.añadirTramo(tramo2);
		assertEquals(nodo1.getTramos().get(0),tramo1);
		assertEquals(nodo1.getTramos().get(1),tramo2);
		
		
	}
	

}
