package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.*;

/**
 * Herramienta que permite realizar/deshacer la operacion de eliminar un 
 * tramo del mapa.
 * @author Grupo ISTrafico
 */
public class HEliminarTramo implements IHerramienta {

	/**
	 * Tramo que se desea eliminar.
	 */
	private Tramo tramo;
	
	public HEliminarTramo (){
		
	}
	
	/**
     * Constructor que obtiene el tramo que se quiere eliminar y lo almacena en la herramienta.
	 * @param tramo
	 */
	public HEliminarTramo(Tramo tramo){
		this.tramo = tramo;
	}
	
	/**
	 * Aplica la herramienta sobre el modelo. En este caso, elimina el tramo especificado del mapa.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	*/
	public int hacer(IModelo modelo) {
		modelo.getMapa().eliminar(tramo);
		return 0;
	}
	/**
	 * Deshace la aplicación realizada por la herramienta. En este caso, reinserta el tramo que fue 
	 * eliminado.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	@roseuid 45C315C50288
	*/
	public int deshacer(IModelo modelo) {
		modelo.getMapa().insertar(tramo);
		return 0;
	}

}
