package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import is.SimTraffic.Vista.PanelesSimulacion.PanelVehiculos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos de
 * menú mostrar la ventana correspondiente al inicio de la simulación y comenzar
 * con ésta.
 * <p>
 * Esta clase tomará en su constructro el controlador como parámetro y lo
 * almacenará, para que cuando se ejecute la acción se pueda crear el
 * PanelVehiculos que permitirá decidir los tipos de vehiculos de la simulación
 * y comenzar con ésta.
 * <p>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionComenzarSimulacion implements ActionListener {

	/**
	 * Interfaz del controlador en el MVC
	 */
	private IControlador controlador;

	private ParametrosSimulacion param;
	
	public AccionComenzarSimulacion(IControlador controlador, ParametrosSimulacion param) {
		super();
		this.controlador = controlador;
		this.param = param;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		JFrame ventanaNodo = new PanelVehiculos(controlador, param);

		// Donde va a aparecer la ventana son las dos primera componentes
		// El tamaño de la ventana son las dos ultimas componentes
		ventanaNodo.setBounds(80, 120, 425, 440);
		ventanaNodo.setVisible(true);
	}

}
