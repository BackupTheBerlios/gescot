package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Vista.PanelBuscar;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Clase que extiende ActionListener para el boton que permite ir a una posición
 * o nodo del mapa.
 * <p>
 * El constructor de esta clase tomara el controlador y el panel mapa, y al ejecutarse
 * la acción mostrará una ventana donde podrá determianrse la posición que se quiere ocupar
 * o escribir el nombre de uno nodo al que se quiere ir.<br>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionBuscar implements ActionListener {

	/**
	 * Interface del controlador del MVC
	 */
	private IControlador controlador;

	/**
	 * PanelMapa que representa el mapa en la pantalla
	 */
	private PanelMapa panel;

	public AccionBuscar(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent arg0) {
		Cursor c;
		c = new Cursor(0);
		panel.setCursor(c);
		JFrame ventanaBuscar = new PanelBuscar(controlador, panel);
		//ventanaBuscar.setLocation(200,200);
		//ventanaBuscar.setSize(400,200);
		////this.setResizable(false);
		//ventanaBuscar.setTitle("Buscar...");
		ventanaBuscar.setVisible(true);
	}
}
