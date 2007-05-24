package is.SimTraffic.jUnit.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.EstadoDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

/**
 * Clase de pruebas para la clase EstadoDistanciaNodos
 *
 */
public class EstadoDistanciaNodosTest extends TestCase{
	
	/**
	 * Atributo que es un nodo necesario para las pruebas
	 */
	Nodo posicion;
	/**
	 * 
	 */
	EstadoDistanciaNodos estado;
	
	/**
	 * 
	 * @param arg0
	 */
	public EstadoDistanciaNodosTest(String arg0) {
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		posicion = new Nodo(new Posicion(100,100));
		estado = new EstadoDistanciaNodos(posicion);
	}
	
	public void testequals()
	{
		Nodo aux = new Nodo(new Posicion (10,50));
		EstadoDistanciaNodos estado= new EstadoDistanciaNodos(aux); 
		assertTrue(!(estado.equals(this.estado)));
		assertTrue((estado.equals(estado)));
	}
	
	
	
	
	
}
