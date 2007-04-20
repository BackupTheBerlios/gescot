package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionCambiarTiempoTotalSem implements ActionListener {
	private Nodo nodo;
	private int nuevoTiempoTotal;
	private JFrame ventanaPadre;
	
	public AccionCambiarTiempoTotalSem(Nodo nodo, int nuevoTiempoTotal, JFrame ventanaPadre) {
		this.nodo = nodo;
		this.nuevoTiempoTotal = nuevoTiempoTotal;
		this.ventanaPadre = ventanaPadre;
	}

	public void actionPerformed(ActionEvent arg0) {
		((Semaforo)nodo.getSe�al()).setTiempoTotal(nuevoTiempoTotal);
		((Semaforo)nodo.getSe�al()).getListaIntervalos().clear();
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
	}

}
