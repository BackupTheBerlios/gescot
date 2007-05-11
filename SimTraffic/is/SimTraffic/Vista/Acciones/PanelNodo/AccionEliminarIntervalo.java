package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionEliminarIntervalo implements ActionListener {

	private Nodo nodo;
	private JFrame ventanaPadre;
	private int intervalo;
	
	public AccionEliminarIntervalo(Nodo nodo, JFrame ventanaPadre, int intervalo) {
		super();
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
		this.intervalo = intervalo;
	}
	
	public void actionPerformed(ActionEvent e) {
		((Semaforo)nodo.getSeñal()).getListaIntervalos().remove(intervalo);
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
		

	}

}
