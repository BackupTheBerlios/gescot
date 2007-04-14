package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.event.ActionEvent;

public class AccionEliminarNodo extends AccionesPopUpMenu {

		public AccionEliminarNodo(IModelo modelo, IControlador controlador, PanelMapa panel_mapa) {
			super(modelo,controlador,panel_mapa);
		}

		public void actionPerformed(ActionEvent arg0) {
			Nodo seleccionado = buscarNodo(panel_mapa.getPosEx(), panel_mapa.getPosEy());
			if(seleccionado != null){
					panel_mapa.getMenuEmergente().setVisible(false);
					controlador.herramienta(new HEliminarNodo(seleccionado));
					panel_mapa.sugerir(null);
					panel_mapa.recrearMapa();
					panel_mapa.repaint();
			}
	}

}
