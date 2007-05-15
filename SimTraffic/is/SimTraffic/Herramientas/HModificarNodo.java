package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Utils.Tiempo;

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
	
	//Nodo auxiliar para poder deshacer los cambios
	Nodo nodoGuardado;
	
	
	public HModificarNodo(Nodo nodo,ITipoElemento  tipo,Posicion pos,boolean es,boolean se�al){
		this.nodo = nodo;
		this.pos = pos;
	    this.tipoNodo = tipo;
	    this.es = es;
	    this.se�al = se�al;
	 
	}
	
	/**
	 * 
	 */
	public int hacer(IModelo modelo) {
		
		/**
		 * Aqui guardamos el nodo anterior,para poder deshacer cualquier cambio
		 */
		nodoGuardado = nodo;
		
		// TODO verificar si hay que cambiar algo en la lista de se�ales
		
		/**
		 * Borramos la se�al o el punto de e/s
		 * (Ojo,las se�ales s�lo se eliminan de esta manera,para 
		 * modificarlas lo hacemos en la herramienta HModificaSe�al)
		 * 
		 */
		if(se�al==false) nodo.setSe�al(null);
	    if(es==false) nodo.setEs(null);
	    
	    /**
	     * Si cambiamos el tipo de nodo o la posicion,se lo aplicamos al nodo
	     */
	    if (tipoNodo!=nodo.getTipo()) 
	     nodo.setTipo(tipoNodo);
	    if (pos !=nodo.getPos())
	     nodo.setPos(pos);
		return 0;
	}

	/**
	 * 
	 */
	public int deshacer(IModelo modelo) {
		
		//Deshacemos el cambio restaurando los parametros con el anterior
		nodo = nodoGuardado;
		
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+ "Nodo modificado";
	}

	public boolean isEs() {
		return es;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public Posicion getPos() {
		return pos;
	}

	public boolean isSe�al() {
		return se�al;
	}

	public ITipoElemento getTipoNodo() {
		return tipoNodo;
	}
}
