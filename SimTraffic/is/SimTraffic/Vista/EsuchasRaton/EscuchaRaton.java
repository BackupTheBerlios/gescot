/**
 * 
 */
package is.SimTraffic.Vista.EsuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Clase EsuchaRaton, con los métodos básicos para una clase que toma entras de
 * raton
 * <p>
 * Esta clase deberá ser extendida por todas aquellas que pretendan implementar
 * la funcionalidad de una herramienta sobre el mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
abstract public class EscuchaRaton implements MouseListener, MouseMotionListener {

	IModelo modelo;

	IControlador controlador;

	PanelMapa panel;

	Image cursor;

	boolean activo;

	public EscuchaRaton(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
	}

	/**
	 * Método que establece este esucha como el activo en el panel del mapa.<p>
	 * Este metodo primero estable el icono del esucha, para luego esablecer esta
	 * instancia como esucha del raton y del movimiento del raton.
	 */
	public void activar() {
		if (cursor != null)
			panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
					cursor, new Point(0, 0), "AñadirNodo"));
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}

	/**
	 * Método para quitar esta instancia como esucha del raton y su movimiento.
	 */
	public void desactivar() {
		panel.removeMouseListener(this);
		panel.removeMouseMotionListener(this);
	}

	abstract public void mouseClicked(MouseEvent arg0);

	abstract public void mouseEntered(MouseEvent arg0);

	abstract public void mouseExited(MouseEvent arg0);

	abstract public void mousePressed(MouseEvent arg0);

	abstract public void mouseReleased(MouseEvent arg0);

	abstract public void mouseDragged(MouseEvent arg0);

	abstract public void mouseMoved(MouseEvent arg0);

}
