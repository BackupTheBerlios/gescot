package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Mapa.TipoElemento.TipoViaHighway;
import junit.framework.TestCase;

/**
* Clase JUnit para probar la clase TipoviaHighway
*/
	public class TipoViaHighwayTest extends TestCase {

			/**
			 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoviaHighway.equals(Object)'
			 * Se comprueba que dos nodos son iguales
			 */
			public void testEqualsObject() {
				TipoViaHighway tipo1 = new TipoViaHighway("Autovia");
				TipoViaHighway tipo2= new TipoViaHighway("Autovia");
				TipoViaHighway tipo3= new TipoViaHighway("Escaleras");
				if (tipo1 != tipo1)
					fail("Tipo deberia ser igual a si misma");
				if (!tipo1.equals(tipo2))
					fail("Tipos con el mismo tipo deberian ser iguales");			
				if (tipo1.equals(tipo3))
					fail("Tipos con distintos tipos deberían ser diferentes");	
			}
			/**
			 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoviaHighway.testtraduciraOSM'
			 * Se comprueba que dos nodos obtienen la misma traducion OSM
			 */
			public void testtraduciraOSM()
			{
				TipoViaHighway tipo1 = new TipoViaHighway("Autovia");
				TipoViaHighway tipo2= new TipoViaHighway("Autovia");
				TipoViaHighway tipo3= new TipoViaHighway("Escaleras");
				if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
					fail("Tipo deberia tener la misma traduccion");
				if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
					fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
				if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
					fail("Los tipos deberian tener una traduccion distinta");
			}
			/**
			 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoviaHighway.testtraduciraCastellano'
			 * Se comprueba que dos nodos obtienen la misma traducion a castellano
			 */
			public void testtraduciraCastellano()
			{
				TipoViaHighway tipo1 = new TipoViaHighway("Autovia");
				TipoViaHighway tipo2= new TipoViaHighway("Autovia");
				TipoViaHighway tipo3= new TipoViaHighway("Escaleras");
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
					fail("Tipo deberia tener la misma traduccion");
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
					fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
					fail("Los tipos deberian tener una traduccion distinta");
			}
	}
