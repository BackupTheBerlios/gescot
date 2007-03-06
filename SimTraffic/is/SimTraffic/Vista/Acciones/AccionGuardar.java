/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HGuardarMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Grupo ISTrafico
 *
 */
public class AccionGuardar implements ActionListener {

	IControlador controlador;
	
	public AccionGuardar(IControlador controlador) {
		this.controlador = controlador;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(controlador!= null)
			controlador.herramienta(new HGuardarMapa());
	}

}
