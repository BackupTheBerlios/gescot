package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AccionVerNodos extends AccionVer {

	public AccionVerNodos(IControlador controlador, IModelo modelo, JFrame ventana, PanelMapa panel_mapa, String direccionIcono, String tituloVer, String nombreElemento) {
		super(controlador, modelo, ventana, panel_mapa, Messages.getString("AccionVerNodos.0"), Messages.getString("AccionVerNodos.1"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("AccionVerNodos.2")); //$NON-NLS-1$
	}
	
	protected void estableceOyenteBuscar(JButton buscar) {
		buscar.addActionListener(new accionBuscarNodos());
	}

	protected void poneElementosEnLista(){
		elementos = new List(); 
		
		//Esta es la l�nea que cambia en cada AccionVer
		Iterator<Nodo> it = modelo.getMapa().getNodos().iterator();
		
		while (it.hasNext()){
			ElementoMapa elem = (ElementoMapa)it.next();
			String nombreAux = elem.getNombre();
			if ((nombreAux!=null) && !nombreAux.equals(Messages.getString("AccionVerNodos.3"))) //$NON-NLS-1$
				elementos.add(nombreAux);
			else
				elementos.add(Messages.getString("AccionVerNodos.4")+elem.getID()); //$NON-NLS-1$
		}
	}
	
	private class accionBuscarNodos implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Nodo> lista=modelo.getMapa().getNodos();
			if (!lista.isEmpty()&&elementos.getSelectedIndex()>=0){			
				Nodo seleccionado = lista.get(elementos.getSelectedIndex());
				//HBuscarElemento herramientaBuscar = new HBuscarElemento(nombreElemento,seleccionado.getNombre(),panel_mapa);
				//controlador.herramienta(herramientaBuscar);
				//Ver si esta ultima instrucci�n necesaria.
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().a�adirNodo(seleccionado);
				panel_mapa.centrarEnPosicion(seleccionado.getPos().getLat(),seleccionado.getPos().getLon());
				//panel_mapa.repaint();
			}
		}
	}
}
