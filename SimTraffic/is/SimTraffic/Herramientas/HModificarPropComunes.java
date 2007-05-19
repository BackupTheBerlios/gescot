package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.TipoViaHighway;
import is.SimTraffic.Vista.Acciones.PanelTramo.InfoTramo;

import java.util.ArrayList;
import java.util.Iterator;

public class HModificarPropComunes implements IHerramienta
{

	private Tramo tramo;
	private int numCarr1, numCarr2;
	private float vel;
	private ArrayList<InfoTramo> infotramos;
	private String nombreStringViaGuardado;	
	private String tipoStringViaGuardado;
	private String nuevoNombreVia;
	private String nuevoTipoVia;
	
	public HModificarPropComunes(Tramo tramo, int auxCarriles1, int auxCarriles2, float f, String nuevoNombreVia, String nuevoTipoVia) 
	{
		this.tramo = tramo;
		this.numCarr1 = auxCarriles1;
		this.numCarr2 = auxCarriles2;
		this.vel = f;
		infotramos = new ArrayList<InfoTramo>();
		extraerInformacionTramos();
		this.nuevoNombreVia = nuevoNombreVia;
		this.nuevoTipoVia = nuevoTipoVia;
		
	}

	private void extraerInformacionTramos() 
	{
		ArrayList<Tramo> listat = tramo.getVia().getTramos();
		Iterator<Tramo> it = listat.iterator();
		Tramo t;
		while(it.hasNext())
		{
			t = it.next();
			infotramos.add(new InfoTramo(t.getNumCarrilesDir1(), t.getNumCarrilesDir2(), t.getVelMax()));
		}
	}

	public int deshacer(IModelo modelo) 
	{
		ArrayList<Tramo> listat = tramo.getVia().getTramos();
		Iterator<Tramo> it = listat.iterator();
		Iterator<InfoTramo> it2 = infotramos.iterator();
		Tramo t;
		InfoTramo inf;
		while(it.hasNext())
		{
			t = it.next();
			inf = it2.next();
			t.setNumCarrilesDir1(inf.getNumC1());
			t.setNumCarrilesDir2(inf.getNumC2());
			t.setVelMax(inf.getVelocidad());
		}
		Via via = tramo.getVia();
		via.setNombre(nombreStringViaGuardado);
		TipoViaHighway nuevotipo = new TipoViaHighway(tipoStringViaGuardado);
		via.setTipo(nuevotipo);
		return 0;
	}

	public int hacer(IModelo modelo) 
	{
		ArrayList<Tramo> listat = tramo.getVia().getTramos();
		Iterator<Tramo> it = listat.iterator();
		Tramo t;
		while(it.hasNext())
		{
			t = it.next();
			t.setNumCarrilesDir1(numCarr1);
			t.setNumCarrilesDir2(numCarr2);
			t.setVelMax(vel);
		}
		
		Via via = tramo.getVia();
		if (via != null)
		{
			nombreStringViaGuardado = via.getNombre();
			tipoStringViaGuardado = via.getTipo().getTipoCastellano();
			via.setNombre(nuevoNombreVia);
			TipoViaHighway nuevotipo = new TipoViaHighway(nuevoTipoVia);
			via.setTipo(nuevotipo);
		}
		return 0;
	}

}
