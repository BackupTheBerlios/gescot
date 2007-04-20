package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MLSeleccionaNodoBDerecho extends EscuchaRaton {
	
	private Nodo seleccionado;
	private boolean drag;
	public MLSeleccionaNodoBDerecho(IModelo modelo, IControlador controlador, PanelMapa panel){
		super(modelo, controlador, panel);
		panel.setFocusable(true);
		drag = false;
	}

		public void mouseClicked(MouseEvent arg0) {
			if(arg0.isPopupTrigger()){
				seleccionado = buscarNodo(arg0.getX(), arg0.getY());
				if (seleccionado != null){
					if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(seleccionado)){
						modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(seleccionado);
					} else {
						modelo.getMapa().getSeleccion().añadirNodo(seleccionado);
					}
					panel.setPosE(arg0.getX(), arg0.getY());
				}
			}
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.isPopupTrigger() && buscarNodo(arg0.getX(), arg0.getY())!= null){
			panel.setPosE(arg0.getX(), arg0.getY());
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
			panel.getMenuEmergente().show(panel,arg0.getX(),arg0.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.isPopupTrigger() && buscarNodo(arg0.getX(), arg0.getY()) != null){
			panel.setPosE(arg0.getX(), arg0.getY());
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
			panel.getMenuEmergente().show(panel,arg0.getX(),arg0.getY());
			panel.setModoSeleccion(false);
			panel.repaint();
			
			if (this.getModificadorDeTeclado() != 17 && drag)
				this.modelo.getMapa().limpiaSeleccion();

			if (drag)
				panel.notificaSeleccion(0);

			drag = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public String getAyuda() 
	{
		return "";
	}
	

}
