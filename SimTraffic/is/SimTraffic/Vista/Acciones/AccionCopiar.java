/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
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
		if (modelo.getMapa().getSeleccion()!=null) {
			for (int j=0; j<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); j++) {
				Tramo tramoMapa = modelo.getMapa().getSeleccion().
				getTramosSeleccionados().get(j);
				Nodo nodoInicial = new Nodo(tramoMapa.getNodoInicial().getPos().clone());
				Nodo nodoFinal = new Nodo (tramoMapa.getNodoFinal().getPos().clone());
				Tramo tramoPortapapeles = new Tramo(nodoInicial,nodoFinal);
				modelo.getMapa().getPortapapeles().añadirTramo(tramoPortapapeles);
				nodoInicial.añadirTramo(tramoPortapapeles);
				nodoFinal.añadirTramo(tramoPortapapeles);
				Nodo nodoInicialEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoInicial);
				if (nodoInicialEnPortapapeles!=null)
					nodoInicialEnPortapapeles.añadirTramo(tramoPortapapeles);
				else
					modelo.getMapa().getPortapapeles().añadirNodo(nodoInicial);
				Nodo nodoFinalEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoFinal);
				if (nodoFinalEnPortapapeles!=null)
					nodoFinalEnPortapapeles.añadirTramo(tramoPortapapeles);
				else
					modelo.getMapa().getPortapapeles().añadirNodo(nodoFinal);					
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
