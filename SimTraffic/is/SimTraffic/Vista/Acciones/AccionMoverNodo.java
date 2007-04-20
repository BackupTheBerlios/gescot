package is.SimTraffic.Vista.Acciones;

import java.awt.event.ActionEvent;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

public class AccionMoverNodo extends AccionesPopUpMenu{

	public AccionMoverNodo(IModelo modelo, IControlador controlador, PanelMapa panel_mapa) {
		super(modelo, controlador, panel_mapa);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Nodo nodoSeleccionado = buscarNodo(panel_mapa.getPosEx(), panel_mapa
				.getPosEy());
			if (modelo.getMapa().getSeleccion().getNodosSeleccionados().size()==0){
				modelo.getMapa().getSeleccion().getNodosSeleccionados().add(nodoSeleccionado);
			}
			
		}
}
