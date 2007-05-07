package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Herramientas.HConfigurarEntradaSalida;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.util.List;

import junit.framework.TestCase;

public class HCopiarTest extends TestCase {
	
	private List<Nodo> nodos;
	
	private List<Tramo> tramos;
	
	private IHerramienta herramienta;

	
	protected void setUp() throws Exception {
		super.setUp();
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Nodo nodo3 = new Nodo(new Posicion(200,250));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		Tramo tramo2 = new Tramo(nodo1,nodo2);
		nodos.add(nodo1);
		nodos.add(nodo2);
		nodos.add(nodo3);
		tramos.add(tramo1);
		tramos.add(tramo2);
		herramienta=new HCopiar(nodos,tramos);
	}
}
