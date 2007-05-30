package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Utils.Tiempo;

public class HEliminarLineaBus implements IHerramienta {

	/**
	 * Tramo que se desea eliminar.
	 */
	private LineaBus linea;
	
	public HEliminarLineaBus (){
		
	}
	
	/**
     * Constructor que obtiene la linea de bus que se quiere eliminar y lo almacena en la herramienta.
	 * @param tramo
	 */
	public HEliminarLineaBus(LineaBus linea){
		this.linea=linea;
	}
	
	/**
	 * Aplica la herramienta sobre el modelo. En este caso, elimina la linea de bus especificada en el mapa.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	*/
	public int hacer(IModelo modelo) {
		modelo.getMapa().eliminar(linea);
		return 0;
	}
	/**
	 * Deshace la aplicación realizada por la herramienta. En este caso, reinserta la linea de bus que fue 
	 * eliminado.
	@param modelo Modelo que contiene toda la informacion de la aplicación.
	@return int Codigo de retorno. 0 indica que no hubo errores.
	@roseuid 45C315C50288
	*/
	public int deshacer(IModelo modelo) {
		modelo.getMapa().insertar(linea);
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HEliminarLineaBus.0")+Messages.getString("HEliminarLineaBus.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public LineaBus getLineaBus() {
		return linea;
	}
	
	
}
