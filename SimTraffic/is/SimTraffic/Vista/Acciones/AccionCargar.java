package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HCargarMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionCargar implements ActionListener {

	IControlador controlador;
	
	public AccionCargar(IControlador controlador) {
		this.controlador = controlador;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(controlador!= null) {
			controlador.herramienta(new HCargarMapa(controlador));
		}
	}

}
