package is.SimTraffic.jUnit.Mapa;

import is.SimTraffic.Mapa.*;
import junit.framework.TestCase;

/**
 * Clase de prueba de la clase EntradaSalida 
 *
 */

public class EntradaSalidaTest extends TestCase {
	
	/**
	 * Atributo de prueba de EntradaSalida
	 */
	private EntradaSalida es;
	/**
	 * Atributo de prueba de cochesUnidadTiempo
	 */
	private int cut;
	/**
	 * Atributo de prueba de valoresFranjaHoraria
	 */
	private int[] franjas;
	/**
	 * Atributo de prueba de cochesQueHanEntradoYnoHanSalido
	 */
	private int cochesEnMapa;
	
	public EntradaSalidaTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		cut=5;
		franjas=new int[3];
		franjas[0]=20;
		franjas[1]=30;
		franjas[2]=50;
		es = new EntradaSalida(cut,franjas);
		cochesEnMapa=1000;
	}
	
	/**
	 * Método de prueba de la constructora
	 * Se comprueba que los atributos de EntradaSalida son los asignados
	 */
	
	public void testEntradaSalida() {
		assertEquals(franjas,es.getValoresFranjaHoraria());
		assertEquals(cut,es.getCochesUnidadTiempo());
		assertEquals(0,es.getCochesQueHanEntradoYnoHanSalido());
	}

	/**
	 * Método de prueba de saleCocheDelMapa()
	 * Se comprueba que el número de coches que hay en el mapa, atributo
	 * cochesQueHanEntradoYnoHanSalido, ha disminuido en uno
	 */
	public void testSaleCocheDelMapa() {
		
		es.setCochesQueHanEntradoYnoHanSalido(cochesEnMapa);
		int valorAnterior = es.getCochesQueHanEntradoYnoHanSalido();
		es.saleCocheDelMapa();
		int valorEsperado = valorAnterior-1;
		assertEquals(valorEsperado,es.getCochesQueHanEntradoYnoHanSalido());
		
	}
	
	/**
	 * Método de prueba de entraCocheEnMapa()
	 * Se comprueba que el número de coches que hay en el mapa, atributo
	 * cochesQueHanEntradoYnoHanSalido, ha aumentado en uno
	 */
	
	public void testEntraCocheEnMapa() {
		es.setCochesQueHanEntradoYnoHanSalido(cochesEnMapa);
		int valorAnterior = es.getCochesQueHanEntradoYnoHanSalido();
		es.entraCocheEnMapa();
		int valorEsperado = valorAnterior+1;
		assertEquals(valorEsperado,es.getCochesQueHanEntradoYnoHanSalido());
	}

	/**
	 * Método de prueba de getCochesQueHanEntradoYnoHanSalido()
	 * Se comprueba que el valor devuelto es correcto
	 *
	 */
	public void testGetCochesQueHanEntradoYnoHanSalido() {
		es.setCochesQueHanEntradoYnoHanSalido(cochesEnMapa);
		assertEquals(cochesEnMapa,es.getCochesQueHanEntradoYnoHanSalido());
	}
	
	/**
	 * Método de prueba de setCochesQueHanEntradoYnoHanSalido(int)
	 * Se comprueba que el valor asignado es correcto
	 *
	 */
	public void testSetCochesQueHanEntradoYnoHanSalido() {
		es.setCochesQueHanEntradoYnoHanSalido(cochesEnMapa);
		assertEquals(cochesEnMapa,es.getCochesQueHanEntradoYnoHanSalido());
	}

	/**
	 * Método de prueba de getCochesUnidadTiempo()
	 * Se comprueba que el valor devuelto es correcto
	 *
	 */
	public void testGetCochesUnidadTiempo() {
		assertEquals(cut,es.getCochesUnidadTiempo());
	}
	/**
	 * Método de prueba de setCochesUnidadTiempo()
	 * Se comprueba que se modifica correctamente el atributo
	 */
	public void testSetCochesUnidadTiempo() {
		int cut2 = 10;
		es.setCochesUnidadTiempo(cut2);
		assertEquals(cut2,es.getCochesUnidadTiempo());
	}
	/**
	 * Método de prueba de getValoresFranjaHoraria()
	 * Se comprueba que el valor devuelto es correcto
	 */
	public void testGetValoresFranjaHoraria() {
		assertEquals(franjas,es.getValoresFranjaHoraria());
	}
	/**
	 * Método de prueba de setValoresFranjaHoraria()
	 * Se comprueba que el valor se modifica correctamente
	 */
	public void testSetValoresFranjaHoraria() {
		int[] franjas2;
		franjas2= new int[2];
		franjas2[0]=30;
		franjas2[1]=70;
		es.setValoresFranjaHoraria(franjas2);
		assertEquals(franjas2,es.getValoresFranjaHoraria());
	}

}
