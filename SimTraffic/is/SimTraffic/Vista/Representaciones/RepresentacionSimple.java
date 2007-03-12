package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.ConversorUTM;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.text.DecimalFormat;

public class RepresentacionSimple extends Representacion {
	/**
	 * Par�metro que establece el ancho de cada uno de los carriles a dibujar
	 */
	private int tama�o_carril;

	/**
	 * Constructor por defecto de la RepresentacionSimple
	 */
	public RepresentacionSimple() {
		super();
		tama�o_carril = 5;
	}

	public void pintar(Graphics2D g, Nodo nodo) {
		g.setColor(Color.GRAY);
		g.fillOval(x_MapaARep(nodo.getPos().getPosX()) - 5, y_MapaARep(nodo
				.getPos().getPosY()) - 5, 10, 10);
		g.setColor(Color.RED);
		g.drawOval(x_MapaARep(nodo.getPos().getPosX()) - 5, y_MapaARep(nodo
				.getPos().getPosY()) - 5, 10, 10);
	}

	public void pintar(Graphics2D g, Rectangle rectanguloSeleccion) {
		g.draw(rectanguloSeleccion);
		Color colorTransparente = new Color((float) 0.8, (float) 0.1,
				(float) 0.05, (float) 0.2);
		g.setColor(colorTransparente);
		g.fill(rectanguloSeleccion);
	}

	public void pintar(Graphics2D g, Tramo tramo) {
		// almacena posiciones de los nodos
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// almacena numero de carriles en cada sentido
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		// determina largo y alto del tramo y calcula el angulo en base a estos
		double largo = posnodo1.getPosX() - posnodo2.getPosX();
		double alto = posnodo1.getPosY() - posnodo2.getPosY();
		double angulo = Math.atan(alto / largo);

		// genera los puntos (x,y) de las esquinas del poligono
		int x[] = {
				x_MapaARep((int) (posnodo1.getPosX() + tama�o_carril
						* carriles_ida * Math.sin(angulo))),
				x_MapaARep((int) (posnodo2.getPosX() + tama�o_carril
						* carriles_ida * Math.sin(angulo))),
				x_MapaARep((int) (posnodo2.getPosX() + tama�o_carril
						* carriles_vuelta * (-Math.sin(angulo)))),
				x_MapaARep((int) (posnodo1.getPosX() + tama�o_carril
						* carriles_vuelta * (-Math.sin(angulo)))) };
		int y[] = {
				y_MapaARep((int) (posnodo1.getPosY() + tama�o_carril
						* carriles_ida * (-Math.cos(angulo)))),
				y_MapaARep((int) (posnodo2.getPosY() + tama�o_carril
						* carriles_ida * (-Math.cos(angulo)))),
				y_MapaARep((int) (posnodo2.getPosY() + tama�o_carril
						* carriles_vuelta * Math.cos(angulo))),
				y_MapaARep((int) (posnodo1.getPosY() + tama�o_carril
						* carriles_vuelta * Math.cos(angulo))) };
		// establece el color y dibuja el poligono
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(x, y, 4);

		// ahora dibujara las lineas
		g.setColor(Color.WHITE);
		float array[] = { 10, 10 };
		// primero la linea central
		g.drawLine(x_MapaARep(posnodo1.getPosX()), y_MapaARep(posnodo1
				.getPosY()), x_MapaARep(posnodo2.getPosX()),
				y_MapaARep(posnodo2.getPosY()));
		// luego una linea punteada por cada carril en un sentido y una normal
		// para el ultimo
		for (int i = 0; i < carriles_ida; i++) {
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(x_MapaARep((int) (posnodo1.getPosX() + tama�o_carril
					* (i + 1) * Math.sin(angulo))),
					y_MapaARep((int) (posnodo1.getPosY() + tama�o_carril
							* (i + 1) * (-Math.cos(angulo)))),
					x_MapaARep((int) (posnodo2.getPosX() + tama�o_carril
							* (i + 1) * Math.sin(angulo))),
					y_MapaARep((int) (posnodo2.getPosY() + tama�o_carril
							* (i + 1) * (-Math.cos(angulo)))));
		}
		// luego una linea punteada por cada carril en el otro y una normal para
		// el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(x_MapaARep((int) (posnodo1.getPosX() + tama�o_carril
					* -(i + 1) * Math.sin(angulo))),
					y_MapaARep((int) (posnodo1.getPosY() + tama�o_carril
							* -(i + 1) * (-Math.cos(angulo)))),
					x_MapaARep((int) (posnodo2.getPosX() + tama�o_carril
							* -(i + 1) * Math.sin(angulo))),
					y_MapaARep((int) (posnodo2.getPosY() + tama�o_carril
							* -(i + 1) * (-Math.cos(angulo)))));
		}
	}

	@Override
	public void pintar(Graphics2D g, Se�al se�al) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pintarSugerencia(Graphics2D g, ElementoMapa elemento) {
		int tama�o = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				g.setColor(Color.RED);
				g.drawOval(x_MapaARep(nodo.getPos().getPosX()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getPosY()) - tama�o / 2,
						tama�o, tama�o);
			}
			if (elemento.getClass() == Tramo.class) {
				// pintar un tramo sugerido
				Posicion posnodo1 = ((Tramo) elemento).getNodoInicial()
						.getPos();
				Posicion posnodo2 = ((Tramo) elemento).getNodoFinal().getPos();
				g.setColor(Color.RED);
				g.setStroke(new BasicStroke(6));
				g.drawLine(x_MapaARep(posnodo1.getPosX()), y_MapaARep(posnodo1
						.getPosY()), x_MapaARep(posnodo2.getPosX()),
						y_MapaARep(posnodo2.getPosY()));
				g.setStroke(new BasicStroke(1));
			}
		}
	}

	/**
	 * M�todo para determinar el �rea ocupada por un tramo. Devuelve un pol�gono
	 * que representa dicha �rea.
	 * 
	 * @param tramo
	 *            El tramo del que se quiere conocer el �rea que ocupa.
	 * @return El pol�gono que rodea al tramo.
	 */
	public Polygon generarAreaTramo(Tramo tramo) {
		// almacena posiciones de los nodos inicial y final del tramo.
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// almacena numero de carriles en cada sentido.
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		// determina largo y alto del tramo y calcula el angulo del tramo en
		// base a estos datos.
		double largo = posnodo1.getPosX() - posnodo2.getPosX();
		double alto = posnodo1.getPosY() - posnodo2.getPosY();
		double angulo = Math.atan(alto / largo);
		// Coordenadas de los puntos del rect�ngulo que representa al tramo.
		int x[] = {
				(int) (posnodo1.getPosX() + tama�o_carril * carriles_ida
						* Math.sin(angulo)),
				(int) (posnodo2.getPosX() + tama�o_carril * carriles_ida
						* Math.sin(angulo)),
				(int) (posnodo2.getPosX() + tama�o_carril * carriles_vuelta
						* (-Math.sin(angulo))),
				(int) (posnodo1.getPosX() + tama�o_carril * carriles_vuelta
						* (-Math.sin(angulo))) };
		int y[] = {
				(int) (posnodo1.getPosY() + tama�o_carril * carriles_ida
						* (-Math.cos(angulo))),
				(int) (posnodo2.getPosY() + tama�o_carril * carriles_ida
						* (-Math.cos(angulo))),
				(int) (posnodo2.getPosY() + tama�o_carril * carriles_vuelta
						* Math.cos(angulo)),
				(int) (posnodo1.getPosY() + tama�o_carril * carriles_vuelta
						* Math.cos(angulo)) };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}

	@Override
	public void pintarCoordenadas(Graphics2D g) {
		float array[] = { 10, 5, 5, 5 };
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 1, array, 1));
		DecimalFormat cincoCifras = new DecimalFormat("0.00000");

		for (int i = 0; i < 30; i++) {
			int posx = x_MapaARep((posX0 - posX0 % (int) (100 * zoom))
					+ (int) (100 * i * zoom));
			g.drawLine(posx, 13, posx, 2000);
			// g.drawString(""+x_RepAMapa(posx), posx - 10, 12);

			int posy = y_MapaARep((- posY0 + posY0 % (int) (100 * zoom))
					- (int) (100 * i * zoom));
			g.drawLine(0, posy, 2000, posy);
			// g.drawString(""+y_RepAMapa(posy),3 , posy - 4);

			double[] latlon = ConversorUTM.UTMXYToLatLon(x_RepAMapa(posx),
					y_RepAMapa(posy), 1, false);
			g.drawString("" + cincoCifras.format(latlon[1]) + " �", posx - 10,12);
			g.drawString("" + cincoCifras.format(latlon[0]) + " �", 3,posy - 4);

		}
		g.setStroke(new BasicStroke(1));
		g.drawLine(25, 40, 25 + (int) (50*zoom), 40);
		g.drawLine(25, 35, 25, 45);
		g.drawLine(25 + (int) (50*zoom), 35, 25 + (int) (50*zoom), 45);
		g.drawString("50 m", 40, 35);
	}
}
