package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MLMover extends EscuchaRaton{

	HMover herramientaMover;
	Point2D puntoInicial;
	Point2D puntoFinal;
	
	boolean yaSeleccionado;
	
	public MLMover(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		//drag = false;
		panel.setFocusable(true);
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
		yaSeleccionado = false;
		
		Nodo nodoSeleccionado = buscarNodo(arg0.getX(), arg0.getY());
		if (nodoSeleccionado != null){
			yaSeleccionado = true;
			/*if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoSeleccionado)){
				//modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(nodoSeleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirNodo(nodoSeleccionado);
			}*/
		}
		
		Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
		if (seleccionado != null && !yaSeleccionado)
			yaSeleccionado = true;
			/*if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(seleccionado)){
				modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(seleccionado);
				
			} else {
				modelo.getMapa().getSeleccion().añadirTramo(seleccionado);*/
			
		
		if (yaSeleccionado == true){
			puntoInicial = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),panel.lat_RepAMapa(arg0.getY()));
		}
		int i = 5;
		i++;
		//i= puntoInicial.x;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (yaSeleccionado){
			puntoFinal = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),panel.lat_RepAMapa(arg0.getY()));
			herramientaMover = new HMover(modelo.getMapa().getSeleccion().getNodosSeleccionados(),
											modelo.getMapa().getSeleccion().getTramosSeleccionados(),
											puntoInicial,puntoFinal);
			controlador.herramienta(herramientaMover);
			this.panel.setRecrear(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
