package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;

public interface IVista {

	/**
	 * @roseuid 45C1DF090280
	 */
	public void mostrar();

	public void actualizar();

	/**
	 * @param modelo
	 * @roseuid 45C1DFB203B8
	 */
	public void setModelo(IModelo modelo);
}
