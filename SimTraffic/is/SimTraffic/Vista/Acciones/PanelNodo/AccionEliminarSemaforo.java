package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionEliminarSemaforo implements ActionListener {

	private Nodo nodo;
	private JFrame ventanaPadre;
	
	public AccionEliminarSemaforo(Nodo nodo, JFrame ventanaPadre) {
		super();
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
	}

	public void actionPerformed(ActionEvent e) {
		nodo.setSeñal(null);
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
	}

}
