package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccionCargar implements ActionListener {

	IControlador controlador;
	PanelMapa panel;
	
	public AccionCargar(IControlador controlador,PanelMapa panel) {
		this.controlador = controlador;
		this.panel= panel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(controlador!= null) {
			controlador.herramienta(new HCargarMapa(controlador, panel));
	     panel.repaint();
	     panel.recrear();
		}
	}

}
