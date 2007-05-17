package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase TipoNodoManMade
 */
public class TipoNodoManMadeTest extends TestCase {

		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade.equals(Object)'
		 * Se comprueba que dos nodos son iguales
		 */
		public void testEqualsObject() {
			TipoNodoManMade tipo1 = new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.0")); //$NON-NLS-1$
			TipoNodoManMade tipo2= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.1")); //$NON-NLS-1$
			TipoNodoManMade tipo3= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.2")); //$NON-NLS-1$
			if (tipo1 != tipo1)
				fail(Messages.getString("TipoNodoManMadeTest.3")); //$NON-NLS-1$
			if (!tipo1.equals(tipo2))
				fail(Messages.getString("TipoNodoManMadeTest.4"));			 //$NON-NLS-1$
			if (tipo1.equals(tipo3))
				fail(Messages.getString("TipoNodoManMadeTest.5"));	 //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade.testtraduciraOSM'
		 * Se comprueba que dos nodos obtienen la misma traducion OSM
		 */
		public void testtraduciraOSM()
		{
			TipoNodoManMade tipo1 = new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.6")); //$NON-NLS-1$
			TipoNodoManMade tipo2= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.7")); //$NON-NLS-1$
			TipoNodoManMade tipo3= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.8")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
				fail(Messages.getString("TipoNodoManMadeTest.9")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
				fail(Messages.getString("TipoNodoManMadeTest.10")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
				fail(Messages.getString("TipoNodoManMadeTest.11")); //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade.testtraduciraCastellano'
		 * Se comprueba que dos nodos obtienen la misma traducion a castellano
		 */
		public void testtraduciraCastellano()
		{
			TipoNodoManMade tipo1 = new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.12")); //$NON-NLS-1$
			TipoNodoManMade tipo2= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.13")); //$NON-NLS-1$
			TipoNodoManMade tipo3= new TipoNodoManMade(Messages.getString("TipoNodoManMadeTest.14")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
				fail(Messages.getString("TipoNodoManMadeTest.15")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
				fail(Messages.getString("TipoNodoManMadeTest.16")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
				fail(Messages.getString("TipoNodoManMadeTest.17")); //$NON-NLS-1$
		}
		

	}