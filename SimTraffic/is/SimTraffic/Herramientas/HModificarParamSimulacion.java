/**
 * 
 */
package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Simulacion.ParametrosSimulacion;

/**
 * @author usuario_local
 *
 */
public class HModificarParamSimulacion implements IHerramienta {
		
	private ParametrosSimulacion parametros;
	private ParametrosSimulacion parametrosGuardados;

	public HModificarParamSimulacion(ParametrosSimulacion nuevosParametros) {
		this.parametros=nuevosParametros;
		this.parametrosGuardados=null;
	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Herramientas.IHerramienta#hacer(is.SimTraffic.IModelo)
	 */
	public int hacer(IModelo modelo) {
		// TODO Auto-generated method stub
		this.parametrosGuardados=modelo.getSimulacion().getParametros();
		return modelo.getSimulacion().modificaParametros(this.parametros);		
	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Herramientas.IHerramienta#deshacer(is.SimTraffic.IModelo)
	 */
	public int deshacer(IModelo modelo) {
		this.parametros=this.parametrosGuardados;
		return modelo.getSimulacion().modificaParametros(this.parametros);		
	}

}
