package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelesSimulacion.PanelVehiculos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionComenzarSimulacion implements ActionListener {

	IControlador controlador;

	IModelo modelo;

	public AccionComenzarSimulacion(IControlador controlador, IModelo modelo) {
		super();
		// TODO Auto-generated constructor stub
		this.controlador = controlador;
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame ventanaNodo = new PanelVehiculos(controlador); 
		
		// Donde va a aparecer la ventana son las dos primera componentes
		// El tamaño de la ventana son las dos ultimas componentes
		ventanaNodo.setBounds(80, 120, 425, 400);
		ventanaNodo.setVisible(true);
	}

}
