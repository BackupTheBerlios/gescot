package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.Vista.PanelNodo;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EscuchaCambioSpinner implements ChangeListener {

	int numeroIntervalo;
	PanelNodo panelPadre;
	
	public EscuchaCambioSpinner(int intervalo, PanelNodo padre) {
		super();
		numeroIntervalo = intervalo;
		panelPadre = padre;
	}

	public void stateChanged(ChangeEvent e) {
		panelPadre.getListaBotonesAplicarIntervalo().get(numeroIntervalo).setEnabled(true);

	}

}
