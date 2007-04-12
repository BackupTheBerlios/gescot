package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class HModificarNodo implements IHerramienta {

	Nodo nodo;
	ITipoElemento tipoNodo;
	Posicion pos;
	
	/**
	 * Estos booleanos nos permiten modificar el nodo cuando queremos
	 * borrar el nodo como punto de entrada salida o la señal que tiene en el.
	 */
	boolean es;
	boolean señal;
	
	//Nodo auxiliar para poder deshacer los cambios
	Nodo nodoGuardado;
	
	
	public HModificarNodo(Nodo nodo,ITipoElemento  tipo,Posicion pos,boolean es,boolean señal){
		this.nodo = nodo;
		this.pos = pos;
	    this.tipoNodo = tipo;
	    this.es = es;
	    this.señal = señal;
	 
	}
	
	/**
	 * 
	 */
	public int hacer(IModelo modelo) {
		
		/**
		 * Aqui guardamos el nodo anterior,para poder deshacer cualquier cambio
		 */
		nodoGuardado = nodo;
		
		/**
		 * En caso de que quitemos una señal de semáforo del nodo,esta linea lo comprueba
		 * y si es asi lo borra de la lista de semaforos que mantiene el modelo
		 */
		if (nodo.getSeñal().getTipo()=="semaforo")
			modelo.getMapa().eliminar(nodo.getSeñal());
		/**
		 * Borramos la señal o el punto de e/s
		 * (Ojo,las señales sólo se eliminan de esta manera,para 
		 * modificarlas lo hacemos en la herramienta HModificaSeñal)
		 * 
		 */
		if(señal==false) nodo.setSeñal(null);
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
		return "Evento-Solo es temporal";
	}
}
