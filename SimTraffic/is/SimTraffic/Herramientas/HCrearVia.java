package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;


public class HCrearVia implements IHerramienta {


	Via via;
	ArrayList<Nodo> nodos,nodosGuardados;
	ArrayList<Tramo> tramos,tramosGuardados;
	
	public HCrearVia(ArrayList<Nodo> ListaNodos,Via via){
		this.nodos=ListaNodos;
		this.via= via;
		this.tramos=via.getTramos();
	}
	
	public int hacer(IModelo modelo) {
		nodosGuardados = new ArrayList<Nodo>();
		while (!nodos.isEmpty()){
			modelo.getMapa().insertar(nodos.get(0));
			nodosGuardados.add(nodos.get(0));
			nodos.remove(0);
		}
		tramosGuardados = new ArrayList<Tramo>();
		while (!tramos.isEmpty()){
			modelo.getMapa().insertar(tramos.get(0));
			tramosGuardados.add(tramos.get(0));
			tramos.remove(0);
		}
		
		//Repasar, si esto puede alterar el orden.
		via.setTramos(tramosGuardados);
		
		modelo.getMapa().insertar(via);
		return 0;
	}

	public int deshacer(IModelo modelo) {
		
		nodosGuardados=null;
		tramosGuardados=null;
		modelo.getMapa().eliminar(via);
		return 0;
	}

	public String toString(){
		return Tiempo.Hora()+" - "+"Vía Creada";
	}

}
