/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase (por ahora provisional) para representar simplemente el mapa en
 * pantalla.
 * <p>
 * Utiliza iteradores para recorrer los distintos componentes del mapa y
 * dibujarlos.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class PanelMapa extends JPanel {

	private static final long serialVersionUID = -3680412115222562074L;

	private IModelo modelo;

	private Image mapa;

	private int zoom;

	private int tamX, tamY;

	private boolean recrear;

	private int contador;

	/**
	 * Alamcena la representacion que se utiliza para mostrar el mapa por
	 * pantalla.
	 */
	private Representacion representacion;

	/**
	 * @param tamX
	 * @param tamY
	 */
	public PanelMapa(int tamX, int tamY) {
		super();
		setSize(new Dimension(tamX, tamY));
		modelo = null;
		zoom = 1;
		this.tamX = tamX;
		this.tamY = tamY;
		recrear = true;
		contador = 0;
		representacion = new RepresentacionSimple();
	}

	/**
	 * Para establecer el modelo donde esta la infromación.
	 * <p>
	 * Se utliza este método para establecer el modelo y no el contructor, dado
	 * que el modelo podría cambiar.
	 * 
	 * @param modelo
	 *            IModelo con el modelo a mostrar
	 */
	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
		recrear = true;
	}

	public void resize(int tamX, int tamY) {
		this.tamX = tamX;
		this.tamY = tamY;
		recrear = true;

	}

	/**
	 * Método que recera la imagen del mapa a partir de la infromación del
	 * modelo.
	 * <p>
	 * 
	 */
	public void recrearMapa() {
		if (modelo == null)
			return;
		Dimension tamaño = this.getSize();
		tamX = tamaño.width;
		tamY = tamaño.height;
		representacion.setTamX(tamX);
		representacion.setTamY(tamY);
		if (tamX == 0 || tamY == 0)
			mapa = this.createImage(200, 200);
		else
			mapa = this.createImage(tamX, tamY);
		Graphics2D g = (Graphics2D) mapa.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// g.drawOval(20, 20, 4, 4);
		Iterator<Tramo> itramos = modelo.getMapa().getTramos().iterator();
		while (itramos.hasNext()) {
			representacion.pintar(g, itramos.next());
		}
		Iterator<Nodo> inodos = modelo.getMapa().getNodos().iterator();
		while (inodos.hasNext()) {
			representacion.pintar(g, inodos.next());
		}
		contador++;
	}

	public void repaint() {
		recrear = true;
		super.repaint();
	}

	public void paintComponent(Graphics g) {
		if (modelo == null)
			return;
		if (recrear) {
			mapa = super.createImage(tamX, tamY);
			recrearMapa();
			recrear = false;
		}
		g.drawImage(mapa, 0, 0, null);
		g.drawString("redibujardo: " + contador, 80, 80);
	}

	public void setRepresentacion(Representacion representacion) {
		this.representacion = representacion;
	}
}
