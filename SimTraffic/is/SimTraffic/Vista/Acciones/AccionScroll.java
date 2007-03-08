package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class AccionScroll implements AdjustmentListener {

	private PanelMapa panel;
	
	public AccionScroll(PanelMapa panel) {
		this.panel = panel;
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
		panel.repaint();
	}

}
