package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;

public class HConfigurarEntradaSalida implements IHerramienta {
	//Suponemos que recibimos como parametros el nodo
	//y los valores de entrada salida

	Nodo nodo;
	
	int[] valoresFranjas;
	
	public HConfigurarEntradaSalida(Nodo nodo, int[] valoresFranjas) {
		this.nodo = nodo;
		this.valoresFranjas = valoresFranjas;
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
