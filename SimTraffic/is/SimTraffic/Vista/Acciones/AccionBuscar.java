package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Vista.PanelBuscar;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionBuscar implements ActionListener {

	IControlador controlador;
	
	private PanelMapa panel;

	public AccionBuscar(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JFrame ventanaBuscar=new PanelBuscar(controlador,panel);
		ventanaBuscar.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
