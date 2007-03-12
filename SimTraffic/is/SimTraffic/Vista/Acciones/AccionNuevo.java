/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Grupo ISTrafico
 *
 */
public class AccionNuevo implements ActionListener {

	private IModelo modelo;
	
	public AccionNuevo(IModelo modelo) {
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent arg0) {
		modelo.setMapa(new Mapa());
	}

}
