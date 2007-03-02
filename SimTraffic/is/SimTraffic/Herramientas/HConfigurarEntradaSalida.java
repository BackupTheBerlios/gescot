package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;

public class HConfigurarEntradaSalida implements IHerramienta {
	//Suponemos que recibimos como parametros el nodo
	//y los valores de entrada salida

	Nodo nodo;
	int[] valoresFranjas;
	int  frecuencia;
	EntradaSalida esGuardado;
	
	public HConfigurarEntradaSalida(Nodo nodo, int[] valoresFranjas,int frecuencia) {
		this.nodo = nodo;
		this.valoresFranjas = valoresFranjas;
		this.frecuencia = frecuencia; 
	}
	
	public int hacer(IModelo modelo) {
	    esGuardado = nodo.getEs();
		for (int i = 0 ; i < valoresFranjas.length; i++){
		 if ((valoresFranjas[i]>100) ||(valoresFranjas[i]<0)) 
		  return i;
		}
		EntradaSalida es = new EntradaSalida(frecuencia,valoresFranjas);
		nodo.setEs(es);
		// TODO Auto-generated method stub
		return 0;
	}

	public int deshacer(IModelo modelo) {
		nodo.setEs(esGuardado);
		// TODO Auto-generated method stub
		return 0;
	}

}
