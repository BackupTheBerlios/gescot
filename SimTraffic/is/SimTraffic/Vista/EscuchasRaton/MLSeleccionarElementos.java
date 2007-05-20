package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class MLSeleccionarElementos extends EscuchaRaton{

	private boolean drag;
	
	public MLSeleccionarElementos(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		drag = false;
		panel.setFocusable(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!modelo.getSimulacion().estaActiva()) {
		boolean yaSeleccionado = false;
		
		if (this.getModificadorDeTeclado() != 17){
			this.modelo.getMapa().limpiaSeleccion();
		}
		Nodo nodoSeleccionado = buscarNodo(e.getX(), e.getY());
		if (nodoSeleccionado != null){
			yaSeleccionado = true;
			if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(nodoSeleccionado)){
				modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(nodoSeleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirNodo(nodoSeleccionado);
			}
		}
		
		Tramo seleccionado = buscarTramo(e.getX(), e.getY());
		if (seleccionado != null && !yaSeleccionado)
			if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(seleccionado)){
				modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(seleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirTramo(seleccionado);
		}
		}
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
			
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
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
		//panel.recrearMapa();
		//panel.repaint();
		//panel.notificaSeleccion(1);
		if (!modelo.getSimulacion().estaActiva()) {
		panel.setModoSeleccion(false);
		panel.repaint();
		
		if (this.getModificadorDeTeclado() != 17 && drag)
			this.modelo.getMapa().limpiaSeleccion();

		if (drag)
			panel.notificaSeleccion(1);

		drag = false;
	
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
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

	@Override
	public String getAyuda() {
		return Messages.getString("MLSeleccionarElementos.0"); //$NON-NLS-1$
	}

}
