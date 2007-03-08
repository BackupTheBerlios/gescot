/**
 * 
 */
package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;

/**
 * Clase que extiende MouseListener para recoger todos los eventos
 * que ocurran en cualquier momento en el mapa.
 * <p>
 * Esta clase implemeta los métodos de MouseListener para poder caturar los
 * en todo momento el desplazamiento y los clicks del ratón sobre el mapa.
 * 
 * @author Grupo ISTrafico
 */
public class MLEscuchaSiempre extends EscuchaRaton {

	public MLEscuchaSiempre(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == MouseEvent.BUTTON2){
			
		}
			
	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		

	}

}
