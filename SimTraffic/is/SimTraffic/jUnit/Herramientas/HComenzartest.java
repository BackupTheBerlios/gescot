package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HAñadirTramo;
import is.SimTraffic.Herramientas.HComenzar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

/**
 * Matodo que testea la clase HComenzar
 */
public class HComenzartest extends TestCase {

	/**
	 * Atributo que es un modelo
	 */
	Modelo modelo;
	
	/**
	 * Metodo que inicializa los valores del modelo
	 * A este se le añade un tramo y sobre este modelo se probara
	 * la simulacion
	 */
	protected void setUp () throws Exception
	{
	super.setUp();		
	Nodo nodoInicial = new Nodo (new Posicion(100,100));
	Nodo nodoFinal = new Nodo (new Posicion(200,200));
	int velMax = 50;
	int numCarrilesDir1 = 2;
	int numCarrilesDir2 = 3;		
	IHerramienta herramienta = new HAñadirTramo(nodoInicial,nodoFinal,velMax,
			numCarrilesDir1,numCarrilesDir2);
	modelo = new Modelo();
	modelo.getMapa().insertar(nodoInicial);
	modelo.getMapa().insertar(nodoFinal);
	herramienta.hacer(modelo);
	}

	/**
	 * Metodo que comprueba el funcionamiento del metodo hacer
	 */
	public void testhacer()
	{
		IHerramienta Hcomenzar = new HComenzar();
		assertEquals(0,Hcomenzar.hacer(modelo)); 
	}
	/**
	 * Metodo que comprueba el funcionamiento del metodo hacer
	 */
	public void testdeshacer()
	{
		IHerramienta Hcomenzar = new HComenzar();
		assertEquals(0,Hcomenzar.deshacer(modelo)); 
	}
}
