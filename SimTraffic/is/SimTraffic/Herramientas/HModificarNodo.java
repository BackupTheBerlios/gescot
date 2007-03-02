package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;

public class HModificarNodo implements IHerramienta {

	Nodo nodo;
	EntradaSalida es;
	
	
	public HModificarNodo(Nodo nodo,EntradaSalida es){
	 this.nodo = nodo;
	 this.es = es;
	}
	public int hacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
