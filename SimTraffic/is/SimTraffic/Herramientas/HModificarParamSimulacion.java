/**
 * 
 */
package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Simulacion.ParametrosSimulacion;

/**
 * @author Grupo ISTrafico
 *
 */
public class HModificarParamSimulacion implements IHerramienta {
		
	private ParametrosSimulacion parametros;
	private ParametrosSimulacion parametrosGuardados;

	public HModificarParamSimulacion(ParametrosSimulacion nuevosParametros) {
		this.parametros=nuevosParametros;
		this.parametrosGuardados=null;
	}

	/**
	 * 
	 */
	public int hacer(IModelo modelo) {
		this.parametrosGuardados=modelo.getSimulacion().getParametros();
		return modelo.getSimulacion().modificaParametros(this.parametros);		
	}

	/**
	 * 
	 */
	public int deshacer(IModelo modelo) {
		this.parametros=this.parametrosGuardados;
		return modelo.getSimulacion().modificaParametros(this.parametros);		
	}
	public String toString(){
		return "Evento-Solo es temporal";
	}
}
