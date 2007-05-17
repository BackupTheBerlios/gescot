package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Messages;
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
			TipoNodoHighway tipo1 = new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.0")); //$NON-NLS-1$
			TipoNodoHighway tipo2= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.1")); //$NON-NLS-1$
			TipoNodoHighway tipo3= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.2")); //$NON-NLS-1$
			if (tipo1 != tipo1)
				fail(Messages.getString("TipoNodoHigwayTest.3")); //$NON-NLS-1$
			if (!tipo1.equals(tipo2))
				fail(Messages.getString("TipoNodoHigwayTest.4"));			 //$NON-NLS-1$
			if (tipo1.equals(tipo3))
				fail(Messages.getString("TipoNodoHigwayTest.5"));	 //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoHigway.testtraduciraOSM'
		 * Se comprueba que dos nodos obtienen la misma traducion OSM
		 */
		public void testtraduciraOSM()
		{
			TipoNodoHighway tipo1 = new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.6")); //$NON-NLS-1$
			TipoNodoHighway tipo2= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.7")); //$NON-NLS-1$
			TipoNodoHighway tipo3= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.8")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
				fail(Messages.getString("TipoNodoHigwayTest.9")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
				fail(Messages.getString("TipoNodoHigwayTest.10")); //$NON-NLS-1$
			if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
				fail(Messages.getString("TipoNodoHigwayTest.11")); //$NON-NLS-1$
		}
		/**
		 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoHigway.testtraduciraCastellano'
		 * Se comprueba que dos nodos obtienen la misma traducion a castellano
		 */
		public void testtraduciraCastellano()
		{
			TipoNodoHighway tipo1 = new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.12")); //$NON-NLS-1$
			TipoNodoHighway tipo2= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.13")); //$NON-NLS-1$
			TipoNodoHighway tipo3= new TipoNodoHighway(Messages.getString("TipoNodoHigwayTest.14")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
				fail(Messages.getString("TipoNodoHigwayTest.15")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
				fail(Messages.getString("TipoNodoHigwayTest.16")); //$NON-NLS-1$
			if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
				fail(Messages.getString("TipoNodoHigwayTest.17")); //$NON-NLS-1$
		}
		

	}
