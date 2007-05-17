package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Señales.Semaforo;
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
		Señal semaforo = new Semaforo(nodo, nodo.getTramos());
		nodo.setSeñal(semaforo);
		//Actualizamos la vista de los semáforos.
		((PanelNodo)ventanaPadre).creaPanelSemaforos();
		((PanelNodo)ventanaPadre).daFocoAPanelSemaforo();
		
	}

}
