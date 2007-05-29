package is.SimTraffic.jUnit.LibreriaIA.Problema.DistanciaNodos;

import java.util.Vector;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.EstadoDistanciaNodos;
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
	
	NodoIA nodoia;
	
	Tramo tramo1;
	
	Tramo tramo2;
	
	ExploraNodo explorar;
	
	EstadoDistanciaNodos estado;
	
	Modelo modelo;
	
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
		estado = new EstadoDistanciaNodos(origen);
		nodoia= new NodoIA(estado);
		explorar = new ExploraNodo(origen,tramo1);
		modelo = new Modelo();
		modelo.getMapa().insertar(origen);
		modelo.getMapa().insertar(destino);
		modelo.getMapa().insertar(intermedio1);
		modelo.getMapa().insertar(intermedio2);
		modelo.getMapa().insertar(tramo1);
		modelo.getMapa().insertar(tramo2);
		
	}
	
	
	public void testaplicarOperador()
	{
		Vector<NodoIA> aux = explorar.aplicarOperador(nodoia);
		assertEquals(2,aux.size());
	}
	
	public void testgetCoste()
	{
		if (explorar.getCoste()==0.0) fail(Messages.getString("ExplorarNodoTest.0"));	 //$NON-NLS-1$
	}

}
