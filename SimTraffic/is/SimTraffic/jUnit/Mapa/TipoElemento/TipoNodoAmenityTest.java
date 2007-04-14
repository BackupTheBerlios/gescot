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
			fail("Tipos con el mismo tipo deberian ser iguales");			
		if (tipo1.equals(tipo3))
			fail("Tipos con distintos tipos deberían ser diferentes");	
	}
	
	public void testtraduciraOSMOSM()
	{
		TipoNodoAmenity tipo1 = new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo2= new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo3= new TipoNodoAmenity("Gasolinera");
		if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
			fail("Tipo deberia tener la misma traduccion");
		if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
			fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
		if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
			fail("Los tipos deberian tener una traduccion distinta");
	}
	public void testtraduciraCastellano()
	{
		TipoNodoAmenity tipo1 = new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo2= new TipoNodoAmenity("Parking");
		TipoNodoAmenity tipo3= new TipoNodoAmenity("Gasolinera");
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
			fail("Tipo deberia tener la misma traduccion");
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
			fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
			fail("Los tipos deberian tener una traduccion distinta");
	}
	

}