package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AccionVerVias extends AccionVer {

	public AccionVerVias(IControlador controlador, IModelo modelo, JFrame ventana, PanelMapa panel_mapa, String direccionIcono, String tituloVer, String nombreElemento) {
		super(controlador, modelo, ventana, panel_mapa, "añadir_via2.png", "Vías existentes",   //$NON-NLS-1$//$NON-NLS-2$
				"Vía"); //$NON-NLS-1$
	}
	
	protected void estableceOyenteBuscar(JButton buscar) {
		buscar.addActionListener(new accionBuscarVias());
	}

	protected void poneElementosEnLista(){
		elementos = new List(); 
		
		//Esta es la línea que cambia en cada AccionVer
		Iterator<Via> it = modelo.getMapa().getVias().iterator();
		
		while (it.hasNext()){
			ElementoMapa elem = (ElementoMapa)it.next();
			String nombreAux = elem.getNombre();
			if ((nombreAux!=null) && !nombreAux.equals("")) //$NON-NLS-1$
				elementos.add(nombreAux);
			else
				elementos.add("<Sin nombre> ID"+elem.getID()); //$NON-NLS-1$
		}
	}
	
	private class accionBuscarVias implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Via> lista=modelo.getMapa().getVias();
			if (!lista.isEmpty()&&elementos.getSelectedIndex()>=0){			
				Via seleccionada = lista.get(elementos.getSelectedIndex());
				//HBuscarElemento herramientaBuscar = new HBuscarElemento(nombreElemento,seleccionado.getNombre(),panel_mapa);
				//controlador.herramienta(herramientaBuscar);
				modelo.getMapa().limpiaSeleccion();
				Iterator<Tramo> tramos = seleccionada.getTramos().iterator();
				
				//Añadimos cada uno de los tramos  
				while(tramos.hasNext()){
				   Tramo aux=tramos.next();
				   modelo.getMapa().getSeleccion().añadirTramo(aux);  
				 } 
				panel_mapa.centrarEnPosicion(seleccionada.getTramos().get(0).getNodoInicial().getPos().getLat(),
						seleccionada.getTramos().get(0).getNodoInicial().getPos().getLon() );
				panel_mapa.repaint();
			}
		}
	}

}
