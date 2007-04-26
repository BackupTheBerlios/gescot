package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;

public class HCrearSemaforo implements IHerramienta {

	Nodo nodo;
	Semaforo nuevoSemaforo;
	
	public HCrearSemaforo(Nodo nodo){
		this.nodo = nodo;
	}
	
	public int hacer(IModelo modelo) {
		nuevoSemaforo = new Semaforo(nodo);
		nodo.setSe�al(nuevoSemaforo);
		return 0;
	}

	public int deshacer(IModelo modelo) {
		int indiceNodoSem = modelo.getMapa().getNodos().indexOf(nodo);
		//Le borramos la referencia al sem�foro.
		modelo.getMapa().getNodos().get(indiceNodoSem).setSe�al(null);
		
		return 0;
	}

}
