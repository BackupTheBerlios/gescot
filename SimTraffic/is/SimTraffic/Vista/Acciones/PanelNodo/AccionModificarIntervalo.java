package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class AccionModificarIntervalo implements ActionListener {

	private Nodo nodo;
	
	private JFrame ventanaPadre;
	
	public AccionModificarIntervalo(Nodo nodo, JFrame ventanaPadre) {
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
	}

	public void actionPerformed(ActionEvent arg0) {
		int numeroBoton = Integer.parseInt(((JButton)arg0.getSource()).getName());
		int valorInicial = ((PanelNodo)ventanaPadre).dameTiempoInicialIntervalo(numeroBoton);
		int valorFinal = ((PanelNodo)ventanaPadre).dameTiempoFinalIntervalo(numeroBoton);
		((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numeroBoton).setTiempoInicial(valorInicial);
		((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numeroBoton).setTiempoFinal(valorFinal);
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
	}

}
