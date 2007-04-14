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
	private boolean simpleClic;
	
	
	public MLSeleccionarYMover(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		drag = false;
		panel.setFocusable(true);
		simpleClic=false;
	}
	
	@Override
	/**
	 * Este mouseClicked detecta si se ha hecho clic en un nodo, en un tramo o
	 * en el mapa. Si estaba pulsado el control, se añade el elemento correspondiente
	 * a la selección. Si no, se borra la selección y se añade ese elemento. 
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		modoHerramienta=0; //seleccionar
		puntoDrag = puntoInicial; //es un clic
		simpleClic = true;
		nodoInicial=null;
		tramoInicial=null;
		puntoInicial = new Point(arg0.getX(),arg0.getY());
		Nodo posibleNodo = buscarNodo(arg0.getX(),arg0.getY());
		if (posibleNodo!=null) { //si se hace clic sobre un nodo	
			nodoInicial=posibleNodo;		
		}
		Tramo posibleTramo = buscarTramo(arg0.getX(),arg0.getY());
		if (posibleTramo!=null) { //si se hace clic sobre un tramo
			tramoInicial = posibleTramo;					
		}
		if (nodoInicial!=null) { //si se ha pulsado sobre un nodo
			if (this.getModificadorDeTeclado()==17) { //si se pulsa control
				//se añade el nodo si no está en la selección
				if (!modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoInicial))
					modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
				//si no, se elimina
				else
					modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(nodoInicial);
			}
			else { //si no se pulsa control
				//se borra la seleccion antigua y se selecciona el nodo
				modelo.getMapa().setSeleccion(new Seleccion());
				modelo.getMapa().getSeleccion().añadirNodo(nodoInicial);
			}
		}
		else { //si no se ha pulsado sobre un nodo
			if (tramoInicial!=null) { //si se ha pulsado sobre un tramo
				if (this.getModificadorDeTeclado()==17) { //si se pulsa control
					//si no está en la selección se añade el tramo
					if (!modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(tramoInicial))
						modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
					else //si está en la salección, se elimina de la selección
						modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(tramoInicial);
				}
				else { //si no se pulsa control
					//se borra la seleccion y se selecciona el tramo
					modelo.getMapa().setSeleccion(new Seleccion());
					modelo.getMapa().getSeleccion().añadirTramo(tramoInicial);
				}
			}
			else { //si se ha pulsado sobre el mapa, se borra la selección
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
	/**
	 * Método mousePressed que detecta si se mantiene pulsado el ratón estando
	 * en el modo selección/mover.
	 */
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
		if (posibleNodo!=null) { //si se ha pulsado sobre un nodo, se va a mover		
			nodoInicial=posibleNodo;
			modoHerramienta=1;//mover
		}
		Tramo posibleTramo = buscarTramo(arg0.getX(),arg0.getY());
		if (posibleTramo!=null) { //si se ha pulsado sobre un tramo, se va a seleccionar
			tramoInicial = posibleTramo;
			modoHerramienta=0;//seleccionar
			
		}
		if (posibleNodo==null && posibleTramo==null) {
			//si no se ha pulsado sobre nodo ni tramo, se va a hacer un rectángulo
			//de selección
			modoHerramienta=0;//seleccionar
			puntoDrag = puntoInicial;
			panel.setPuntoInicial(arg0.getPoint());
			panel.setModoSeleccion(true);
		}
		
	}
	
	@Override
	/**
	 * Método mouseReleased que se encarga de seleccionar nuevos elementos, o
	 * de mover los elementos seleccionados
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if (modoHerramienta==0) { //seleccionar
			//if (puntoInicial.equals(puntoDrag)) {//es un clic
			if (simpleClic) { //si es un clic
				simpleClic=false;
				panel.setModoSeleccion(false);
				panel.repaint();
			}
			else { //era un pressed
				//if (this.getModificadorDeTeclado() != 17 && drag)
				//	this.modelo.getMapa().limpiaSeleccion();
				panel.setModoSeleccion(false);
				panel.repaint();
				if (drag && panel.isModoSeleccion())
					panel.notificaSeleccion(1);
				drag = false;											
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
		
		//puntoDrag = new Point(arg0.getX(),arg0.getY());
		if (modoHerramienta==0) { //seleccionar
			panel.setModoSeleccion(true);
			Point puntoDrag = arg0.getPoint();
			panel.setPuntoDrag(puntoDrag);
			panel.repaint();
			drag = true;
		}
		else { //modoHerramienta=1, mover		
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
