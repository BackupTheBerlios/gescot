/**
 *  CLASE NO TERMINADA
 */
package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Graphics;
import java.awt.event.MouseEvent;


/**
 * Clase que extiende MouseListener para poder dibujar en el mapa.
 * <p>
 * Clase que permite seleccionar un grupo de elementos
 * del mapa; por ejemplo, nodos y tramos.
 * @author Grupo ISTrafico
 */
public class MLEliminarSeleccion extends EscuchaRaton {

	private int x1;
	private int y1;
	
	public MLEliminarSeleccion(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		super(modelo, controlador, panel);
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1)
			return;
		x1 = panel.x_RepAMapa(e.getX());
		y1 = panel.y_RepAMapa(e.getY());
		panel.recrearMapa();
		panel.repaint();
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		//HEliminarSeleccion nueva = new HEliminarSeleccion ();
		//controlador.herramienta(nueva);
		panel.recrearMapa();
		panel.repaint();

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x2 = arg0.getX();
		int y2 = arg0.getY();
		//Graphics2D g = null;
		//java.awt.Graphics.drawRect(x1,y1,x2-x1,y2-y1);
		//
		panel.recrearMapa();
		panel.repaint();

	

	}
}

