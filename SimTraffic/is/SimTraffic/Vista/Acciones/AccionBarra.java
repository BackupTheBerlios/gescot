package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

/**
 * Clase que extiende ActionListener para permitir a los botones mostrar u
 * ocultar una barra de herramientas.
 * <p>
 * Esta clase tomara la ventana donde se muestra la barra y la barra en si en su
 * constructor y se encargará de hacerla visible o invisible según exista o no.<br>
 * 
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionBarra implements ActionListener {

	/**
	 * Ventana donde se muestra la barra
	 */
	private Ventana panel;

	/**
	 * Barra que se debe mostrar en la ventana
	 */
	private JToolBar barra;

	public AccionBarra(Ventana ventana, JToolBar barra) {
		this.panel = ventana;
		this.barra = barra;
	}

	public void actionPerformed(ActionEvent e) {
		if (barra != null) {
			panel.mostrar(barra);
		} else {
			panel.ocultarBarraSuperior();
		}
	}

}
