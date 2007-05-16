package is.SimTraffic.Herramientas;


import is.SimTraffic.Herramientas.DescargarMapa.BoundingBoxDownloader;

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

	
	public HDescargarMapa(IControlador controlador,double minlat, double minlon, double maxlat, double maxlon) {
		this.controlador = controlador;
		//BoudingBoxDownloader bbdAux = new BoundingBoxDownloader(minlat, minlon, maxlat, maxlon)

	}
	
	public int hacer(IModelo modelo) {

		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
