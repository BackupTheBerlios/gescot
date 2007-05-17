package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Point;
import java.awt.event.MouseEvent;
/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class MLSeleccionarTramos extends EscuchaRaton{
	
	private boolean drag;
	
	public MLSeleccionarTramos(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
		drag = false;
		panel.setFocusable(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (this.getModificadorDeTeclado() != 17){
			this.modelo.getMapa().limpiaSeleccion();
		}
		Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
		if (seleccionado != null)
			if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(seleccionado)){
				modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(seleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirTramo(seleccionado);
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
		panel.setPuntoInicial(arg0.getPoint());
		panel.setModoSeleccion(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//panel.setModoSeleccion(false);		
		//panel.repaint();
		//panel.notificaSeleccion(2);		
		
		panel.setModoSeleccion(false);
		panel.repaint();
		
		if (this.getModificadorDeTeclado() != 17 && drag)
			this.modelo.getMapa().limpiaSeleccion();

		if (drag)
			panel.notificaSeleccion(2);

		drag = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Point puntoDrag = arg0.getPoint();
		panel.setPuntoDrag(puntoDrag);
		panel.repaint();
		drag = true;

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
		return Messages.getString("MLSeleccionarTramos.0"); //$NON-NLS-1$
	}
}
