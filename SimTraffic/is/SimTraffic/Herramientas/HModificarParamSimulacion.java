/**
 * 
 */
package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import is.SimTraffic.Utils.Tiempo;

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
		this.parametrosGuardados=modelo.getSimulacion().getParam();
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
		return Tiempo.Hora()+Messages.getString("HModificarParamSimulacion.0")+ Messages.getString("HModificarParamSimulacion.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public ParametrosSimulacion getParametros() {
		return parametros;
	}
}
