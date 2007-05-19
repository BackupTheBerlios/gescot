package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;


public class HCrearVia implements IHerramienta {


	Via via;
	ArrayList<Nodo> nodos,nodosUsados, nodosGuardados;
	ArrayList<Tramo> tramos,tramosModificados, tramosGuardados;
	
	public HCrearVia(ArrayList<Nodo> ListaNodos,ArrayList<Nodo> nodosUsados, ArrayList<Tramo> tramosModificados, Via via){
		this.nodos=ListaNodos;
		this.via= via;
		this.tramos=via.getTramos();
		this.nodosUsados = nodosUsados;
		this.tramosModificados = tramosModificados;
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
		
		while (!nodosUsados.isEmpty()){
			modelo.getMapa().insertar(nodosUsados.get(0));
			nodosUsados.remove(0);
		}
		
		while (!tramosModificados.isEmpty()){
			modelo.getMapa().insertar(tramosModificados.get(0));
			tramosModificados.remove(0);
		}
		
		return 0;
	}

	public String toString(){
		return Tiempo.Hora()+Messages.getString("HCrearVia.0")+Messages.getString("HCrearVia.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
