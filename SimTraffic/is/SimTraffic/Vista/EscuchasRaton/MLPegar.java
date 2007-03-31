package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Posicion;

import java.awt.event.MouseEvent;

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
		double difX = x - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLon();
		double difY = y - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLat();

		//for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size(); i++) {
		for (int i=0; i<modelo.getMapa().getPortapapeles().getTramosSeleccionados().size(); i++) {
			Tramo tramoTemp = modelo.getMapa().getPortapapeles().getTramosSeleccionados().get(i);			
			Nodo nodoInicial = tramoTemp.getNodoInicial();
			Nodo nodoFinal = tramoTemp.getNodoFinal();
			Nodo nodoInicialTemp = new Nodo (nodoInicial.getPos().clone());
			nodoInicialTemp.setPos(new Posicion(nodoInicialTemp.getPos().getLat()+difY,nodoInicialTemp.getPos().getLon()+difX));
			Nodo nodoInicialMapa = modelo.getMapa().existeNodo(nodoInicialTemp);
			if (nodoInicialMapa==null) {
				nodoInicialMapa = nodoInicialTemp;
				modelo.getMapa().insertar(nodoInicialMapa);
			}
			

			Nodo nodoFinalTemp = new Nodo (nodoFinal.getPos().clone());
			nodoFinalTemp.setPos(new Posicion(nodoFinalTemp.getPos().getLat()+difY,nodoFinalTemp.getPos().getLon()+difX));
			Nodo nodoFinalMapa = modelo.getMapa().existeNodo(nodoFinalTemp);
			if (nodoFinalMapa==null) {
				nodoFinalMapa = nodoFinalTemp;
				modelo.getMapa().insertar(nodoFinalMapa);
			}
			
			
			Tramo tramoMapa = tramoTemp.pseudoClone(nodoInicialMapa,nodoFinalMapa);
			nodoInicialMapa.añadirTramo(tramoMapa);
			nodoFinalMapa.añadirTramo(tramoMapa);
			modelo.getMapa().insertar(tramoMapa);
		}
		
		for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size(); i++) {
			Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
			Nodo nodoMapa = nodoTemp.pseudoClone();
			nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
			if (modelo.getMapa().existeNodo(nodoMapa)==null) {
				//Nodo nodoMapa = nodoTemp.pseudoClone();
				modelo.getMapa().insertar(nodoMapa);
				//nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
			}
				
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
