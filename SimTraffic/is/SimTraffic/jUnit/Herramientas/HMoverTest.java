package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

/**
 * Clase de prueba de HMover
 */
public class HMoverTest extends TestCase{

	/**
	 * Lista de nodos necesaria para las pruebas
	 */
	private List<Nodo> nodos;
	
	/**
	 * 
	 */
	double diferenciaX;
	
	/**
	 * Cuánto se ha movido con respecto a la posición inicial en el eje X.
	 */
	double diferenciaY;
	
	
	/**
	 * La herramienta que se va a utilizar
	 */
	private HMover herramienta;
	
	/**
	 * Atributo de prueba Modelo
	 */
	private Modelo modelo;
	
	
	public HMoverTest(String arg0) {
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
		nodos = new LinkedList<Nodo>();
		nodos.add(nodo1);
		nodos.add(nodo2);
		nodos.add(nodo3);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(nodo3);
		herramienta=new HMover(nodos);		
	}
	
	/**
	 * Metodo que comprueaba que se crea correctamente
	 */
	public void testHMover() {
		assertEquals(nodos,herramienta.getNodos());
		assertEquals(nodos.size(),herramienta.getNodosAnteriores().size());
	}
	
	
	public void testestableceInicioYFin(){
		Point2D puntoOrigen= new Point(500,500);
		Point2D puntoDestino= new Point(600,650);
		herramienta.estableceInicioYFin(puntoOrigen, puntoDestino);
		assertEquals(100.0,herramienta.getDiferenciaX());
		assertEquals(150.0,herramienta.getDiferenciaY());
	}
	
	/**
	 * Metodo que compreuaba que sel metodo hacer funciona correctamente
	 */
	public void testhacer()
	{
		if (herramienta.hacer(modelo)!=0)
			fail("La herramienta de Copiar no ha hecho correctamente");
	}
	
	/**
	 * Metodo que compreuaba que sel metodo deshacer funciona correctamente -----------------Este metodo falla 
	 */
	public void testdeshacer()
	{
		herramienta.hacer(modelo);
		if (herramienta.deshacer(modelo)!=0)
			fail("La herramienta de Copiar no ha desecho correctamente");
	}
	
	
}
