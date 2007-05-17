package is.SimTraffic.Vista.Acciones;

import java.awt.event.ActionEvent;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelTramo;

public class AccionPropiedadesTramo extends AccionesPopUpMenu{
	
	
	public AccionPropiedadesTramo(IModelo modelo, IControlador controlador,
			PanelMapa panel_mapa) {
		super(modelo, controlador, panel_mapa);
	}

	public void actionPerformed(ActionEvent arg0) {
		Tramo tramoAux=this.buscarTramo(panel_mapa.getPosEx(), panel_mapa
				.getPosEy());
		if (tramoAux != null) {
			//JFrame ventanaNodo = new JFrame();
			PanelTramo panelTramo = new PanelTramo(tramoAux, panel_mapa,controlador);
			panelTramo.setTitle(Messages.getString("AccionPropiedadesTramo.0")); //$NON-NLS-1$
			panelTramo.setBounds(80, 120, 500, 600);
			panelTramo.setVisible(true);
		}
	}

}
