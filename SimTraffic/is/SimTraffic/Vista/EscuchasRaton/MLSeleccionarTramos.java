package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class MLSeleccionarTramos extends EscuchaRaton{

	public MLSeleccionarTramos(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		panel.setPuntoInicial(arg0.getPoint());
		panel.setModoSeleccion(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		panel.setModoSeleccion(false);
		//panel.recrearMapa();
		panel.repaint();
		panel.notificaSeleccion(2);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Point puntoDrag = arg0.getPoint();
		panel.setPuntoDrag(puntoDrag);
		panel.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
