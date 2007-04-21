package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionInsertarIntervalo implements ActionListener {
	private Nodo nodo;
	private int tiempoInicial;
	private int tiempoFinal;
	private JFrame ventanaPadre;
	
	public AccionInsertarIntervalo(Nodo nodo, JFrame ventanaPadre){
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		int tiempo1 = ((PanelNodo)ventanaPadre).dameTiempoInicialSemaforo();
		int tiempo2 = ((PanelNodo)ventanaPadre).dameTiempoFinalSemaforo();
		((Semaforo)nodo.getSe�al()).a�adirIntervalo(tiempo1,tiempo2 );
		//((Semaforo)nodo.getSe�al()).a�adirIntervalo(tiempoInicial, tiempoFinal);
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
	}

}
