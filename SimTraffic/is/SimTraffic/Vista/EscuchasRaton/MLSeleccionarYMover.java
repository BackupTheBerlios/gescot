/**
 * EN CONSTRUCCION
 * Pablo y Álex (the best)
 */

package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MLSeleccionarYMover extends EscuchaRaton{
	
	private boolean drag;
	private Point puntoDrag;
	private Nodo nodoSeleccionado;
	private int modoHerramienta; //Selección:0,Mover:1
	private Nodo nodoInicial;
	private Tramo tramoInicial;
	private Point puntoInicial;
	private boolean estaSeleccionado;
	
	
	public MLSeleccionarYMover(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		drag = false;
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
		// TODO Auto-generated method stub
		/*	
		 double x = this.panel.lon_RepAMapa(arg0.getX());
		 double y = this.panel.lat_RepAMapa(arg0.getY());
		 Nodo posibleNodo = buscarNodo(x,y);
		 if (posibleNodo!=null) {
		 nodoMover=posibleNodo;
		 }
		 */		
		//double x = this.panel.lon_RepAMapa(arg0.getX());
		//double y = this.panel.lat_RepAMapa(arg0.getY());
		nodoInicial=null;
		tramoInicial=null;
		puntoInicial = new Point(arg0.getX(),arg0.getY());
		Nodo posibleNodo = buscarNodo(arg0.getX(),arg0.getY());
		if (posibleNodo!=null) {			
			nodoInicial=posibleNodo;
		}
		Tramo posibleTramo = buscarTramo(arg0.getX(),arg0.getY());
		if (posibleTramo!=null) {
			tramoInicial = posibleTramo;
			modoHerramienta=0;//seleccionar
			
		}
		if (posibleNodo==null && posibleTramo==null)
			modoHerramienta=0;//seleccionar
		puntoDrag = puntoInicial;
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (modoHerramienta==0) { //seleccionar
			if (puntoInicial.equals(puntoDrag)) {//es un clic
				modelo.getMapa().setSeleccion(new Seleccion());
				if (nodoInicial!=null) 
					modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
				else {
					if (tramoInicial!=null)
						modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
				}
			}
			else {//es un rectangulo de seleccion
				
			}
			
		}
		
		else { //mover
			
		}
		
	}

@Override
public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub
	puntoDrag = new Point(arg0.getX(),arg0.getY());
	if (nodoInicial!=null && modoHerramienta!=1)
		modoHerramienta=1; //mover
	else {
		modoHerramienta=0; //seleccionar
	}
	//dibujar o cuadrado o fantasma
	
}

@Override
public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
