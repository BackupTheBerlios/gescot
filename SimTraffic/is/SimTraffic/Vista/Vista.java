package is.SimTraffic.Vista;

import java.awt.Image;
import java.awt.Toolkit;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;

public class Vista implements IVista {
	/**
	 * Mantiene la instancia actual del modelo, para saber que mostrar en
	 * pantalla
	 */
	private IModelo modelo;

	/**
	 * Mantiene la instancia actual del controlador, para el paso de
	 * herramientas
	 */
	private IControlador controlador;

	/**
	 * Comonente principal de la GUI
	 */
	private Ventana ventana;

	/**
	 * @roseuid 45B8B3A90088
	 */
	public Vista(IModelo modelo) {
		setModelo(modelo);
		ventana = new Ventana(modelo);
	}

	/**
	 * @roseuid 45C1DF84033B
	 */
	public void mostrar() {
		ventana.setVisible(true);
	}

	public void actualizar() {
		ventana.repaint();
	}

	/**
	 * @param modelo
	 * @roseuid 45C1E04500CA
	 */
	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(IControlador controlador) {
		this.controlador = controlador;
	}
	
	public Image dibujarMapa(){
		// implementacion en http://josm.eigenheimstrasse.de/svn/src/org/openstreetmap/josm/
		
		Mapa mapa = modelo.getMapa();
		Image imagen = Toolkit.getDefaultToolkit().createImage((byte[]) null);
		imagen.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		
		
		return imagen;
		
	}
}
