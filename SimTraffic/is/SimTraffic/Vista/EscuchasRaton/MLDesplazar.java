package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;


public class MLDesplazar extends EscuchaRaton {

	int xAnterior;
	int yAnterior;
	
	public MLDesplazar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		panel.cambiaPosX(xAnterior - arg0.getX());
		panel.cambiaPosY(yAnterior - arg0.getY());
		xAnterior = arg0.getX();
		yAnterior = arg0.getY();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.xAnterior = arg0.getX();
		this.yAnterior = arg0.getY();
	}

	@Override
	public String getAyuda() {
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
