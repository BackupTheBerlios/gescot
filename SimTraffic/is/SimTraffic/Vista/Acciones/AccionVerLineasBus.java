package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HBuscarElemento;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

/**
 * 
 * @author usuario_local
 * En construccion
 */

public class AccionVerLineasBus extends AccionVer implements ActionListener{

	public AccionVerLineasBus(IControlador controlador, IModelo modelo,JFrame ventana,PanelMapa panel_mapa) {
		super(controlador, modelo, ventana, panel_mapa, Messages.getString("AccionVerLineasBus.0"), Messages.getString("AccionVerLineasBus.1"), //$NON-NLS-1$ //$NON-NLS-2$
		Messages.getString("AccionVerLineasBus.2")); //$NON-NLS-1$
	}
	
	protected void estableceOyenteBuscar(JButton buscar) {
		buscar.addActionListener(new accionBuscarLineasBus());
	}
	
	protected void poneElementosEnLista(){
		elementos = new List(); 
		Iterator<LineaBus> it = modelo.getMapa().getLineasAutobuses().iterator();
		while (it.hasNext()){
			ElementoMapa elem = (ElementoMapa)it.next();
			String nombreAux = elem.getNombre();
			if ((nombreAux!=null) && !nombreAux.equals(Messages.getString("AccionVerLineasBus.3"))) //$NON-NLS-1$
				elementos.add(nombreAux);
			else
				elementos.add(Messages.getString("AccionVerLineasBus.4")+elem.getID()); //$NON-NLS-1$
		}
	}
	
	private class accionBuscarLineasBus implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<LineaBus> lista=modelo.getMapa().getLineasAutobuses();
			if (!lista.isEmpty()&&elementos.getSelectedIndex()>=0){			
			LineaBus seleccionada = lista.get(elementos.getSelectedIndex());
			HBuscarElemento herramientaBuscar = new HBuscarElemento(Messages.getString("AccionVerLineasBus.5"),seleccionada.getNombre(),panel_mapa); //$NON-NLS-1$
			controlador.herramienta(herramientaBuscar);
			modelo.getMapa().limpiaSeleccion();
			panel_mapa.centrarEnPosicion(seleccionada.getTramos().get(0).getNodoInicial().getPos().getLat(),
					seleccionada.getTramos().get(0).getNodoInicial().getPos().getLon() );
			panel_mapa.setLinea(seleccionada);
			panel_mapa.repaint();
			}
		}
	}

	

	
}
