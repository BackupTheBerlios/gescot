package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase TipoNodoLeisure
 */
public class TipoNodoLeisureTest extends TestCase {

		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure.equals(Object)'
		 * Se comprueba que dos nodos son iguales
		 */
		public void testEqualsObject() {
			TipoNodoLeisure tipo1 = new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.0")); //$NON-NLS-1$
			TipoNodoLeisure tipo2= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.1")); //$NON-NLS-1$
			TipoNodoLeisure tipo3= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.2")); //$NON-NLS-1$
			if (tipo1 != tipo1)
				fail(Messages.getString("TipoNodoLeisureTest.3")); //$NON-NLS-1$
			if (!tipo1.equals(tipo2))
				fail(Messages.getString("TipoNodoLeisureTest.4"));			 //$NON-NLS-1$
			if (tipo1.equals(tipo3))
				fail(Messages.getString("TipoNodoLeisureTest.5"));	 //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure.testtraduciraOSM'
		 * Se comprueba que dos nodos obtienen la misma traducion OSM
		 */
		public void testtraduciraOSM()
		{
			TipoNodoLeisure tipo1 = new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.6")); //$NON-NLS-1$
			TipoNodoLeisure tipo2= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.7")); //$NON-NLS-1$
			TipoNodoLeisure tipo3= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.8")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
				fail(Messages.getString("TipoNodoLeisureTest.9")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
				fail(Messages.getString("TipoNodoLeisureTest.10")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
				fail(Messages.getString("TipoNodoLeisureTest.11")); //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure.testtraduciraCastellano'
		 * Se comprueba que dos nodos obtienen la misma traducion a castellano
		 */
		public void testtraduciraCastellano()
		{
			TipoNodoLeisure tipo1 = new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.12")); //$NON-NLS-1$
			TipoNodoLeisure tipo2= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.13")); //$NON-NLS-1$
			TipoNodoLeisure tipo3= new TipoNodoLeisure(Messages.getString("TipoNodoLeisureTest.14")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
				fail(Messages.getString("TipoNodoLeisureTest.15")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
				fail(Messages.getString("TipoNodoLeisureTest.16")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
				fail(Messages.getString("TipoNodoLeisureTest.17")); //$NON-NLS-1$
		}
		

	}
