package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class HModificarTramo implements IHerramienta {

	
	private Tramo tramo;
	private int numCarrilesDir1,numCarrilesDir2;
	private int numCarrilesGuardados1,numCarrilesGuardados2;
	private float velocidadMax,velocidadMaxGuardada;
	
	
	public HModificarTramo(Tramo tramo,int numCarrilesDir1,int numCarrilesDir2,float velocidadMax){
	  
		this.tramo=tramo;
		this.numCarrilesDir1=numCarrilesDir1;
		this.numCarrilesDir2=numCarrilesDir2;
		this.velocidadMax=velocidadMax;
		this.numCarrilesGuardados1=tramo.getNumCarrilesDir1();
		this.numCarrilesGuardados2=tramo.getNumCarrilesDir2();
		this.velocidadMaxGuardada=tramo.getVelMax();
	}
	
	/**
	 * 
	 */
	public int deshacer(IModelo modelo) {
		tramo.setNumCarrilesDir1(numCarrilesGuardados1);
		tramo.setNumCarrilesDir2(numCarrilesGuardados2);
		tramo.setVelMax(velocidadMaxGuardada);
		return 0;
	}
	
	/**
	 * 
	 */
	public int hacer(IModelo modelo) {
		tramo.setNumCarrilesDir1(numCarrilesDir1);
		tramo.setNumCarrilesDir2(numCarrilesDir2);
		tramo.setVelMax(velocidadMax);
		return 0;
	}

}
