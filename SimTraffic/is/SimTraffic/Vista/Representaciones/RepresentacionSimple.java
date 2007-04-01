package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Vehiculo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Clase que extiende Representacion.
 * <p>
 * Esta clase implementa la representación de forma simple, pero con todas las
 * funciones necesarias para mostrar todos las opciones del osm y de la
 * simulación.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class RepresentacionSimple extends Representacion {
	/**
	 * Parámetro que establece el ancho de cada uno de los carriles a dibujar
	 */
	private double tamaño_carril = 2.5;

	/**
	 * Constructor por defecto de la RepresentacionSimple
	 */
	public RepresentacionSimple() {
		super();
	}

	/**
	 * Constructur de la clase que toma los parámetros de otra clase
	 * representacion.
	 * 
	 * @param rep
	 */
	public RepresentacionSimple(Representacion rep) {
		super(rep);
	}

	public void pintar(Graphics2D g, Nodo nodo) {
		g.setColor(Color.GRAY);
		g.fillOval(x_MapaARep(nodo.getPos().getLon()) - 5, y_MapaARep(nodo
				.getPos().getLat()) - 5, 10, 10);
		g.setColor(Color.RED);
		g.drawOval(x_MapaARep(nodo.getPos().getLon()) - 5, y_MapaARep(nodo
				.getPos().getLat()) - 5, 10, 10);
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

		double angulo = tramo.getAngulo();

		// genera los puntos (x,y) de las esquinas del poligono
		int x[] = {
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))),
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
						* carriles_vuelta * Math.cos(angulo)),
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_vuelta * Math.cos(angulo)) };
		// establece el color y dibuja el poligono
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(x, y, 4);

		// ahora dibujara las lineas
		g.setColor(Color.WHITE);
		float array[] = { 10, 10 };
		// primero la linea central
		g.drawLine(x_MapaARep(posnodo1.getLon()),
				y_MapaARep(posnodo1.getLat()), x_MapaARep(posnodo2.getLon()),
				y_MapaARep(posnodo2.getLat()));
		// luego una linea punteada por cada carril en un sentido y una normal
		// para el ultimo
		for (int i = 0; i < carriles_ida; i++) {
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
					/ zoom * (i + 1) * Math.sin(angulo)),
					(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
							* (i + 1) * (-Math.cos(angulo))),
					(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
							* (i + 1) * Math.sin(angulo)),
					(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
							* (i + 1) * (-Math.cos(angulo))));
		}
		// luego una linea punteada por cada carril en el otro y una normal para
		// el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
					/ zoom * -(i + 1) * Math.sin(angulo)),
					(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
							* -(i + 1) * (-Math.cos(angulo))),
					(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
							* -(i + 1) * Math.sin(angulo)),
					(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
							* -(i + 1) * (-Math.cos(angulo))));
		}
	}

	@Override
	public void pintar(Graphics2D g, Señal señal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pintarSugerencia(Graphics2D g, ElementoMapa elemento) {
		int tamaño = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				g.setColor(Color.RED);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
			}
			if (elemento.getClass() == Tramo.class) {
				// pintar un tramo sugerido
				Posicion posnodo1 = ((Tramo) elemento).getNodoInicial()
						.getPos();
				Posicion posnodo2 = ((Tramo) elemento).getNodoFinal().getPos();
				g.setColor(Color.RED);
				g.setStroke(new BasicStroke(6));
				g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
						.getLat()), x_MapaARep(posnodo2.getLon()),
						y_MapaARep(posnodo2.getLat()));
				g.setStroke(new BasicStroke(1));
			}
		}
	}

	/**
	 * Pinta una sugerencia de nodos y tramos para los elementos seleccionados
	 */
	public void pintarSugerenciaSeleccion(Graphics2D g, ElementoMapa elemento) {
		int tamaño = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				g.setColor(Color.yellow);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
				Color colorTransparente = new Color((float) 1, (float) 0.6,
						(float) 0, (float) 0.6);
				g.setColor(colorTransparente);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
				g.fillOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
			}
			if (elemento.getClass() == Tramo.class) {
				// pintar un tramo sugerido
				Tramo t = (Tramo) elemento;
				Polygon p = generarAreaTramo(t);
				Color colorTransparente = new Color((float) 0.1, (float) 0.8,
						(float) 0.05, (float) 0.6);
				g.setColor(colorTransparente);
				g.fillPolygon(p);
			}
		}
	}

	public Polygon generarAreaTramo(Tramo tramo) {
		// almacena posiciones de los nodos
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// almacena numero de carriles en cada sentido
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();

		double angulo = tramo.getAngulo();

		// genera los puntos (x,y) de las esquinas del poligono
		int x[] = {
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))),
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
						* carriles_vuelta * Math.cos(angulo)),
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_vuelta * Math.cos(angulo)) };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}

	@Override
	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo) {
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null) return;
		Posicion posnodo1 = vehiculo.getNodoOrigen().getPos();
		Posicion posnodo2 = vehiculo.getNodoDestino().getPos();
		// TODO si el tramo no se dibuja, puede ser bueno que ya no sigua
		g.setColor(Color.RED);
		int posX1, posY1, posX2, posY2, posX, posY;
		if (tramo.getNodoFinal() == vehiculo.getNodoDestino()) {
			posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * (-Math.sin(tramo.getAngulo())));
			posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * Math.cos(tramo.getAngulo()));
			posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * (-Math.sin(tramo.getAngulo())));
			posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * Math.cos(tramo.getAngulo()));
		} else {
			posX2 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * Math.sin(tramo.getAngulo()));
			posY2 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * (-Math.cos(tramo.getAngulo())));
			posX1 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * Math.sin(tramo.getAngulo()));
			posY1 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
					* (vehiculo.getCarril() - 0.5) * (-Math.cos(tramo.getAngulo())));
		}
		posX = posX1 + (int) ((posX2 - posX1) * vehiculo.getPosicion());
		posY = posY1 + (int) ((posY2 - posY1) * vehiculo.getPosicion());
		
		g.drawRect(posX, posY, 3, 3);
	}

}
