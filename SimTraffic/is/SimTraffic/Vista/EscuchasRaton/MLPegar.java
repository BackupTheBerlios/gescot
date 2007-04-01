package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Herramientas.HPegar;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Posicion;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class MLPegar extends EscuchaRaton {

	public MLPegar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		double x = panel.lon_RepAMapa(arg0.getX());
		double y = panel.lat_RepAMapa(arg0.getY());
		Point2D puntoPegar = new Point2D.Double(x,y);
		//getSeleccion o getPortapapeles ??
		HPegar herramientaPegar = new HPegar (puntoPegar);
		controlador.herramienta(herramientaPegar);
		panel.setRecrear(true);
		panel.repaint();		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
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
	public void mouseMoved(MouseEvent arg0) {
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
	

}
