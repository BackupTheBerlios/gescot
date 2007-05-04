package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.BarrasHerramientas.BarraSuperior;

/**
 * @author Grupo ISTrafico
 *
 */
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
	}

	/**
	 * @roseuid 45C1DF84033B
	 */
	public void mostrar() {
		if (controlador != null) {
			ventana = new Ventana(modelo, controlador);
			ventana.setVisible(true);
		}
	}
	
	public void actualizar() {
		((BarraSuperior)ventana.getBarraArriba()).setTiempo(modelo.getSimulacion().getTiempo()*1000);
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
}
