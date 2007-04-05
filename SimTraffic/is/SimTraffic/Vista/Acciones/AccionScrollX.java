package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;

/**
 * Action listener utilizado sobre el scroll vertical.
 * <p>
 * Este action listener implenta accionadores para cuando se hace click en los
 * botones con flechas y ejecuta el thread correspondiente para cuando se
 * arrastra el scroll.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionScrollX implements AdjustmentListener, MouseListener {

	private PanelMapa panel;

	AuxScrollX thread;

	public AccionScrollX(PanelMapa panel) {
		this.panel = panel;
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		JScrollBar barra = (JScrollBar) e.getSource();
		if (!barra.getValueIsAdjusting()) {
			int valor = barra.getValue();
			if (valor >= 10)
				valor = 11;
			else
				valor = 9;
			panel.cambiaPosX((valor - 10) * 8);
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
		thread = new AuxScrollX(barra, panel);
		thread.start();
	}

	public void mouseReleased(MouseEvent arg0) {
		((JScrollBar) arg0.getComponent()).setValue(10);
		thread.terminar();
		thread = null;
	}

}
