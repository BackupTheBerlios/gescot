package is.SimTraffic.jUnit.LibreriaIA.Algoritmos.Problema.DistanciaNodos;

import is.SimTraffic.Herramientas.HPegar;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.EstadoDistanciaNodos;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.HeuristicaDistanciaLineaRecta;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import junit.framework.TestCase;

public class HeuristicaDistanciaLineaRectaTest extends TestCase{

	HeuristicaDistanciaLineaRecta heuristica;
	
	/**
	 * 
	 * @param arg0
	 */
	public HeuristicaDistanciaLineaRectaTest(String arg0) {
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		EstadoDistanciaNodos objetivo = new EstadoDistanciaNodos(new Nodo(new Posicion(100,102)));
	    heuristica = new HeuristicaDistanciaLineaRecta(objetivo);
	}
	
	
	public void testdarValorHeuristico()
	{

		EstadoDistanciaNodos estado = new EstadoDistanciaNodos(new Nodo(new Posicion(100,100)));
		heuristica.darValorHeuristico(estado);
		//assertEquals(estado,estado);
		
	}
}