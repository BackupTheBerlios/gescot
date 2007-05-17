package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HMover;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

/**
 * Escucha que permite recibir los eventos de ratón que suceden cuando el
 * usuario se dispone a mover los nodos sobre el mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class MLMover extends EscuchaRaton {
	/**
	 * La herramienta que será aplicada por el controlador.
	 */
	HMover herramientaMover;

	/**
	 * Punto en el que el usuario hace click inicialmente. Es el origen, la
	 * posicion relativa donde se encuentran los nodos inicialmente.
	 */
	Point2D puntoInicial;

	/**
	 * Punto destino al cual se desea desplazar los nodos.
	 */
	Point2D puntoFinal;

	/**
	 * Punto auxiliar que permite redibujar los nodos y tramos mientras se están
	 * desplazando.
	 */
	Point2D puntoDragAntiguo;

	/**
	 * Punto auxiliar que permite redibujar los nodos y tramos mientras se están
	 * desplazando.
	 */
	Point2D puntoDragNuevo;

	/**
	 * 
	 */
	boolean yaSeleccionado;

	public MLMover(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		// drag = false;
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
		yaSeleccionado = false;

		Nodo nodoSeleccionado = buscarNodo(arg0.getX(), arg0.getY());

		// Para permitir el desplazamiento se tienen que cumplir la condicion
		// siguiente:
		// ·Que se haga click y arrastre sobre un nodo seleccionado.
		if (nodoSeleccionado != null
				&& modelo.getMapa().getSeleccion().getNodosSeleccionados()
						.contains(nodoSeleccionado)) {
			yaSeleccionado = true;
			// Si no hay nodos seleccionados, se permite seleccionar un nodo con
			// un click y moverlo.
			if (modelo.getMapa().getSeleccion().getNodosSeleccionados().size() == 0) {
				modelo.getMapa().getSeleccion().getNodosSeleccionados().add(
						nodoSeleccionado);
			}
		}

		Tramo seleccionado = buscarTramo(arg0.getX(), arg0.getY());
		if (seleccionado != null && !yaSeleccionado)
			yaSeleccionado = true;

		if (yaSeleccionado == true) {
			puntoInicial = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),
					panel.lat_RepAMapa(arg0.getY()));
			herramientaMover = new HMover(modelo.getMapa().getSeleccion()
					.getNodosSeleccionados());
			puntoDragAntiguo = puntoInicial;
		}
		int i = 5;
		i++;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (yaSeleccionado) {
			puntoFinal = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),
					panel.lat_RepAMapa(arg0.getY()));

			herramientaMover.estableceInicioYFin(puntoInicial, puntoFinal);
			controlador.herramienta(herramientaMover);
			this.panel.setRecrear(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		puntoDragNuevo = new Point2D.Double(panel.lon_RepAMapa(arg0.getX()),
				panel.lat_RepAMapa(arg0.getY()));

		if (yaSeleccionado) {
			double diferenciaX = puntoDragNuevo.getX()
					- puntoDragAntiguo.getX();
			double diferenciaY = puntoDragNuevo.getY()
					- puntoDragAntiguo.getY();
			for (int i = 0; i < modelo.getMapa().getSeleccion()
					.getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion()
						.getNodosSeleccionados().get(i);
				nodoTemp
						.setPos(new Posicion(nodoTemp.getPos().getLat()
								+ diferenciaY, nodoTemp.getPos().getLon()
								+ diferenciaX));
				Iterator<Tramo> tram = nodoTemp.getTramos().iterator();
				Tramo temp = null;
				while (tram.hasNext()) {
					temp = tram.next();
					temp.calculaLargo();
					temp.calculaAngulo();
					System.out.println(Messages.getString("MLMover.0")); //$NON-NLS-1$
				}
			}
		}
		puntoDragAntiguo = puntoDragNuevo;

		panel.setRecrear(true);
		panel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAyuda() {
		return Messages.getString("MLMover.1"); //$NON-NLS-1$
	}

}
