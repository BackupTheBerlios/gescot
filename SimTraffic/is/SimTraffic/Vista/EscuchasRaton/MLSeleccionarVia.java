package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class MLSeleccionarVia extends EscuchaRaton{

	private boolean drag;
	
	public MLSeleccionarVia(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
		drag = false;
		panel.setFocusable(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!modelo.getSimulacion().estaActiva()) {
		if (this.getModificadorDeTeclado() != 17){
			this.modelo.getMapa().limpiaSeleccion();
		}
		Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
		if (seleccionado != null)
			//Si volvemos a seleccionar una via ya seleccionada = deseleccion
			if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(seleccionado)){
				modelo.getMapa().setSeleccion(null);
			} else {
				ArrayList<Via> vias =modelo.getMapa().getVias();
				
				//Buscamos la via que contiene al tramo elegido
				for(int i=0;i<vias.size();i++)
				 if (vias.get(i).getTramos().contains(seleccionado)){
				   //Añadir los tramos y los nodos a la seleccion
				  Iterator tramos = vias.get(i).getTramos().iterator(); 	
				  while(tramos.hasNext()){
				   Tramo aux = (Tramo)tramos.next();	
				   //Añadimos cada uno de los tramos	
				   modelo.getMapa().getSeleccion().añadirTramo(aux);
				   //Añadimos cada uno de los nodos que une cada tramo de la via
				   modelo.getMapa().getSeleccion().añadirNodo(aux.getNodoInicial());
				   modelo.getMapa().getSeleccion().añadirNodo(aux.getNodoFinal());
				 }
				 }
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
		if (!modelo.getSimulacion().estaActiva()) {
		panel.setPuntoInicial(arg0.getPoint());
		panel.setModoSeleccion(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//panel.setModoSeleccion(false);		
		//panel.repaint();
		//panel.notificaSeleccion(2);		
		if (!modelo.getSimulacion().estaActiva()) {
		panel.setModoSeleccion(false);
		panel.repaint();
		
		if (this.getModificadorDeTeclado() != 17 && drag)
			this.modelo.getMapa().limpiaSeleccion();

		if (drag)
			panel.notificaSeleccion(2);

		drag = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (!modelo.getSimulacion().estaActiva()) {
		Point puntoDrag = arg0.getPoint();
		panel.setPuntoDrag(puntoDrag);
		panel.repaint();
		drag = true;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
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
	
	public String getAyuda() {
		return Messages.getString("MLSeleccionarVia.0"); //$NON-NLS-1$
	}
}
