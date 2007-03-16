package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.PanelVehiculos;

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
		JFrame ventanaNodo = new JFrame();
		
		is.SimTraffic.Vista.PanelVehiculos panelNod = new is.SimTraffic.Vista.PanelVehiculos();
		
		ventanaNodo.setContentPane(panelNod);
		ventanaNodo.setTitle("Tipos de Veh�culos");
		//ventanaNodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Donde va a aparecer la ventana son las dos primera componentes
		// El tama�o de la ventana son las dos ultimas componentes
		ventanaNodo.setBounds(80,120,425,400);
		ventanaNodo.setVisible(true);	
	}
	
	
}
