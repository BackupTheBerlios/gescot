package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;

public class AccionScroll implements AdjustmentListener {

	private PanelMapa panel;
	
	public AccionScroll(PanelMapa panel) {
		this.panel = panel;
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		panel.repaint();
		JScrollBar barra = (JScrollBar) e.getSource();
		if(barra.getValue() <= barra.getMinimum() + 4)
			barra.setMinimum(barra.getMinimum() - 5);
		if(barra.getValue() >= barra.getMaximum() - 10)
			barra.setMaximum(barra.getMaximum() + 5);
	}

}
