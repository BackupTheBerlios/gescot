/**
 * 
 */
package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

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
abstract public class EscuchaRaton implements MouseListener,
		MouseMotionListener {

	/**
	 * Modelo actual de la aplicacion, donde se deben aplicar algunos de los
	 * efectos de un esucha de raton o donde puede conseguir información
	 */
	IModelo modelo;

	/**
	 * Controlador actual de la aplicación, donde se deben aplicar las posibles
	 * herramientas utilizadas por el escucha de raton
	 */
	IControlador controlador;

	/**
	 * Panel donde se esta representando el mapa
	 */
	PanelMapa panel;

	/**
	 * Imagen de cursor mostrada por la herramienta
	 */
	Image cursor;

	boolean activo;

	/**
	 * Este modificador permite ser modificado en función de lo que se haya
	 * recibido desde un evento de teclado. Representará el código de la tecla
	 * pulsada.
	 */
	private int modificadorDeTeclado;

	public EscuchaRaton(IModelo modelo, IControlador controlador,
			PanelMapa panel) {
		this.modelo = modelo;
		this.controlador = controlador;
		this.panel = panel;
		modificadorDeTeclado = 0;

	}

	/**
	 * Método que establece este esucha como el activo en el panel del mapa.
	 * <p>
	 * Este metodo primero estable el icono del esucha, para luego esablecer
	 * esta instancia como esucha del raton y del movimiento del raton.
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

	/**
	 * Método para obtener un nodo a partir de dos puntos en el panel del mapa.
	 * <p>
	 * Este método recorre la lista de nodos y busca el nodo que tiene una
	 * posicion (x,y) similar a la pasada como parámetro.
	 * 
	 * @param posX
	 *            Posicion a lo largo del eje x
	 * @param posY
	 *            Posicion a lo largo del eje y
	 * @return Nodo encontrado en la posicion dada o null
	 */
	public Nodo buscarNodo(int posX, int posY) {
		double error = 3;
		Iterator<Nodo> iter = modelo.getMapa().getNodos().iterator();
		Nodo sel = null;
		boolean encontrado = false;
		while (!encontrado && iter.hasNext()) {
			Nodo next = iter.next();
			int nodox = panel.getRepresentacion().x_MapaARep(
					next.getPos().getLon());
			int nodoy = panel.getRepresentacion().y_MapaARep(
					next.getPos().getLat());
			if ((nodoy - error <= posY) && (nodoy + error >= posY)
					&& (nodox - error <= posX) && (nodox + error >= posX)) {
				encontrado = true;
				sel = next;
			}
		}
		if (encontrado)
			return sel;
		else
			return null;
	}

	/**
	 * Método para obtener un tramo a partir de un punto en el panel del mapa.
	 * <p>
	 * Este método recorre la lista de tramos y busca el tramo que tiene una
	 * posicion (x,y) similar a la pasada como parámetro.
	 * 
	 * @param x
	 *            Posicion a lo largo del eje x
	 * @param y
	 *            Posicion a lo largo del eje y
	 * @return Tramo encontrado en la posicion dada o null
	 */
	public Tramo buscarTramo(int posX, int posY) {
		Iterator<Tramo> iter = modelo.getMapa().getTramos().iterator();
		Tramo sel = null;
		boolean encontrado = false;
		while (!encontrado && iter.hasNext()) {
			Tramo next = iter.next();
			Polygon p = panel.getRepresentacion().generarAreaTramo(next);
			if (p.contains(posX, posY)) {
				encontrado = true;
				sel = next;
			}
		}
		if (encontrado)
			return sel;
		else
			return null;
	}

	/**
	 * Permite a un oyente externo modificar el valor del modificador para poder
	 * a éste oyente generar condiciones especiales (Especialmente útil para el
	 * oyente externo de teclado).
	 * 
	 * @param modificador
	 */
	public void notificar(int modificador) {
		this.modificadorDeTeclado = modificador;
	}

	/**
	 * Da el valor por defecto al modicador de éste oyente.
	 */
	public void desnotificar() {
		this.modificadorDeTeclado = 0;
	}

	abstract public void mouseClicked(MouseEvent arg0);

	abstract public void mouseEntered(MouseEvent arg0);

	abstract public void mouseExited(MouseEvent arg0);

	abstract public void mousePressed(MouseEvent arg0);

	abstract public void mouseReleased(MouseEvent arg0);

	abstract public void mouseDragged(MouseEvent arg0);

	abstract public void mouseMoved(MouseEvent arg0);

	public int getModificadorDeTeclado() {
		return modificadorDeTeclado;
	}

	public void setModificadorDeTeclado(int modificadorDeTeclado) {
		this.modificadorDeTeclado = modificadorDeTeclado;
	}

}
