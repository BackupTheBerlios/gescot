package is.SimTraffic.Herramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import java.io.File;

import javax.swing.JFileChooser;

import is.SimTraffic.Herramientas.CargarMapa.*;
import is.SimTraffic.Mapa.Mapa;

public class HCargarMapa implements IHerramienta {
	IControlador controlador;

	/**
	 * @param controlador
	 * @roseuid 45B8B3A70182
	 */
	public HCargarMapa(IControlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * @param archivo
	 * @roseuid 45B8B1430105
	 */
	/*
	 * public HCargarMapa(File archivo) {
	 *  }
	 */

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
			} catch (Exception e) {
				System.out.println("Error al leer archivo");
				e.printStackTrace();
			}
			// if (mapaNuevo==null)
			// System.out.println("Mapa vacío?");
			modelo.setMapa(mapaNuevo);
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
}
