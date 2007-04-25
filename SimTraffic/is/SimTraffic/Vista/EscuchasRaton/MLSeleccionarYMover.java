package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Esta clase se encarga de controlar la escucha que se encarga de seleccionar y mover elementos 
 * @author GrupoISTrafico
 *
 */


public class MLSeleccionarYMover extends EscuchaRaton{
	
	
	private boolean drag;
	private int modoHerramienta; //Selección:0,Mover:1
	private Nodo nodoInicial;
	private Tramo tramoInicial;
	private Point2D puntoInicial;	
	private boolean yaSeleccionado;
	private HMover herramientaMover;
	private Point2D puntoDragAntiguo;
	private Point2D puntoFinal;
	private Point2D puntoDragNuevo;
	
	
	public MLSeleccionarYMover(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		drag = false;
		panel.setFocusable(true);		
		yaSeleccionado=false;
	}
	
	@Override
	/**
	 * Este mouseClicked detecta si se ha hecho clic en un nodo, en un tramo o
	 * en el mapa. Si estaba pulsado el control, se añade el elemento correspondiente
	 * a la selección. Si no, se borra la selección y se añade ese elemento. 
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getButton() != MouseEvent.BUTTON1)
			return;

		modoHerramienta=0; //seleccionar
		nodoInicial=null;
		tramoInicial=null;
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
		
		if (arg0.getButton() != MouseEvent.BUTTON1)
			return;
		nodoInicial=null;
		tramoInicial=null;
		puntoInicial = new Point(arg0.getX(),arg0.getY());		
		Nodo posibleNodo = buscarNodo(arg0.getX(),arg0.getY());
		if (posibleNodo!=null) { //si se ha pulsado sobre un nodo, se va a mover		
			nodoInicial=posibleNodo;
			modoHerramienta=1;//mover
			
			yaSeleccionado = false;
			Nodo nodoSeleccionado = buscarNodo(arg0.getX(), arg0.getY());
			//Para permitir el desplazamiento se tienen que cumplir la condicion siguiente:
			//·Que se haga click y arrastre sobre un nodo seleccionado.
			if (nodoSeleccionado != null && modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoSeleccionado)){
				yaSeleccionado = true;
				//Si no hay nodos seleccionados, se permite seleccionar un nodo con un click y moverlo.
				if (modelo.getMapa().getSeleccion().getNodosSeleccionados().size()==0){
					modelo.getMapa().getSeleccion().getNodosSeleccionados().add(nodoSeleccionado);
				}
			}
			
			Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
			if (seleccionado != null && !yaSeleccionado)
				yaSeleccionado = true;
			
			if (yaSeleccionado == true){
				puntoInicial = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),panel.lat_RepAMapa(arg0.getY()));
				herramientaMover = new HMover(modelo.getMapa().getSeleccion().getNodosSeleccionados());
				puntoDragAntiguo = puntoInicial;
			}
			int i = 5;
			i++;
			
		}		
		if (posibleNodo==null) {
			modoHerramienta=0;//seleccionar		
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
		if (arg0.getButton() != MouseEvent.BUTTON1)
			return;
		if (modoHerramienta==0) { //seleccionar
			panel.setModoSeleccion(false);
			if (this.getModificadorDeTeclado() != 17 && drag)
				this.modelo.getMapa().limpiaSeleccion();
			if (drag)
				panel.notificaSeleccion(1);
			drag = false;		
		}
		else { //mover
			if (yaSeleccionado){
				puntoFinal = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),panel.lat_RepAMapa(arg0.getY()));
				
				herramientaMover.estableceInicioYFin(puntoInicial,puntoFinal);
				controlador.herramienta(herramientaMover);
				this.panel.setRecrear(true);
			}
		}
		panel.repaint();				
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub				
		if (modoHerramienta==0) { //seleccionar			
			Point puntoDrag = arg0.getPoint();
			panel.setPuntoDrag(puntoDrag);
			panel.repaint();
			drag = true;					
		}
		else { //modoHerramienta=1, mover
			puntoDragNuevo = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),panel.lat_RepAMapa(arg0.getY()));
			
			if (yaSeleccionado){double diferenciaX = puntoDragNuevo.getX()-puntoDragAntiguo.getX();
				double diferenciaY = puntoDragNuevo.getY()-puntoDragAntiguo.getY();
				for (int i = 0; i < modelo.getMapa().getSeleccion().getNodosSeleccionados().size();i++){
					Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
					nodoTemp.setPos(new Posicion(nodoTemp.getPos().getLat()+diferenciaY,
							nodoTemp.getPos().getLon()+diferenciaX));
				}
			}
			puntoDragAntiguo = puntoDragNuevo;
			
			panel.setRecrear(true);
			panel.repaint();
		}			
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
		}
		
	}
@Override
public String getAyuda() {
	return "Selecciona mediante clics o recuadro de selección(click+arrastre). " +
			"Arrastra una selección para moverla. Pulsa 'Ctrl' para añadir o quitar.";
}

}
