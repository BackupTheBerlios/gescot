package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class HCrearVia implements IHerramienta {


	Via via;
	ArrayList<Nodo> nodos,nodosGuardados;
	ArrayList<Tramo> tramos,tramosGuardados;
	
	public HCrearVia(String nombre,ArrayList<Tramo> Lista,ITipoElemento tipoVia){
		
		via = new Via();
		via.setNombre(nombre);
		via.setTramos(Lista);
		via.setTipo(tipoVia);
		
	}
	public HCrearVia(ArrayList<Nodo> ListaNodos,Via via){
		this.nodos=ListaNodos;
		this.via= via;
		this.tramos=via.getTramos();
	}
	
	public int hacer(IModelo modelo) {
		nodosGuardados=nodos;
		tramosGuardados =tramos;
		while (!nodos.isEmpty()){
			modelo.getMapa().insertar(nodos.get(0));
			nodos.remove(0);
		}
		while (!tramos.isEmpty()){
			modelo.getMapa().insertar(tramos.get(0));
			tramos.remove(0);
		}
		modelo.getMapa().insertar(via);
		return 0;
	}

	public int deshacer(IModelo modelo) {
		while (!nodos.isEmpty()){
			nodosGuardados.get(0);
			modelo.getMapa().eliminar(nodos.get(0));
			nodosGuardados.remove(0);
		}
		while (!tramosGuardados.isEmpty()){
			modelo.getMapa().eliminar(tramosGuardados.get(0));
			tramosGuardados.remove(0);
		}
		modelo.getMapa().eliminar(via);
		return 0;
	}

}
