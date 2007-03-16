package is.SimTraffic.jUnit.Herramientas;

import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.*;
import is.SimTraffic.Herramientas.*;
import java.util.ArrayList;
import junit.framework.TestCase;

public class HAsignarTramosAViaTest extends TestCase {
	
	HAsignarTramosAVia t;
	Via v;

	public HAsignarTramosAViaTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		String nombre = "via 1";
		String valorTipo = "valor tipo";
		TipoViaHighway tipoVia = new TipoViaHighway(valorTipo);
		int ID=1;
		t = new HAsignarTramosAVia(ID, tipoVia, nombre, tramos); 
		v = new Via();
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HAsignarTramosAVia.HAsignarTramosAVia(int, ITipoElemento, String, ArrayList<Tramo>)'
	 */
	public void testHAsignarTramosAVia() {
		assertEquals(t.getNombre(), "via 1");
		assertEquals(t.getID(),1);
		assertEquals(t.getTramos().size(),0);
		assertEquals(t.getTipoVia().getValorTipo(), "valor tipo");
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HAsignarTramosAVia.hacer(IModelo)'
	 */
	public void testHacer() {
		//aun no estaba hecho en la clase a probar
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HAsignarTramosAVia.deshacer(IModelo)'
	 */
	public void testDeshacer() {
		//aun no estaba hecho en la clase a probar
	}

	/*
	 * Test method for 'is.SimTraffic.Herramientas.HAsignarTramosAVia.configurarVia()'
	 */
	public void testConfigurarVia() {
		v = t.configurarVia();
		assertEquals(v.getNombre(), "via 1");
		assertEquals(v.getID(),1);
		assertEquals(v.getTramos().size(),0);
		assertEquals(v.getTipo().getValorTipo(), "valor tipo");
	}
}
