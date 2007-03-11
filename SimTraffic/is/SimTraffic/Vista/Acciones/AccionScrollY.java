package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;

public class AccionScrollY implements AdjustmentListener, MouseListener {

	private PanelMapa panel;
	
	AuxScrollY thread;
	
	public AccionScrollY(PanelMapa panel) {
		this.panel = panel;
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		JScrollBar barra = (JScrollBar) e.getSource();
		if (!barra.getValueIsAdjusting()) {
			int valor = barra.getValue();
			System.out.println(""+valor);
			if (valor >= 10) valor = 11;
			else valor = 9;
			panel.cambiaPosY((valor-10)*8);
			barra.setValue(valor);
		}
	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		JScrollBar barra = (JScrollBar) arg0.getComponent();
		thread = new AuxScrollY(barra, panel);
		thread.start();
	}

	public void mouseReleased(MouseEvent arg0) {
		((JScrollBar) arg0.getComponent()).setValue(10);
		thread.terminar();
		thread = null;
	}

}
