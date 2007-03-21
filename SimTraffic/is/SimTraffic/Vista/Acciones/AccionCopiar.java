/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
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
	 * inicial y final. Si estaban en el portapapeles, se a�ade el tramo a los tramos de
	 * los nodos. Si no, se a�aden al portapapeles.
	 * Despu�s se busca el nodo que tenga la posici�n m�nima para que sirva de referencia
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
		if (modelo.getMapa().getSeleccion()!=null) {
			modelo.getMapa().setPortapapeles(new Seleccion());
			for (int j=0; j<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); j++) {
				Tramo tramoMapa = modelo.getMapa().getSeleccion().
										getTramosSeleccionados().get(j);
								
				Nodo nodoInicialEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(tramoMapa.getNodoInicial());
				if (nodoInicialEnPortapapeles==null)
					nodoInicialEnPortapapeles = tramoMapa.getNodoInicial().pseudoClone();
				
				Nodo nodoFinalEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(tramoMapa.getNodoFinal());
				if (nodoFinalEnPortapapeles==null)
					nodoFinalEnPortapapeles = tramoMapa.getNodoFinal().pseudoClone();
				
				modelo.getMapa().getPortapapeles().a�adirNodo(nodoInicialEnPortapapeles);
				modelo.getMapa().getPortapapeles().a�adirNodo(nodoFinalEnPortapapeles);
				
				Tramo tramoPortapapeles = tramoMapa.pseudoClone(nodoInicialEnPortapapeles,nodoFinalEnPortapapeles);
				modelo.getMapa().getPortapapeles().a�adirTramo(tramoPortapapeles);
				nodoInicialEnPortapapeles.a�adirTramo(tramoPortapapeles);
				nodoFinalEnPortapapeles.a�adirTramo(tramoPortapapeles);									
			}
			for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
				Nodo nodoPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoTemp);
				if (nodoPortapapeles==null) {
					nodoPortapapeles = nodoTemp.pseudoClone();
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoPortapapeles);
				}
			}
		}
		Posicion posMinima= new Posicion(Double.MAX_VALUE,Double.MAX_VALUE);
		for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size();i++){
			Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
			if (nodoTemp.getPos().getLon()<=posMinima.getLon()) {
				if (nodoTemp.getPos().getLat()<=posMinima.getLat()) {
					posMinima = nodoTemp.getPos();
					modelo.getMapa().setNodoReferenciaPortapapeles(nodoTemp);
				}
			}
		}

	}

}
