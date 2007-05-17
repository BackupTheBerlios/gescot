package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HPegar;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import junit.framework.TestCase;

/**
 * Clase para probar la herramienta Copiar 
 *
 */
public class HPegarTest extends TestCase {
		
	
	/**
	 * Atributo que es ista de nodos necesaria para las pruebas
	 */
	@SuppressWarnings(Messages.getString("HPegarTest.0")) //$NON-NLS-1$
	private List<Nodo> nodos;
	
	
	/**
	 * Atributo que es ista de tramos necesaria para las pruebas
	 */
	@SuppressWarnings(Messages.getString("HPegarTest.1")) //$NON-NLS-1$
	private List<Tramo> tramos;
	
	/**
	 * La herrameinta que se va a utilizar
	 */
	private IHerramienta herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;
	
	/**
	 * Atributo que representa el punto.
	 */
	Point2D punto;

	/**
	 * 
	 * @param arg0
	 */
	public HPegarTest(String arg0) {
		super(arg0);
	}
	
	/**
	 * Metodo para inicializar las pruebas
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Nodo nodo1 = new Nodo(new Posicion(50,100));
		Nodo nodo2 = new Nodo(new Posicion(150,150));
		Nodo nodo3 = new Nodo(new Posicion(200,250));
		Tramo tramo1 = new Tramo(nodo1,nodo2);
		Tramo tramo2 = new Tramo(nodo1,nodo2);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(nodo3);
		modelo.getMapa().insertar(tramo1);
		modelo.getMapa().insertar(tramo2);
		punto = new Point();
		herramienta=new HPegar(punto);		
	}
	
	/**
	 * Mwtodo que compreuaba que se crea correctamente
	 */
	public void testHPegar() {
		assertNotNull(((HPegar)herramienta).getNodos());
		assertNotNull(((HPegar)herramienta).getTramos());
		assertEquals(((HPegar)herramienta).getPuntoPegar(),punto);
		
		
	}
	
	/**
	 * Mwtodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		if (herramienta.hacer(modelo)!=0)
			fail(Messages.getString("HPegarTest.2")); //$NON-NLS-1$
	}
	
	/**
	 * Mwtodo que compreuaba que sel metodo deshacer funciona correctamente -----------------Este metodo falla 
	 */
	public void testdeshacer()
	{
		if (herramienta.deshacer(modelo)!=0)
			fail(Messages.getString("HPegarTest.3")); //$NON-NLS-1$
	}
}
