package is.SimTraffic.jUnit.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;

import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class ExplorarNodoTest extends TestCase{

	Nodo origen;
	
	Nodo destino;
	
	Nodo intermedio1;
	
	Nodo intermedio2;
	
	Tramo tramo1;
	
	Tramo tramo2;
	
	ExploraNodo explorar;
	
	
	/**
	 * 
	 * @param arg0
	 */
	public ExplorarNodoTest(String arg0) {
		super(arg0);
	}
	
	protected void setUp() {

		origen = new Nodo(new Posicion(50,50));
		destino = new Nodo(new Posicion(100,100));
		intermedio1= new Nodo(new Posicion(300,100));
		intermedio2= new Nodo(new Posicion(75,75));
		tramo1 = new Tramo(3, origen, intermedio1);
		tramo2 = new Tramo(3, origen, intermedio2);
	}
}
