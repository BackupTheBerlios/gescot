package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;

import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;

public class HEliminarVia implements IHerramienta {

	/**
	 * Tramo que se desea eliminar.
	 */
	private Via via;
	
	public HEliminarVia (){
		
	}
	
	/**
     * Constructor que obtiene la via que se quiere eliminar y lo almacena en la herramienta.
	 * @param tramo
	 */
	public HEliminarVia(Via via){
		this.via=via;
	}
	
	/**
	 * Aplica la herramienta sobre el modelo. En este caso, elimina la via especificada en el mapa.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	*/
	public int hacer(IModelo modelo) {
		modelo.getMapa().eliminar(via);
		return 0;
	}
	/**
	 * Deshace la aplicación realizada por la herramienta. En este caso, reinserta la via que fue 
	 * eliminado.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	@roseuid 45C315C50288
	*/
	public int deshacer(IModelo modelo) {
		modelo.getMapa().insertar(via);
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HEliminarVia.0")+Messages.getString("HEliminarVia.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public Via getLineaBus() {
		return via;
	}
	
}
