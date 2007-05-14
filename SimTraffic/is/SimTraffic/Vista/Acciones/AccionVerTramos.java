package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AccionVerTramos extends AccionVer {

	public AccionVerTramos(IControlador controlador, IModelo modelo, JFrame ventana, PanelMapa panel_mapa, String direccionIcono, String tituloVer, String nombreElemento) {
		super(controlador, modelo, ventana, panel_mapa, "is\\SimTraffic\\Vista\\Imagenes\\añadir_tramo2.png", "Tramos existentes",
				"Tramo");
	}
	
	protected void estableceOyenteBuscar(JButton buscar) {
		buscar.addActionListener(new accionBuscarTramos());
	}

	protected void poneElementosEnLista(){
		elementos = new List(); 
		
		//Esta es la línea que cambia en cada AccionVer
		Iterator<Tramo> it = modelo.getMapa().getTramos().iterator();
		
		while (it.hasNext()){
			ElementoMapa elem = (ElementoMapa)it.next();
			String nombreAux = elem.getNombre();
			if ((nombreAux!=null) && !nombreAux.equals(""))
				elementos.add(nombreAux);
			else
				elementos.add("<Sin nombre> ID"+elem.getID());
		}
	}
	
	private class accionBuscarTramos implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Tramo> lista=modelo.getMapa().getTramos();
			if (!lista.isEmpty()){			
				Tramo seleccionado = lista.get(elementos.getSelectedIndex());
				//HBuscarElemento herramientaBuscar = new HBuscarElemento(nombreElemento,seleccionado.getNombre(),panel_mapa);
				//controlador.herramienta(herramientaBuscar);
				//Ver si esta ultima instrucción necesaria.
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().añadirTramo(seleccionado);
				panel_mapa.repaint();
			}
		}
	}

}
