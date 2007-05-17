package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Messages;
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
				TipoViaHighway tipo1 = new TipoViaHighway(Messages.getString("TipoViaHighwayTest.0")); //$NON-NLS-1$
				TipoViaHighway tipo2= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.1")); //$NON-NLS-1$
				TipoViaHighway tipo3= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.2")); //$NON-NLS-1$
				if (tipo1 != tipo1)
					fail(Messages.getString("TipoViaHighwayTest.3")); //$NON-NLS-1$
				if (!tipo1.equals(tipo2))
					fail(Messages.getString("TipoViaHighwayTest.4"));			 //$NON-NLS-1$
				if (tipo1.equals(tipo3))
					fail(Messages.getString("TipoViaHighwayTest.5"));	 //$NON-NLS-1$
			}
			/**
			 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoviaHighway.testtraduciraOSM'
			 * Se comprueba que dos nodos obtienen la misma traducion OSM
			 */
			public void testtraduciraOSM()
			{
				TipoViaHighway tipo1 = new TipoViaHighway(Messages.getString("TipoViaHighwayTest.6")); //$NON-NLS-1$
				TipoViaHighway tipo2= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.7")); //$NON-NLS-1$
				TipoViaHighway tipo3= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.8")); //$NON-NLS-1$
				if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
					fail(Messages.getString("TipoViaHighwayTest.9")); //$NON-NLS-1$
				if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
					fail(Messages.getString("TipoViaHighwayTest.10")); //$NON-NLS-1$
				if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
					fail(Messages.getString("TipoViaHighwayTest.11")); //$NON-NLS-1$
			}
			/**
			 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoviaHighway.testtraduciraCastellano'
			 * Se comprueba que dos nodos obtienen la misma traducion a castellano
			 */
			public void testtraduciraCastellano()
			{
				TipoViaHighway tipo1 = new TipoViaHighway(Messages.getString("TipoViaHighwayTest.12")); //$NON-NLS-1$
				TipoViaHighway tipo2= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.13")); //$NON-NLS-1$
				TipoViaHighway tipo3= new TipoViaHighway(Messages.getString("TipoViaHighwayTest.14")); //$NON-NLS-1$
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
					fail(Messages.getString("TipoViaHighwayTest.15")); //$NON-NLS-1$
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
					fail(Messages.getString("TipoViaHighwayTest.16")); //$NON-NLS-1$
				if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
					fail(Messages.getString("TipoViaHighwayTest.17")); //$NON-NLS-1$
			}
	}
