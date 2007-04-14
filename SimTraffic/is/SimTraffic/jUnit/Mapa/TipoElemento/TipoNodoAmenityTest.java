package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import junit.framework.TestCase;


public class TipoNodoAmenityTest extends TestCase {

	public void testEqualsObject() {
		TipoNodoAmenity tipo1 = new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo2= new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo3= new TipoNodoAmenity("Gasolinera");
		if (tipo1 != tipo1)
			fail("Tipo deberia ser igual a si misma");
		if (!tipo1.equals(tipo2))
			fail("Posciones con las mismas coordenadas deberian ser iguales");			
		if (tipo1.equals(tipo3))
			fail("Posiciones con coordenadas diferentes deberían ser diferentes");	
	}

}