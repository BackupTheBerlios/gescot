package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HCreaItinerarioEntreDosNodos;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;

public class MLEscuchaItinerario extends EscuchaRaton {

	public MLEscuchaItinerario(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		nodoOrigen = null;
		estado = 0;
	}

	/**
	 * Lista de estados
	 * 0: Estado antes de pulsarse el primer nodo.
	 * 1: Tras seleccionar el primer nodo, se queda esperando el segundo.
	 */
	private int estado;
	
	Nodo nodoOrigen;
	
	@Override
	public void mouseClicked(MouseEvent evento) {
		if (evento.getButton() == MouseEvent.BUTTON1) {
			Nodo seleccionado = (buscarNodo(evento.getX(), evento.getY()));
			if (seleccionado != null)
			{
				if (estado == 0) //Primer nodo seleccionado.
				{
					estado = 1;
					//Ver si necesario inicializar.
					//nodoOrigen = new Nodo(null);
					nodoOrigen =  seleccionado;
					modelo.getMapa().limpiaSeleccion();
					modelo.getMapa().getSeleccion().añadirNodo(nodoOrigen);
					
					//Repintar
					panel.sugerir(null);
					//panel.recrearMapa();
					panel.repaint();
				}
				else /*if (estado==1)*/ { //Solo hay 2 valores para el estado, luego ya sabemos que estado vale 1.
					if (seleccionado != nodoOrigen) {
						//Llamar a la herramienta adecuada (notificándoselo al controlador)
						Nodo nodoDestino = seleccionado;
						IHerramienta nueva = new HCreaItinerarioEntreDosNodos(nodoOrigen,nodoDestino);
						controlador.herramienta(nueva);
					
						//Reiniciar valores de nodoOrigen
						estado = 0;
						nodoOrigen = null;
						
						//Repintar
						panel.sugerir(null);
						//panel.recrearMapa();
						panel.repaint();
					}
				}
				panel.sugerir(null);
			}
		}

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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Nodo nodoSugerir = buscarNodo(e.getX(), e.getY());
		panel.sugerir(nodoSugerir); //sea null o no

	}

	@Override
	public String getAyuda() {
		return Messages.getString("MLEscuchaItinerario.0"); //$NON-NLS-1$
	}

}
