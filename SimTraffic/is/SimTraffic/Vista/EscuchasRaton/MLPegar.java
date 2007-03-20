package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Posicion;

import java.awt.event.MouseEvent;

public class MLPegar extends EscuchaRaton {

	public MLPegar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		double x = panel.lon_RepAMapa(arg0.getX());
		double y = panel.lat_RepAMapa(arg0.getY());
		double difX = x - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLon();
		double difY = y - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLat();

		//for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size(); i++) {
		for (int i=0; i<modelo.getMapa().getPortapapeles().getTramosSeleccionados().size(); i++) {
			Tramo tramoTemp = modelo.getMapa().getPortapapeles().getTramosSeleccionados().get(i);
			Nodo nodoInicial = tramoTemp.getNodoInicial();
			Nodo nodoFinal = tramoTemp.getNodoFinal();
			Nodo nodoTemp = new Nodo (nodoInicial.getPos().clone());
			nodoTemp.setPos(new Posicion(nodoTemp.getPos().getLat()+difY,nodoTemp.getPos().getLon()+difX));
			Nodo nodoMapa = modelo.getMapa().existeNodo(nodoTemp);
			if (nodoMapa!=null)
				nodoMapa.añadirTramo(tramoTemp);
			else
				modelo.getMapa().insertar(nodoTemp);

			nodoTemp = new Nodo (nodoFinal.getPos().clone());
			nodoTemp.setPos(new Posicion(nodoTemp.getPos().getLat()+difY,nodoTemp.getPos().getLon()+difX));
			nodoMapa = modelo.getMapa().existeNodo(nodoTemp);
			if (nodoMapa!=null)
				nodoMapa.añadirTramo(tramoTemp);
			else
				modelo.getMapa().insertar(nodoTemp);
		}
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
