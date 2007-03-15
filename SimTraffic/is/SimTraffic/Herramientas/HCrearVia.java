package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class HCrearVia implements IHerramienta {

	String nombre;
	ArrayList<Tramo> Tramos;
	ITipoElemento tipoVia;
	
	public HCrearVia(String nombre,ArrayList<Tramo> Lista,ITipoElemento tipoVia){
		
		this.nombre = nombre;
		this.Tramos = Lista ;
		this.tipoVia = tipoVia;
		
	}
	
	public int hacer(IModelo modelo) {
		new Via();
		// TODO Auto-generated method stub
		return 0;
	}

	public int deshacer(IModelo modelo) {
		
		// TODO Auto-generated method stub
		return 0;
	}

}
