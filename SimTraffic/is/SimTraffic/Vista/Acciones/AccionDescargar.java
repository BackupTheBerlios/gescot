package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;

import is.SimTraffic.Vista.PanelDescargar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * men� descargar un nuevo fichero de mapa desde un servidor OSM.
 * <p>
 * <p>
 * Esta clase tomara el controlador y el panel con la representaci�n del mapa
 * como par�metros en su construcutr y lo almacenar� para que cuando se ejecute
 * la acci�n poder llamar a la herramienta HCargarMapa encargada de
 * efectivamente cargar el mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionDescargar implements ActionListener{
	

	/**
	 * Interface del controlador en el MVC
	 */
	private IControlador controlador;



	public AccionDescargar(IControlador controlador) {
		this.controlador = controlador;

	}

	public void actionPerformed(ActionEvent arg0) {
		
		JFrame ventanaDescargar = new PanelDescargar(controlador);


		
		/*if (controlador != null) {
			controlador.herramienta(new HDescargarMapa(controlador, panel));
			panel.repaint();
			panel.recrear();
		}*/
	}

}
