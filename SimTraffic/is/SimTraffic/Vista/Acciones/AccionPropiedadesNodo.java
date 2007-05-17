package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;

public class AccionPropiedadesNodo extends AccionesPopUpMenu{
	public AccionPropiedadesNodo(IModelo modelo, IControlador controlador,
			PanelMapa panel_mapa) {
		super(modelo, controlador, panel_mapa);
	}

	public void actionPerformed(ActionEvent arg0) {
		Nodo nodoAux=this.buscarNodo(panel_mapa.getPosEx(), panel_mapa
				.getPosEy());
		if (nodoAux != null) {
			//JFrame ventanaNodo = new JFrame();
			PanelNodo panelNod = new PanelNodo(nodoAux, panel_mapa);
			panelNod.setTitle(Messages.getString("AccionPropiedadesNodo.0")); //$NON-NLS-1$
			panelNod.setBounds(80, 120, 500, 600);
			panelNod.setVisible(true);
		}
	}

}

