package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Messages;
import is.SimTraffic.Utils.PrintUtilities;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionImprimir implements ActionListener {

	PanelMapa panel;
	
	public AccionImprimir (PanelMapa panel){
		this.panel=panel;
	}
	
	@SuppressWarnings("static-access") //$NON-NLS-1$
	public  void actionPerformed(ActionEvent arg0) {
		PrintUtilities impresion=new PrintUtilities(panel);
		impresion.printComponent(panel);
	}

}
