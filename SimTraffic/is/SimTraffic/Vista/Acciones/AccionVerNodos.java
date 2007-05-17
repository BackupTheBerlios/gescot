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
		super(controlador, modelo, ventana, panel_mapa, "añadir_nodo2.png", "Nodos existentes",  //$NON-NLS-2$
				"Nodo"); //$NON-NLS-1$
	}
	
	protected void estableceOyenteBuscar(JButton buscar) {
		buscar.addActionListener(new accionBuscarNodos());
	}

	protected void poneElementosEnLista(){
		elementos = new List(); 
		
		//Esta es la línea que cambia en cada AccionVer
		Iterator<Nodo> it = modelo.getMapa().getNodos().iterator();
		
		while (it.hasNext()){
			ElementoMapa elem = (ElementoMapa)it.next();
			String nombreAux = elem.getNombre();
			if ((nombreAux!=null) && !nombreAux.equals("")) //$NON-NLS-1$
				elementos.add(nombreAux);
			else
				elementos.add("<Sin nombre> ID"+elem.getID()); //$NON-NLS-1$
		}
	}
	
	private class accionBuscarNodos implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Nodo> lista=modelo.getMapa().getNodos();
			if (!lista.isEmpty()&&elementos.getSelectedIndex()>=0){			
				Nodo seleccionado = lista.get(elementos.getSelectedIndex());
				//HBuscarElemento herramientaBuscar = new HBuscarElemento(nombreElemento,seleccionado.getNombre(),panel_mapa);
				//controlador.herramienta(herramientaBuscar);
				//Ver si esta ultima instrucción necesaria.
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().añadirNodo(seleccionado);
				panel_mapa.centrarEnPosicion(seleccionado.getPos().getLat(),seleccionado.getPos().getLon());
				//panel_mapa.repaint();
			}
		}
	}
}
