package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.TipoViaHighway;
import is.SimTraffic.Utils.Tiempo;

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
	private String nombre;
	private String nombreGuardado;
	private String nombreStringVia;// = nombreVia.getText();
	private String tipoStringVia;// = (String) combo_tipoVia.getSelectedItem();
	private String nombreStringViaGuardado;	
	private String tipoStringViaGuardado;
	private Via via;
	
	public HModificarTramo(Tramo tramo,int numCarrilesDir1,int numCarrilesDir2,float velocidadMax, String nombre,String nombreStringVia,String tipoStringVia){
	  
		this.tramo=tramo;
		this.via = tramo.getVia();
		this.numCarrilesDir1=numCarrilesDir1;
		this.numCarrilesDir2=numCarrilesDir2;
		this.velocidadMax=velocidadMax;
		this.nombre = nombre;
		this.numCarrilesGuardados1=tramo.getNumCarrilesDir1();
		this.numCarrilesGuardados2=tramo.getNumCarrilesDir2();
		this.velocidadMaxGuardada=tramo.getVelMax();
		this.nombreGuardado=tramo.getNombre();
		this.nombreStringVia = nombreStringVia;
		if (via!=null) {
			if (via.getNombre()!=null)
				this.nombreStringViaGuardado = via.getNombre();
			if (via.getTipo()!=null)
				this.tipoStringViaGuardado = via.getTipo().getValorTipoCastellano();
		}
		this.tipoStringVia = tipoStringVia;
	}
	
	/**
	 * 
	 */
	public int deshacer(IModelo modelo) {
		tramo.setNumCarrilesDir1(numCarrilesGuardados1);
		tramo.setNumCarrilesDir2(numCarrilesGuardados2);
		tramo.setVelMax(velocidadMaxGuardada);
		tramo.setNombre(nombreGuardado);
		if (via!=null) {
			via.setNombre(nombreStringViaGuardado);
			via.setTipo(new TipoViaHighway(tipoStringViaGuardado));
		}
		return 0;
	}
	
	/**
	 * 
	 */
	public int hacer(IModelo modelo) {
		tramo.setNumCarrilesDir1(numCarrilesDir1);
		tramo.setNumCarrilesDir2(numCarrilesDir2);
		tramo.setVelMax(velocidadMax);
		tramo.setNombre(nombre);
		if (via!=null) {
			via.setNombre(nombreStringVia);
			via.setTipo(new TipoViaHighway(tipoStringVia));
		}
		return 0;
	}
	
	public String toString(){
		return Tiempo.Hora()+" - "+ "Tramo modificado";
	}
}
