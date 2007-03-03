/**
 * 
 */
package is.SimTraffic.Vista;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.*;

import javax.swing.JPanel;

/**
 * Clase (por ahora provisional) para representar simplemente el mapa en pantalla.<p>
 * Utiliza iteradores para recorrer los distintos componentes del mapa y dibujarlos.
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

	/**
	 * @param tamX
	 * @param tamY
	 */
	public PanelMapa(int tamX, int tamY) {
		super();
		setSize(tamX, tamY);
		modelo = null;
		zoom = 0;
		this.tamX = tamX;
		this.tamY = tamY;
		recrear = true;
	}

	/**
	 * Para establecer el modelo donde esta la infromación.<p>
	 * Se utliza este método para establecer el modelo y no el contructor, dado que
	 * el modelo podría cambiar.
	 * @param modelo
	 * IModelo con el modelo a mostrar
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

	public void recrearMapa() {
		if (modelo == null)
			return;
		mapa = this.createImage(tamX, tamY);
		Graphics g = mapa.getGraphics();
		Iterator<Nodo> inodos = modelo.getMapa().getNodos().iterator();
		while (inodos.hasNext()) {
			pintar(g, inodos.next());
		}
		Iterator<Tramo> itramos = modelo.getMapa().getTramos().iterator();
		while(itramos.hasNext()) {
			pintar(g, itramos.next());
		}
	}

	public int recalculaX(int posX) {
		return (int) ((posX - modelo.getMapa().getMinX()) / (modelo.getMapa()
				.getMaxX() - modelo.getMapa().getMinX()))
				* this.getWidth();
	}

	public int recalculaY(int posY) {
		return (int) ((posY - modelo.getMapa().getMinY()) / (modelo.getMapa()
				.getMaxY() - modelo.getMapa().getMinY()))
				* this.getHeight();
	}

	public void pintar(Graphics g, Nodo nodo) {
		g.drawOval(recalculaX(nodo.getPos().getPosX()), recalculaY(nodo
				.getPos().getPosY()), 4, 4);
	}

	public void pintar(Graphics g, Tramo tramo) {
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoInicial().getPos();
		g.drawLine(recalculaX(posnodo1.getPosX()), recalculaY(posnodo1
				.getPosY()), recalculaX(posnodo2.getPosX()),
				recalculaY(posnodo2.getPosY()));

	}

	public void paintComponent(Graphics g) {
		if (modelo == null)
			return;
		if (recrear) {
			mapa = super.createImage(tamX, tamY);
			recrearMapa();
		}
		this.getGraphics().drawImage(mapa, 0, 0, null);

	}

}
