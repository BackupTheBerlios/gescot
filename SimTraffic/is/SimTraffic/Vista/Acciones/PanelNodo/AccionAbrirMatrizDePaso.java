package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AccionAbrirMatrizDePaso implements ActionListener {
	private JFrame ventanaPadre;
	
	public AccionAbrirMatrizDePaso(JFrame ventanaPadre) {
		super();
		this.ventanaPadre = ventanaPadre;
	}

	public void actionPerformed(ActionEvent arg0) {
		int numIntervalo = Integer.parseInt(((JButton)arg0.getSource()).getName());
		((PanelNodo)ventanaPadre).abrirVentanaMatrizDePaso(numIntervalo);
	}

}
