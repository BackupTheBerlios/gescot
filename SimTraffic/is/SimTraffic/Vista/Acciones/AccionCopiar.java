/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HCopiar;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Grupo ISTrafico
 *
 */
public class AccionCopiar implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	
	IModelo modelo;
	IControlador controlador;
	PanelMapa panel;
	
	/**
	 * Constructora de Copiar.
	 * Se recorren todos los tramos de la seleccion. Para cada tramo, se miran sus nodos
	 * inicial y final. Si estaban en el portapapeles, se añade el tramo a los tramos de
	 * los nodos. Si no, se añaden al portapapeles.
	 * Después se busca el nodo que tenga la posición mínima para que sirva de referencia
	 * a la hora de pegar el contenido del portapapeles.
	 * @param modelo
	 * @param controlador
	 * @param panel
	 */
	
	public AccionCopiar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		panel.setFocusable(true);
		HCopiar herramientaCopiar = new HCopiar (modelo.getMapa().getSeleccion().getNodosSeleccionados(),
				modelo.getMapa().getSeleccion().getTramosSeleccionados());
		controlador.herramienta(herramientaCopiar);
		
		
	}

}
