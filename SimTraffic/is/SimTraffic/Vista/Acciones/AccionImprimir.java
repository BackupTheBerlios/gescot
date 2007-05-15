package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Utils.PrintUtilities;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionImprimir implements ActionListener {

	PanelMapa panel;
	
	public AccionImprimir (PanelMapa panel){
		this.panel=panel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		PrintUtilities.printComponent(panel);
	}

}
