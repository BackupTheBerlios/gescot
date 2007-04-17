package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende a la escucha de rat�n. Se encarga de escuchar a 
 * la acci�n copiar, que copia una selecci�n de elementos al portapapeles 
 * @author Grupo ISTrafico
 *
 */
public class MLCopiar extends EscuchaRaton{

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
	
	public MLCopiar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		//drag = false;
		panel.setFocusable(true);		
		if (modelo.getMapa().getSeleccion()!=null) {
			for (int j=0; j<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); j++) {
				Tramo tramoMapa = modelo.getMapa().getSeleccion().
				getTramosSeleccionados().get(j);
				Nodo nodoInicial = new Nodo(tramoMapa.getNodoInicial().getPos().clone());
				Nodo nodoFinal = new Nodo (tramoMapa.getNodoFinal().getPos().clone());
				Tramo tramoPortapapeles = new Tramo(nodoInicial,nodoFinal);
				modelo.getMapa().getPortapapeles().a�adirTramo(tramoPortapapeles);
				nodoInicial.a�adirTramo(tramoPortapapeles);
				nodoFinal.a�adirTramo(tramoPortapapeles);
				Nodo nodoInicialEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoInicial);
				if (nodoInicialEnPortapapeles!=null)
					nodoInicialEnPortapapeles.a�adirTramo(tramoPortapapeles);
				else
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoInicial);
				Nodo nodoFinalEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoFinal);
				if (nodoFinalEnPortapapeles!=null)
					nodoFinalEnPortapapeles.a�adirTramo(tramoPortapapeles);
				else
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoFinal);					
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
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getAyuda() {
		return "";
	}
	
	

}
