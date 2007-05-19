package is.SimTraffic.Vista.Acciones.PanelTramo;

public class InfoTramo 
{
	private int numC1, numC2;
	private float velocidad;
	
	public InfoTramo(int nc1,int nc2,float v)
	{
		numC1 = nc1;
		numC2 = nc2;
		velocidad = v;
	}

	public int getNumC1() {
		return numC1;
	}

	public void setNumC1(int numC1) {
		this.numC1 = numC1;
	}

	public int getNumC2() {
		return numC2;
	}

	public void setNumC2(int numC2) {
		this.numC2 = numC2;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	
}
