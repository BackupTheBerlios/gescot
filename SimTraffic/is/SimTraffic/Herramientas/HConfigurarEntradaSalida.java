package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Utils.Tiempo;

public class HConfigurarEntradaSalida implements IHerramienta {
	//Suponemos que recibimos como parametros el nodo
	//y los valores de entrada salida

	Nodo nodo;
	int[] porcentajesEntrada;
	int[] porcentajesSalida;

	EntradaSalida esGuardado;
	
	public HConfigurarEntradaSalida(Nodo nodo, int[] porcentajesEntrada, int[] porcentajesSalida) {
		this.nodo = nodo;
		this.porcentajesEntrada = porcentajesEntrada;
		this.porcentajesSalida = porcentajesSalida;		
	}
	
	public int hacer(IModelo modelo) {
	    esGuardado = nodo.getEs();
		EntradaSalida es = new EntradaSalida(porcentajesEntrada, porcentajesSalida);
		nodo.setEs(es);
		return 0;
	}

	public int deshacer(IModelo modelo) {
		nodo.setEs(esGuardado);
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+"Entrada/Salida Configurada";
	}
}
