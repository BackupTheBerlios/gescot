package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.Point;
import java.awt.event.*;

public class MLSeleccionarNodos extends EscuchaRaton {
	
	private boolean drag;
	
	public MLSeleccionarNodos(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		drag = false;
		panel.setFocusable(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (this.getModificadorDeTeclado() != 17){
			this.modelo.getMapa().limpiaSeleccion();
		}
		Nodo seleccionado = buscarNodo(arg0.getX(), arg0.getY());
		if (seleccionado != null)
			if (modelo.getMapa().getSeleccion().getNodosSeleccionados().contains(seleccionado)){
				modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(seleccionado);
			} else {
				modelo.getMapa().getSeleccion().añadirNodo(seleccionado);
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
		panel.setPuntoInicial(arg0.getPoint());
		panel.setModoSeleccion(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		panel.setModoSeleccion(false);
		panel.repaint();
		
		if (this.getModificadorDeTeclado() != 17 && drag)
			this.modelo.getMapa().limpiaSeleccion();

		if (drag)
			panel.notificaSeleccion(0);

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
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * Sobreescribo el método del padre para añadir funciones extra.
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#notificar(int)
	 */
	public void notificar(int modificador){
		super.notificar(modificador);
		System.out.print(modificador);
		// Si se ha pulsado la tecla "Supr"
		if (modificador == 127){
			//Crear una herramienta de borrado de varios nodos
			//y aplicarla al modelo.
			
			//(es una idea).
			
		}
	}
}
