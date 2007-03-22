/**
 * 
 */
package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
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
public class AccionCortar implements ActionListener {
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	IModelo modelo;
	IControlador controlador;
	PanelMapa panel;
	
	public AccionCortar (IModelo modelo, IControlador controlador, PanelMapa panel) {
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
				
				modelo.getMapa().getPortapapeles().aņadirNodo(nodoInicialEnPortapapeles);
				modelo.getMapa().getPortapapeles().aņadirNodo(nodoFinalEnPortapapeles);
				
				Tramo tramoPortapapeles = tramoMapa.pseudoClone(nodoInicialEnPortapapeles,nodoFinalEnPortapapeles);
				modelo.getMapa().getPortapapeles().aņadirTramo(tramoPortapapeles);
				nodoInicialEnPortapapeles.aņadirTramo(tramoPortapapeles);
				nodoFinalEnPortapapeles.aņadirTramo(tramoPortapapeles);									
			}
			for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
				Nodo nodoPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoTemp);
				if (nodoPortapapeles==null) {
					nodoPortapapeles = nodoTemp.pseudoClone();
					modelo.getMapa().getPortapapeles().aņadirNodo(nodoPortapapeles);
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
			
//			for(int i=0; i<modelo.getMapa().getPortapapeles().)
			
			
			/*HEliminarSeleccion herramientaBorrar = new HEliminarSeleccion(modelo.getMapa().getPortapapeles().getNodosSeleccionados(),
					modelo.getMapa().getPortapapeles().getTramosSeleccionados());
			controlador.herramienta(herramientaBorrar);
			*/
			for (int i=0; i<modelo.getMapa().getTramos().size();i++) {
				Tramo tramoTemp = modelo.getMapa().getTramos().get(i);
				if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(tramoTemp)) {
					modelo.getMapa().getTramos().remove(i);
					i--;
				}
				
			}
			
			for (int i=0; i<modelo.getMapa().getNodos().size();i++) {
				Nodo nodoTemp = modelo.getMapa().getNodos().get(i);
				if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoTemp)) {
					modelo.getMapa().getNodos().remove(i);
					i--;
				}
				
				
			}
			modelo.getMapa().setSeleccion(new Seleccion());
			panel.setRecrear(true);
			panel.repaint();
		}
	}
	
}
