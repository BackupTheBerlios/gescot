package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase TipoNodoHigway
 */
public class TipoNodoHigwayTest extends TestCase {

	/**
	 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoHigway.equals(Object)'
	 * Se comprueba que dos nodos son iguales
	 */
		public void testEqualsObject() {
			TipoNodoHighway tipo1 = new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo2= new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo3= new TipoNodoHighway("Stop");
			if (tipo1 != tipo1)
				fail("Tipo deberia ser igual a si misma");
			if (!tipo1.equals(tipo2))
				fail("Tipos con el mismo tipo deberian ser iguales");			
			if (tipo1.equals(tipo3))
				fail("Tipos con distintos tipos deber�an ser diferentes");	
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoHigway.testtraduciraOSM'
		 * Se comprueba que dos nodos obtienen la misma traducion OSM
		 */
		public void testtraduciraOSM()
		{
			TipoNodoHighway tipo1 = new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo2= new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo3= new TipoNodoHighway("Stop");
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
				fail("Tipo deberia tener la misma traduccion");
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
				fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
			if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
				fail("Los tipos deberian tener una traduccion distinta");
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoHigway.testtraduciraCastellano'
		 * Se comprueba que dos nodos obtienen la misma traducion a castellano
		 */
		public void testtraduciraCastellano()
		{
			TipoNodoHighway tipo1 = new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo2= new TipoNodoHighway("Mini-rotonda");
			TipoNodoHighway tipo3= new TipoNodoHighway("Stop");
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
				fail("Tipo deberia tener la misma traduccion");
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
				fail("Tipo deberia tener la misma traduccion que un tipo igual a este");
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
				fail("Los tipos deberian tener una traduccion distinta");
		}
		

	}