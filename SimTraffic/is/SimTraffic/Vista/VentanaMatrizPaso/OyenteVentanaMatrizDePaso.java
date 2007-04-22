package is.SimTraffic.Vista.VentanaMatrizPaso;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class OyenteVentanaMatrizDePaso implements WindowListener {
	JFrame ventanaPadre;
	
	public OyenteVentanaMatrizDePaso(JFrame ventanaPadre) {
		this.ventanaPadre = ventanaPadre;
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent arg0) {
		ventanaPadre.setVisible(true);

	}

	public void windowClosed(WindowEvent arg0) {
		

	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowActivated(WindowEvent arg0) {

	}

	public void windowDeactivated(WindowEvent arg0) {

	}

}
