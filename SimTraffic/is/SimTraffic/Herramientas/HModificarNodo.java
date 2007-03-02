package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class HModificarNodo implements IHerramienta {

	Nodo nodo;
	ITipoElemento tipoNodo;
	Posicion pos;
	/**
	 * Estos booleanos nos permiten modificar el nodo cuando queremos
	 * borrar el nodo como punto de entrada salida o la se�al que tiene en el.
	 */
	boolean es;
	boolean se�al;
	
	
	public HModificarNodo(Nodo nodo,ITipoElemento  tipo,Posicion pos,boolean es,boolean se�al){
		this.nodo = nodo;
		this.pos = pos;
	    this.tipoNodo = tipo;
	    this.es = es;
	    this.se�al = se�al;
	 
	}
	public int hacer(IModelo modelo) {
		if (nodo.getSe�al().getTipo()=="semaforo")
			//modelo.setMapa(((Mapa)modelo.getMapa().eliminar(nodo.getSe�al())));
		if(se�al==false) nodo.setSe�al(null);
	    if(es==false) nodo.setEs(null);
		// TODO Auto-generated method stub
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
