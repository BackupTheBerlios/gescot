package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.PanelesSimulacion.PanelVehiculos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos de
 * men� mostrar la ventana correspondiente al inicio de la simulaci�n y comenzar
 * con �sta.
 * <p>
 * Esta clase tomar� en su constructro el controlador como par�metro y lo
 * almacenar�, para que cuando se ejecute la acci�n se pueda crear el
 * PanelVehiculos que permitir� decidir los tipos de vehiculos de la simulaci�n
 * y comenzar con �sta.
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

	private IModelo modelo;
	
	private ParametrosSimulacion param;
	
	private Ventana ventana;	

	public AccionComenzarSimulacion(Ventana ventana, IControlador controlador,IModelo modelo) {
		super();
		this.controlador = controlador;
		this.modelo=modelo;
		this.param =modelo.getSimulacion().getParam();
		this.ventana = ventana;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if(!modelo.getSimulacion().estaActiva()){
		JFrame ventanaNodo = new PanelVehiculos(ventana,controlador, param);

		// Donde va a aparecer la ventana son las dos primera componentes
		// El tama�o de la ventana son las dos ultimas componentes
		ventanaNodo.setBounds(80, 120, 425, 440);
		ventanaNodo.setVisible(true);
		}
		else
		   modelo.getSimulacion().comenzar(null);	
	}

}
