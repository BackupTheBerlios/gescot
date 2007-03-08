/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.AccionScroll;
import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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

	/**
	 * Almacena el modelo que se va a mostrar por pantalla
	 */
	private IModelo modelo;

	/**
	 * Imagen del mapa usada para duplicar el buffer
	 */
	private Image mapa;

	/**
	 * Zoom que se esta utilizando en la representacion del mapa
	 */
	private float zoom;

	/**
	 * Almacena tamaños en x e y del panel, utilizados a la hora de dibujar
	 */
	private int tamX, tamY;

	private int posX, posY;

	/**
	 * Booleano que indica si se debe recreear o no el mapa la proxima vez que
	 * se dibjue el panel en pantalla
	 */
	private boolean recrear;

	/**
	 * Contador de las veces que se dibujo el mapa, para efectos de evaluacion
	 */
	private int contador;

	/**
	 * Elemento a mostrar como sugerido, segun donde esta el puntero del
	 * usuario. Para operaciones de modificacion/borrado/etc
	 */
	private ElementoMapa sugerencia;

	/**
	 * Alamcena la representacion que se utiliza para mostrar el mapa por
	 * pantalla.
	 */
	private Representacion representacion;

	private JScrollBar largo;

	private JScrollBar alto;

	/**
	 * Constructor de la clase PanelMapa.
	 * <p>
	 * Este constructor toma como parámetros el tamaño que se le quiere dar
	 * inicialmente
	 * 
	 * @param tamX
	 * @param tamY
	 */
	public PanelMapa(int tamX, int tamY) {
		super();
		setSize(new Dimension(200, 200));
		modelo = null;
		zoom = 1;
		this.tamX = tamX;
		this.tamY = tamY;
		recrear = true;
		contador = 0;
		representacion = new RepresentacionSimple();
		this.setLayout(new BorderLayout());
		añadirScrolls();
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

	/*
	 * public void setSize(int tamX, int tamY) { this.tamX = tamX; this.tamY =
	 * tamY; recrear = true; super.setSize(tamX, tamY); }
	 */
	public void añadirScrolls() {
		alto = new JScrollBar();
		alto.setMinimum(0);
		alto.setMaximum(100);
		alto.addAdjustmentListener(new AccionScroll(this));
		this.add(alto, BorderLayout.EAST);
		largo = new JScrollBar();
		largo.setOrientation(0);
		largo.setMinimum(0);
		largo.setMaximum(100);
		largo.addAdjustmentListener(new AccionScroll(this));
		this.add(largo, BorderLayout.SOUTH);
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
		posX = largo.getValue();
		posY = alto.getValue();
		representacion.setTamX(tamX);
		representacion.setTamY(tamY);
		representacion.setPosX0(posX);
		representacion.setPosY0(posY);
		representacion.setZoom(zoom);
		if (tamX == 0 || tamY == 0)
			mapa = this.createImage(200, 200);
		else
			mapa = this.createImage(tamX, tamY);
		Graphics2D g = (Graphics2D) mapa.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
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
		if (tamX != getSize().width || tamY != getSize().height
				|| ((largo != null) && posX != largo.getValue())
				|| ((alto != null) && posY != alto.getValue()))
			recrear = true;
		super.repaint();
	}

	public void paintComponent(Graphics g_normal) {
		Graphics2D g = (Graphics2D) g_normal;
		if (recrear) {
			// mapa = super.createImage(tamX, tamY);
			recrearMapa();
			recrear = false;
		}
		g.drawImage(mapa, 0, 0, null);
		representacion.pintarSugerencia(g, sugerencia);
		g.drawString("redibujardo: " + contador, 80, 80);
	}

	public void setRepresentacion(Representacion representacion) {
		this.representacion = representacion;
	}

	public Representacion getRepresentacion() {
		return representacion;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public void sugerir(ElementoMapa sugerencia) {
		this.sugerencia = sugerencia;
		this.repaint();
	}

	public int x_RepAMapa(int posX) {
		return representacion.x_RepAMapa(posX);
	}

	public int y_RepAMapa(int posY) {
		return representacion.y_RepAMapa(posY);
	}
}
