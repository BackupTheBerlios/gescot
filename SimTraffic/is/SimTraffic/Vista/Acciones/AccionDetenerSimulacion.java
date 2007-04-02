package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HDetener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionDetenerSimulacion  implements ActionListener {

	IControlador controlador;

	IModelo modelo;

	public AccionDetenerSimulacion(IControlador controlador, IModelo modelo) {
		super();
		this.controlador = controlador;
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent arg0) {
		controlador.herramienta(new HDetener());
	}

}