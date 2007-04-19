package is.SimTraffic.Herramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import java.io.File;

import javax.swing.JFileChooser;

import is.SimTraffic.Herramientas.CargarMapa.*;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Utils.Tiempo;
import is.SimTraffic.Vista.PanelMapa;

/**
 * Herramienta para cargar un mapa desde un fichero osm.
 * <p>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class HCargarMapa implements IHerramienta {
	/**
	 * 
	 */
	IControlador controlador;

	/**
	 * 
	 */
	PanelMapa panel;

	/**
	 * @param controlador
	 * @roseuid 45B8B3A70182
	 */
	public HCargarMapa(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}

	/**
	 * @param modelo
	 * @return int
	 * @roseuid 45C1E5740203
	 */
	public int hacer(IModelo modelo) {
		Mapa mapaNuevo = null;
		JFileChooser fc = new JFileChooser();
		String[] ext = new String[] { "osm" };
		fc.addChoosableFileFilter(new ExtFilter(ext, "Mapa OSM (*.osm)"));
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// Faltaría definir comportamiento ante fallos.
			try {
				mapaNuevo = CargadorMapa.cargar(file.getAbsolutePath());
				if (mapaNuevo.getNodos().get(0) != null) {
					panel.centrarEnPosicion(mapaNuevo.getNodos().get(0)
							.getPos().getLat(), mapaNuevo.getNodos().get(0)
							.getPos().getLon());
				}
				modelo.setMapa(mapaNuevo);

			} catch (Exception e) {
				System.out.println("Error al leer archivo");
				e.printStackTrace();
			}
			// if (mapaNuevo==null)
			// System.out.println("Mapa vacío?");
		}
		return 0;
	}

	/**
	 * @param modelo
	 * @return int
	 * @roseuid 45C1E5740270
	 */
	public int deshacer(IModelo modelo) {
		return 0;
	}

	public String toString() {
		return Tiempo.Hora() + " - " + "Mapa Cargado";
	}
}
