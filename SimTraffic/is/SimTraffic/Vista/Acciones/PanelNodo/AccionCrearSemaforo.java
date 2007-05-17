package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionCrearSemaforo implements ActionListener {
	private Nodo nodo;
	private JFrame ventanaPadre;
	
	public AccionCrearSemaforo(Nodo nodo, JFrame ventanaPadre){
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Se�al semaforo = new Semaforo(nodo, nodo.getTramos());
		nodo.setSe�al(semaforo);
		//Actualizamos la vista de los sem�foros.
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
		
	}

}
