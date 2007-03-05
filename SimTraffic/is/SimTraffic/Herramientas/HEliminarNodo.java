package is.SimTraffic.Herramientas;

import is.SimTraffic.*;
import is.SimTraffic.Mapa.*;

/**
 * Herramienta que permite realizar/deshacer la operacion de eliminar un nodo
 * del mapa.
 * 
 * @author Grupo ISTrafico
 */
public class HEliminarNodo implements IHerramienta {
	/**
	 * Nodo que se desea eliminar.
	 */
	private Nodo nodo;

	/**
	 * @roseuid 45C3154D02B7
	 */
	public HEliminarNodo() {

	}

	/**
	 * Constructor que obtiene el nodo que se quiere eliminar y lo almacena en
	 * la herramienta.
	 * 
	 * @param nodo
	 */
	public HEliminarNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	/**
	 * Aplica la herramienta sobre el modelo. En este caso, elimina el nodo
	 * especificado del mapa.
	 * 
	 * @param modelo
	 *            Modelo que contiene toda la informacion de la aplicación.
	 * @return int Codigo de retorno. 0 indica que no hubo errores.
	 * @roseuid 45C315C50259
	 */
	public int hacer(IModelo modelo) {
		if (modelo.getMapa().eliminar(nodo))
			return 0;
		else
			return -1; // codigo de error temporal, modificar por el apropiado
	}

	/**
	 * Deshace la aplicación realizada por la herramienta. En este caso,
	 * reinserta el nodo que fue eliminado.
	 * 
	 * @param modelo
	 *            Modelo que contiene toda la informacion de la aplicación.
	 * @return int Codigo de retorno. 0 indica que no hubo errores.
	 * @roseuid 45C315C50288
	 */
	public int deshacer(IModelo modelo) {
		modelo.getMapa().insertar(nodo);
		return 0;
	}
}
