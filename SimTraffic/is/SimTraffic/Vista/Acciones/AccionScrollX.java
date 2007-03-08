package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;

public class AccionScrollX implements AdjustmentListener, MouseListener {

	private PanelMapa panel;
	
	Thread thread;
	
	public AccionScrollX(PanelMapa panel) {
		this.panel = panel;
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		JScrollBar barra = (JScrollBar) e.getSource();
		if (!barra.getValueIsAdjusting()) {
			panel.cambiaPosX((barra.getValue()-10)*10);
			barra.setValue(10);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		JScrollBar barra = (JScrollBar) arg0.getComponent();
		panel.cambiaPosX((barra.getValue()-10)*100);
		barra.setValue(10);
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		JScrollBar barra = (JScrollBar) arg0.getComponent();
		thread = new Thread(new AuxScrollX(barra, panel));
		thread.start();
	}

	public void mouseReleased(MouseEvent arg0) {
		((JScrollBar) arg0.getComponent()).setValue(10);
		thread.stop();
		thread = null;
	}

}
