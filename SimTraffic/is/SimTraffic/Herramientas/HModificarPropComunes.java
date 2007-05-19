package is.SimTraffic.Herramientas;

import java.util.ArrayList;
import java.util.Iterator;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;

public class HModificarPropComunes implements IHerramienta
{

	private Tramo tramo;
	private int numCarr1, numCarr2;
	private float vel;
	
	public HModificarPropComunes(Tramo tramo, int auxCarriles1, int auxCarriles2, float f) 
	{
		this.tramo = tramo;
		this.numCarr1 = auxCarriles1;
		this.numCarr2 = auxCarriles2;
		this.vel = f;
	}

	public int deshacer(IModelo modelo) 
	{
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
		return 0;
	}

}
