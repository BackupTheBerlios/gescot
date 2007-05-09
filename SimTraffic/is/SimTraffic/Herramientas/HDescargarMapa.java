package is.SimTraffic.Herramientas;


import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import is.SimTraffic.Vista.PanelMapa;

/**
 * Herramienta para descargar un mapa desde un servidor osm.
 * <p>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class HDescargarMapa implements IHerramienta{

	IControlador controlador;

	/**
	 * 
	 */
	PanelMapa panel;
	
	public HDescargarMapa(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}
	
	public int hacer(IModelo modelo) {

		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
