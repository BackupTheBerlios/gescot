/**
 * EN CONSTRUCCION
 * Pablo y Álex (the best)
 */

package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Esta clase se encarga de controlar la escucha que se encarga de seleccionar y mover elementos 
 * @author GrupoISTrafico
 *
 */


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

		if (nodoInicial!=null) {
			if (this.getModificadorDeTeclado()==17) { //si pulsas control
				if (!modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoInicial))
					modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
				else
					modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(nodoInicial);
			}
			else {				
				modelo.getMapa().setSeleccion(new Seleccion());
				modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
			}
		}
		else {
			if (tramoInicial!=null) {
				if (this.getModificadorDeTeclado()==17) { //control
					if (!modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(tramoInicial))
						modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
					else
						modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(tramoInicial);
				}
				else {
					modelo.getMapa().setSeleccion(new Seleccion());
					modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
				}
			}
			else {
				modelo.getMapa().setSeleccion(new Seleccion());
			}
				
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
			modoHerramienta=1;//mover
		}
		Tramo posibleTramo = buscarTramo(arg0.getX(),arg0.getY());
		if (posibleTramo!=null) {
			tramoInicial = posibleTramo;
			modoHerramienta=0;//seleccionar
			
		}
		if (posibleNodo==null && posibleTramo==null) {
			modoHerramienta=0;//seleccionar
			puntoDrag = puntoInicial;
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

		
		if (modoHerramienta==0) { //seleccionar
			if (puntoInicial.equals(puntoDrag)) {//es un clic
				panel.setModoSeleccion(false);
				panel.repaint();
				if (this.getModificadorDeTeclado() != 17 && drag)
					this.modelo.getMapa().limpiaSeleccion();

				if (drag)
					panel.notificaSeleccion(1);

				drag = false;				
				if (nodoInicial!=null) {
					if (this.getModificadorDeTeclado()==17) { //si pulsas control
						if (!modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoInicial))
							modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
						else
							modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(nodoInicial);
					}
					else
						modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
				}
				else {
					if (tramoInicial!=null)
						modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
				}
			}
			else {//es un rectangulo de seleccion
				
			}
			
		}
		
		else { //mover
			HMover herramientaMover = new HMover(modelo.getMapa().getSeleccion().getNodosSeleccionados());
			controlador.herramienta(herramientaMover);						
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		/*
		//copiado de MLSeleccionarElementos
		
		panel.setModoSeleccion(true);
		Point puntoDrag = arg0.getPoint();
		panel.setPuntoDrag(puntoDrag);
		panel.repaint();
		drag = true;
		
		//hasta aqui
		*/

		
		//puntoDrag = new Point(arg0.getX(),arg0.getY());
		if (nodoInicial!=null && modoHerramienta!=1)
			modoHerramienta=1; //mover
		else {
			modoHerramienta=0; //seleccionar
		}
		if (modoHerramienta==0) { //seleccionar
			panel.setModoSeleccion(true);
			Point puntoDrag = arg0.getPoint();
			panel.setPuntoDrag(puntoDrag);
			panel.repaint();
			drag = true;
		}
		else { //mover
			Point puntoDrag = arg0.getPoint();
			panel.setPuntoDrag(puntoDrag);
			panel.repaint();
			drag = true;
			
		}
		//dibujar o cuadrado o fantasma
			
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void notificar(int modificador){
		super.notificar(modificador);
		// Si se ha pulsado la tecla "Supr"
		if (modificador == 127){
			//Crear una herramienta de borrado de varios nodos
			HEliminarSeleccion herramientaBorrarSeleccion = 
				new HEliminarSeleccion(modelo.getMapa().getSeleccion().getNodosSeleccionados(),
						modelo.getMapa().getSeleccion().getTramosSeleccionados());
			//y aplicarla al modelo.
			controlador.herramienta(herramientaBorrarSeleccion);
			
			modelo.getMapa().limpiaSeleccion();
			panel.setRecrear(true);
			panel.repaint();
			//panel.setRecrear(false);
		}
		
	}
@Override
public String getAyuda() {
	return "";
}

}
