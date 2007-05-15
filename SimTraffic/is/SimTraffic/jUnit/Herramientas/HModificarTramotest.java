package is.SimTraffic.jUnit.Herramientas;

import java.util.LinkedList;

import is.SimTraffic.Modelo;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Herramientas.HModificarTramo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import junit.framework.TestCase;

public class HModificarTramotest extends TestCase{
	
	Nodo nodo1;
	
	Nodo nodo2;
	
	Tramo tramo;
	
	int numCarrilesDir1;

	int numCarrilesDir2;
	
	float velocidadMax;
	
	String nombre;
	
	String nombreStringVia;
	
	String tipoStringVia;
	
	Modelo modelo;
	
	HModificarTramo herramienta;
	
	/**
	 * Constructor
	 * @param arg0
	 */
	public HModificarTramotest(String arg0) {
		super(arg0);
	}
	
	/**
	 * Metodo para inicializar las pruebas
	 */
	protected void setUp() throws Exception {
		super.setUp();
		nodo1 = new Nodo(new Posicion(50,100));
		nodo2 = new Nodo(new Posicion(150,150));
		this.numCarrilesDir1=1;
		this.numCarrilesDir2=2;
		this.velocidadMax=50;
		nombre="NUEVO";
		this.nombreStringVia="NUEVOVIA";
		this.tipoStringVia="TIPO NUEVO";
		tramo = new Tramo(nodo1,nodo2);
		modelo = new Modelo();
		modelo.getMapa().insertar(nodo1);
		modelo.getMapa().insertar(nodo2);
		modelo.getMapa().insertar(tramo);
		tramo.setNombre("ANTIGUO");
		tramo.setNumCarrilesDir1(2);
		tramo.setNumCarrilesDir2(1);
		tramo.setVelMax(100);
		herramienta=new HModificarTramo(tramo, numCarrilesDir1, numCarrilesDir2, velocidadMax, nombre, nombreStringVia, tipoStringVia);
	}
	
	/**
	 * M�todo que comprueba hacer ()
	 * Se comprueba que se modifica correctamente el tramo
	 */
	public void testHacer() {
		int resultado = herramienta.hacer(modelo);
		assertEquals(0,resultado);
		assertEquals(modelo.getMapa().getTramos().get(0).getNumCarrilesDir1(),1);
		assertEquals(modelo.getMapa().getTramos().get(0).getNumCarrilesDir2(),2);
		assertEquals(modelo.getMapa().getTramos().get(0).getVelMax(),50,0);
		assertTrue(modelo.getMapa().getTramos().get(0).getNombre().equalsIgnoreCase("NUEVO"));
	}
	
	/**
	 * M�todo que comprueba deshacer()
	 * Se comprueba que tras modificar el tramo y deshacerlo se vueve a tener el mismo tramo 
	 *
	 */
	public void testDeshacer() {		
		herramienta.hacer(modelo);
		int resultado = herramienta.deshacer(modelo);
		assertEquals(0,resultado);
		assertEquals(modelo.getMapa().getTramos().get(0).getNumCarrilesDir1(),2);
		assertEquals(modelo.getMapa().getTramos().get(0).getNumCarrilesDir2(),1);
		assertEquals(modelo.getMapa().getTramos().get(0).getVelMax(),100,0);
		assertTrue(modelo.getMapa().getTramos().get(0).getNombre().equalsIgnoreCase("ANTIGUO"));
	}
	
	
}
