package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class MLSeleccionarTramos extends EscuchaRaton{
	
	private boolean drag;
	
	public MLSeleccionarTramos(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
		drag = false;
		panel.setFocusable(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (this.getModificadorDeTeclado() != 17){
			this.modelo.getMapa().limpiaSeleccion();
		}
		Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
		if (seleccionado != null)
			if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(seleccionado)){
				modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(seleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirTramo(seleccionado);
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
		panel.setPuntoInicial(arg0.getPoint());
		panel.setModoSeleccion(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//panel.setModoSeleccion(false);		
		//panel.repaint();
		//panel.notificaSeleccion(2);		
		
		panel.setModoSeleccion(false);
		panel.repaint();
		
		if (this.getModificadorDeTeclado() != 17 && drag)
			this.modelo.getMapa().limpiaSeleccion();

		if (drag)
			panel.notificaSeleccion(2);

		drag = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Point puntoDrag = arg0.getPoint();
		panel.setPuntoDrag(puntoDrag);
		panel.repaint();
		drag = true;

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
