/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Iterator;

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

	private int tama�o_carril;
	
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
		contador = 0;
		tama�o_carril = 5;
	}

	/**
	 * Para establecer el modelo donde esta la infromaci�n.
	 * <p>
	 * Se utliza este m�todo para establecer el modelo y no el contructor, dado
	 * que el modelo podr�a cambiar.
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
	 * M�todo que recera la imagen del mapa a partir de la infromaci�n del
	 * modelo.
	 * <p>
	 * 
	 */
	public void recrearMapa() {
		if (modelo == null)
			return;
		Dimension tama�o = this.getSize();
		tamX = tama�o.width;
		tamY = tama�o.height;
		if (tamX == 0 || tamY == 0)
			mapa = this.createImage(200, 200);
		else
			mapa = this.createImage(tamX, tamY);
		Graphics2D g = (Graphics2D)mapa.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g.drawOval(20, 20, 4, 4);
		Iterator<Tramo> itramos = modelo.getMapa().getTramos().iterator();
		while (itramos.hasNext()) {
			pintar(g, itramos.next());
		}
		Iterator<Nodo> inodos = modelo.getMapa().getNodos().iterator();
		while (inodos.hasNext()) {
			pintar(g, inodos.next());
		}
		contador++;
	}

	/**
	 * M�todo que recalula la posicion en X de un punto del mapa en la pantalla.
	 * <p>
	 * Esta funcion por ahora solo devuelve el punto como esta, pero tendra que
	 * tener en cuenta el zoom, la posicion de la ventana respecto al mapa y
	 * otra informaci�n pertinente.
	 * 
	 * @param posX
	 *            Entero que representa la posicion en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int recalculaX(int posX) {
		/*
		 * if (modelo.getMapa() .getMaxX() - modelo.getMapa().getMinX() == 0)
		 * return (int) (posX); return (int) ((posX -
		 * modelo.getMapa().getMinX()) / (modelo.getMapa() .getMaxX() -
		 * modelo.getMapa().getMinX())) this.getWidth();
		 */
		return posX;
	}

	/**
	 * M�todo que recalula la posicion en Y de un punto del mapa en la pantalla.
	 * <p>
	 * Esta funcion por ahora solo devuelve el punto como esta, pero tendra que
	 * tener en cuenta el zoom, la posicion de la ventana respecto al mapa y
	 * otra informaci�n pertinente.
	 * 
	 * @param posX
	 *            Entero que representa la posicion en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int recalculaY(int posY) {
		/*
		 * if (modelo.getMapa() .getMaxY() - modelo.getMapa().getMinY() == 0)
		 * return (int) (posY); return (int) ((posY -
		 * modelo.getMapa().getMinY()) / (modelo.getMapa() .getMaxY() -
		 * modelo.getMapa().getMinY())) this.getHeight();
		 */return posY;
	}

	public void pintar(Graphics g, Nodo nodo) 
	{	
		g.setColor(Color.DARK_GRAY);
		g.fillOval(recalculaX(nodo.getPos().getPosX()), recalculaY(nodo
				.getPos().getPosY()), 6, 6);
		g.setColor(Color.RED);
		g.drawOval(recalculaX(nodo.getPos().getPosX()), recalculaY(nodo
				.getPos().getPosY()), 6, 6);
	}

	public void pintar(Graphics2D g, Tramo tramo) {
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		double largo = posnodo1.getPosX() - posnodo2.getPosX();
		double alto = posnodo1.getPosY() - posnodo2.getPosY();
		double angulo = Math.atan(alto/largo);
		int x[] = {(int) (3 + posnodo1.getPosX() + tama�o_carril * carriles_ida * Math.sin(angulo)),
				   (int) (3 + posnodo2.getPosX() + tama�o_carril * carriles_vuelta * Math.sin(angulo)),
				   (int) (3 + posnodo2.getPosX() + tama�o_carril * carriles_vuelta * (-Math.sin(angulo))),
				   (int) (3 + posnodo1.getPosX() + tama�o_carril * carriles_ida * (-Math.sin(angulo)))};
		int y[] = {(int) (3 + posnodo1.getPosY() + tama�o_carril * carriles_ida * (-Math.cos(angulo))),
				   (int) (3 + posnodo2.getPosY() + tama�o_carril * carriles_vuelta * (-Math.cos(angulo))),
				   (int) (3 + posnodo2.getPosY() + tama�o_carril * carriles_vuelta * Math.cos(angulo)),
				   (int) (3 + posnodo1.getPosY() + tama�o_carril * carriles_ida * Math.cos(angulo))};
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(x, y, 4);
		g.setColor(Color.WHITE);
		float array[] = {10,10};
		g.drawLine(recalculaX(posnodo1.getPosX() + 3), recalculaY(posnodo1
				.getPosY() + 3), recalculaX(posnodo2.getPosX() + 3),
				recalculaY(posnodo2.getPosY()) + 3);
		for (int i = 0; i < carriles_ida; i++)
		{
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (3 + posnodo1.getPosX() + tama�o_carril * (i + 1) * Math.sin(angulo))), 
					   recalculaY((int) (3 + posnodo1.getPosY() + tama�o_carril * (i + 1) * (-Math.cos(angulo)))), 
					   recalculaX((int) (3 + posnodo2.getPosX() + tama�o_carril * (i + 1) * Math.sin(angulo))),
					   recalculaY((int) (3 + posnodo2.getPosY() + tama�o_carril * (i + 1) * (-Math.cos(angulo)))));
		}
		for (int i = 0; i < carriles_vuelta; i++)
		{
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (3 + posnodo1.getPosX() + tama�o_carril * -(i + 1) * Math.sin(angulo))), 
					   recalculaY((int) (3 + posnodo1.getPosY() + tama�o_carril * -(i + 1) * (-Math.cos(angulo)))), 
					   recalculaX((int) (3 + posnodo2.getPosX() + tama�o_carril * -(i + 1) * Math.sin(angulo))),
					   recalculaY((int) (3 + posnodo2.getPosY() + tama�o_carril * -(i + 1) * (-Math.cos(angulo)))));
		}
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
		g.drawString("redibujardo: "+contador, 80, 80);
	}

}