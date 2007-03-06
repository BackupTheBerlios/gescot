package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class RepresentacionSimple extends Representacion {
	private int tamaño_carril;

	public RepresentacionSimple() {
		super();
		tamaño_carril = 5;
	}

	public void pintar(Graphics2D g, Nodo nodo) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(recalculaX(nodo.getPos().getPosX()) - 3, recalculaY(nodo
				.getPos().getPosY()) - 3, 6, 6);
		g.setColor(Color.RED);
		g.drawOval(recalculaX(nodo.getPos().getPosX()) - 3, recalculaY(nodo
				.getPos().getPosY()) - 3, 6, 6);
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
				(int) (posnodo1.getPosX() + tamaño_carril * carriles_ida
						* Math.sin(angulo)),
				(int) (posnodo2.getPosX() + tamaño_carril * carriles_ida
						* Math.sin(angulo)),
				(int) (posnodo2.getPosX() + tamaño_carril * carriles_vuelta
						* (-Math.sin(angulo))),
				(int) (posnodo1.getPosX() + tamaño_carril * carriles_vuelta
						* (-Math.sin(angulo))) };
		int y[] = {
				(int) (posnodo1.getPosY() + tamaño_carril * carriles_ida
						* (-Math.cos(angulo))),
				(int) (posnodo2.getPosY() + tamaño_carril * carriles_ida
						* (-Math.cos(angulo))),
				(int) (posnodo2.getPosY() + tamaño_carril * carriles_vuelta
						* Math.cos(angulo)),
				(int) (posnodo1.getPosY() + tamaño_carril * carriles_vuelta
						* Math.cos(angulo)) };
		// establece el color y dibuja el poligono
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(x, y, 4);

		// ahora dibujara las lineas
		g.setColor(Color.WHITE);
		float array[] = { 10, 10 };
		//primero la linea central
		g.drawLine(recalculaX(posnodo1.getPosX()), recalculaY(posnodo1
				.getPosY()), recalculaX(posnodo2.getPosX()),
				recalculaY(posnodo2.getPosY()));
		//luego una linea punteada por cada carril en un sentido y una normal para el ultimo
		for (int i = 0; i < carriles_ida; i++) {
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (posnodo1.getPosX() + tamaño_carril
					* (i + 1) * Math.sin(angulo))),
					recalculaY((int) (posnodo1.getPosY() + tamaño_carril
							* (i + 1) * (-Math.cos(angulo)))),
					recalculaX((int) (posnodo2.getPosX() + tamaño_carril
							* (i + 1) * Math.sin(angulo))),
					recalculaY((int) (posnodo2.getPosY() + tamaño_carril
							* (i + 1) * (-Math.cos(angulo)))));
		}
		//luego una linea punteada por cada carril en el otro y una normal para el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (posnodo1.getPosX() + tamaño_carril
					* -(i + 1) * Math.sin(angulo))),
					recalculaY((int) (posnodo1.getPosY() + tamaño_carril
							* -(i + 1) * (-Math.cos(angulo)))),
					recalculaX((int) (posnodo2.getPosX() + tamaño_carril
							* -(i + 1) * Math.sin(angulo))),
					recalculaY((int) (posnodo2.getPosY() + tamaño_carril
							* -(i + 1) * (-Math.cos(angulo)))));
		}
	}

	@Override
	public void pintar(Graphics2D g, Señal señal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pintarSugerencia(Graphics2D g, ElementoMapa elemento) {			
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				Nodo nodo = (Nodo) elemento;
				g.setColor(Color.RED);
				g.drawOval(recalculaX(nodo.getPos().getPosX()) - 5, recalculaY(nodo
						.getPos().getPosY()) - 5, 10, 10);
			}		}
	}

}
