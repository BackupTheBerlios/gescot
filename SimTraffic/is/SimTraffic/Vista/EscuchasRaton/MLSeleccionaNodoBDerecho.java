package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MLSeleccionaNodoBDerecho extends EscuchaRaton {
	
	private boolean drag;
	public MLSeleccionaNodoBDerecho(IModelo modelo, IControlador controlador, PanelMapa panel){
		super(modelo, controlador, panel);
		panel.setFocusable(true);
		drag = false;
	}

		public void mouseClicked(MouseEvent arg0) {
			if(arg0.isPopupTrigger()){
				Nodo seleccionado = buscarNodo(arg0.getX(), arg0.getY());
				if (seleccionado != null){
					if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(seleccionado)){
						modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(seleccionado);
					} else {
						modelo.getMapa().getSeleccion().añadirNodo(seleccionado);
					}
					panel.setPosE(arg0.getX(), arg0.getY());
					System.out.println(panel.getPosEx());
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
			System.out.println(panel.getPosEx());
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
			panel.getMenuEmergente().setLocation(arg0.getX()+30,arg0.getY()+76);
			panel.getMenuEmergente().setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.isPopupTrigger() && buscarNodo(arg0.getX(), arg0.getY()) != null){
			panel.setPosE(arg0.getX(), arg0.getY());
			System.out.println(panel.getPosEx());
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
			panel.getMenuEmergente().setLocation(arg0.getX()+30,arg0.getY()+76);
			panel.getMenuEmergente().setVisible(true);
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
