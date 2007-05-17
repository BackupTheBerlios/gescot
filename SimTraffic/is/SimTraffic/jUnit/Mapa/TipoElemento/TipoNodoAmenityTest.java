package is.SimTraffic.jUnit.Mapa.TipoElemento;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import junit.framework.TestCase;

/**
 * Clase JUnit para probar la clase TipoNodoAmenity
 */
public class TipoNodoAmenityTest extends TestCase {

	/**
	 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity.equals(Object)'
	 * Se comprueba que dos nodos son iguales
	 */
	public void testEqualsObject() {
		TipoNodoAmenity tipo1 = new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.0")); //$NON-NLS-1$
		TipoNodoAmenity tipo2= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.1")); //$NON-NLS-1$
		TipoNodoAmenity tipo3= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.2")); //$NON-NLS-1$
		if (tipo1 != tipo1)
			fail(Messages.getString("TipoNodoAmenityTest.3")); //$NON-NLS-1$
		if (!tipo1.equals(tipo2))
			fail(Messages.getString("TipoNodoAmenityTest.4"));			 //$NON-NLS-1$
		if (tipo1.equals(tipo3))
			fail(Messages.getString("TipoNodoAmenityTest.5"));	 //$NON-NLS-1$
	}
	/**
	 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity.testtraduciraOSM'
	 * Se comprueba que dos nodos obtienen la misma traducion OSM
	 */
	public void testtraduciraOSM()
	{
		TipoNodoAmenity tipo1 = new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.6")); //$NON-NLS-1$
		TipoNodoAmenity tipo2= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.7")); //$NON-NLS-1$
		TipoNodoAmenity tipo3= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.8")); //$NON-NLS-1$
		if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo1.traduciraOSM(tipo1.getValorTipo()))
			fail(Messages.getString("TipoNodoAmenityTest.9")); //$NON-NLS-1$
		if (tipo1.traduciraOSM(tipo1.getValorTipo())!=tipo2.traduciraOSM(tipo2.getValorTipo()))
			fail(Messages.getString("TipoNodoAmenityTest.10")); //$NON-NLS-1$
		if (tipo1.traduciraOSM(tipo1.getValorTipo())==tipo3.traduciraOSM(tipo3.getValorTipo()))
			fail(Messages.getString("TipoNodoAmenityTest.11")); //$NON-NLS-1$
	}
	/**
	 * Test method for 'is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity.testtraduciraCastellano'
	 * Se comprueba que dos nodos obtienen la misma traducion a castellano
	 */
	public void testtraduciraCastellano()
	{
		TipoNodoAmenity tipo1 = new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.12")); //$NON-NLS-1$
		TipoNodoAmenity tipo2= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.13")); //$NON-NLS-1$
		TipoNodoAmenity tipo3= new TipoNodoAmenity(Messages.getString("TipoNodoAmenityTest.14")); //$NON-NLS-1$
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo())))
			fail(Messages.getString("TipoNodoAmenityTest.15")); //$NON-NLS-1$
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))!=tipo2.traduciraCastellano(tipo2.traduciraOSM(tipo2.getValorTipo())))
			fail(Messages.getString("TipoNodoAmenityTest.16")); //$NON-NLS-1$
		if (tipo1.traduciraCastellano(tipo1.traduciraOSM(tipo1.getValorTipo()))==tipo3.traduciraCastellano(tipo3.traduciraOSM(tipo3.getValorTipo())))
			fail(Messages.getString("TipoNodoAmenityTest.17")); //$NON-NLS-1$
	}
	

}