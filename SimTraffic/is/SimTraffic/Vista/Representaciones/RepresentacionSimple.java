package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Mapa;
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
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	 * Almacena las imagenes de los coches para dibujarlos de manera más
	 * eficiente.
	 */
	private BufferedImage coches[];

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
		String tipo = null;
		if (nodo.getTipo() != null)
			tipo = nodo.getTipo().getTipo();
		if (tipo == null) {
			g.setColor(Color.black);
		} else if (tipo.compareTo("leisure") == 0) {
			g.setColor(Color.green);
		} else if (tipo.compareTo("amenity") == 0) {
			g.setColor(Color.gray);
		} else if (tipo.compareTo("highway") == 0) {
			g.setColor(Color.blue);
		} else if (tipo.compareTo("manmade") == 0) {
			g.setColor(Color.pink);
		} else {
			g.setColor(Color.red);
		}

		g.drawRect(x_MapaARep(nodo.getPos().getLon()) - 2, y_MapaARep(nodo
				.getPos().getLat()) - 2, 4, 4);
	}

	public void pintar(Graphics2D g, Rectangle rectanguloSeleccion) {
		g.draw(rectanguloSeleccion);
		Color colorTransparente = new Color((float) 0.8, (float) 0.1,
				(float) 0.05, (float) 0.2);
		g.setColor(colorTransparente);
		g.fill(rectanguloSeleccion);
	}

	public void pintar(Graphics2D g, Tramo tramo, String tipo) {
		// almacena posiciones de los nodos
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();

		// almacena numero de carriles en cada sentido
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();

		double angulo = tramo.getAngulo();

		if (tipo == null) {
			g.setColor(Color.black);
		} else if (tipo.compareTo("leisure") == 0) {
			g.setColor(Color.green);
		} else if (tipo.compareTo("amenity") == 0) {
			g.setColor(Color.gray);
		} else if (tipo.compareTo("highway") == 0) {
			g.setColor(Color.blue);
		} else if (tipo.compareTo("manmade") == 0) {
			g.setColor(Color.pink);
		} else {
			g.setColor(Color.red);
		}
		g.drawLine(x_MapaARep(posnodo1.getLon()),
				y_MapaARep(posnodo1.getLat()), x_MapaARep(posnodo2.getLon()),
				y_MapaARep(posnodo2.getLat()));

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
				g.setColor(Color.yellow);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
				Color colorTransparente = new Color((float) 1, (float) 0.6,
						(float) 0, (float) 0.7);
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
				Tramo tramo = (Tramo) elemento;
				try {
					this.pintar(g, tramo, null);
					Polygon p = generarAreaTramo(tramo);
					Color colorTransparente = new Color((float) 1, (float) 0.5,
							(float) 0, (float) 0.7);
					g.setColor(colorTransparente);
					g.fillPolygon(p);
				} catch (ArithmeticException e) {

				}
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
				g.setColor(Color.red);// yellow
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tamaño / 2,
						y_MapaARep(nodo.getPos().getLat()) - tamaño / 2,
						tamaño, tamaño);
				Color colorTransparente = new Color((float) 1, (float) 0,
						(float) 0, (float) 0.6);// 1,0.6,0
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
		int temp1 = (int) (tamaño_carril / zoom * carriles_ida * Math
				.sin(angulo));
		int temp2 = (int) (-tamaño_carril / zoom * carriles_vuelta * Math
				.sin(angulo));
		int x[] = { x_MapaARep(posnodo1.getLon()) + temp1,
				x_MapaARep(posnodo2.getLon()) + temp1,
				x_MapaARep(posnodo2.getLon()) + temp2,
				x_MapaARep(posnodo1.getLon()) + temp2 };

		temp1 = (int) (tamaño_carril / zoom * carriles_ida * Math.cos(angulo));
		temp2 = (int) (-tamaño_carril / zoom * carriles_vuelta * Math
				.cos(angulo));
		int y[] = { y_MapaARep(posnodo1.getLat()) + temp1,
				y_MapaARep(posnodo2.getLat()) + temp1,
				y_MapaARep(posnodo2.getLat()) + temp2,
				y_MapaARep(posnodo1.getLat()) + temp2 };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.Representaciones.Representacion#pintarVehiculo(java.awt.Graphics2D,
	 *      is.SimTraffic.Simulacion.Vehiculo, is.SimTraffic.Mapa.Tramo)
	 */
	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo, Tramo tramo2) {
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null || !tramo2.equals(tramo))
			return;

		Shape rect;
		if (vehiculo.getNombre() == "Turismo")
			rect = new Rectangle2D.Double(-4, -tamaño_carril, 4,
					tamaño_carril);
		else if (vehiculo.getNombre() == "Camion") {
			rect = new Rectangle2D.Double(-6, -tamaño_carril, 6,
					tamaño_carril);
		}
		else {
			rect = new Rectangle2D.Double(-4, -tamaño_carril, 4,
					tamaño_carril);
		}
		// BufferedImage rect = coches[vehiculo.getId() % 7];

		// TODO si el tramo no se dibuja, puede ser bueno que ya no sigua

		if (vehiculo.getColor()==null)
			g.setColor(Color.RED);
		else {
			g.setColor(vehiculo.getColor());
		}

		int posX1, posY1, posX2, posY2, posX, posY;

		posX1 = x_MapaARep(tramo.getNodoInicial().getPos().getLon());
		posX2 = x_MapaARep(tramo.getNodoFinal().getPos().getLon());
		posY1 = y_MapaARep(tramo.getNodoInicial().getPos().getLat());
		posY2 = y_MapaARep(tramo.getNodoFinal().getPos().getLat());

		// rotacion de la imagen
		AffineTransform rot = new AffineTransform();

		// translacion en el mapa
		AffineTransform trans = new AffineTransform();

		// zoom y desplazamiento por carril
		AffineTransform zoom = new AffineTransform();
		if (vehiculo.getTramo().getNodoFinal() == vehiculo.getNodoDestino()) {
			rot.rotate(-vehiculo.getTramo().getAngulo());
			posX = posX1 + (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY1 + (int) ((posY2 - posY1) * vehiculo.getPosicion());
		} else {
			rot.rotate(Math.PI - vehiculo.getTramo().getAngulo());
			posX = posX2 - (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY2 - (int) ((posY2 - posY1) * vehiculo.getPosicion());
		}
		zoom.scale(1 / this.zoom, 1 / this.zoom);
		zoom.translate(0, tamaño_carril * vehiculo.getCarril());
		rot.concatenate(zoom);
		trans.translate(posX, posY);
		trans.concatenate(rot);
		AffineTransform temp = g.getTransform();
		g.transform(trans);
		g.draw(rect);
		// g.drawImage(rect, -5, -2, 4, 2, null);
		g.setTransform(temp);

		// g.drawRect(posX - 1, posY - 1, 2, 2);

	}

	@Override
	public Polygon generarTrianguloFlechaSugerencia(Nodo nodo, Tramo destino) 
	{
		Posicion pos; 
		double angulo;
		if (destino.getNodoFinal().equals(nodo))
		{
			pos = destino.getNodoInicial().getPos();
			angulo = Math.PI - destino.getAngulo();
		}
		else
		{
			pos = destino.getNodoFinal().getPos();
			angulo = Math.PI*2 - destino.getAngulo();
		}

		int carriles = Math.max(destino.getNumCarrilesDir1(), destino.getNumCarrilesDir2());
		
		int desplazamiento = 15;
		int temp3 = (int) (desplazamiento * Math.cos(Math.PI + angulo) / zoom);
		int temp4 = (int) (-desplazamiento * Math.sin(Math.PI + angulo) / zoom);

		angulo = Math.PI/2 + angulo;
		int temp1 = (int) (2 * tamaño_carril / zoom * carriles * Math.cos(angulo));
		
		int x[] = {x_MapaARep(pos.getLon()) - temp1 + temp3,
				x_MapaARep(pos.getLon()) + temp1 + temp3,
				x_MapaARep(pos.getLon()) - temp3};

		temp1 = (int) (2 * tamaño_carril / zoom * carriles * Math.sin(angulo));
		int y[] = {y_MapaARep(pos.getLat()) - temp1 - temp4,
				y_MapaARep(pos.getLat()) + temp1 - temp4,
				y_MapaARep(pos.getLat()) + temp4};
		
		System.out.println(x[0] + " " + x[1] + " " + x[2]);
		System.out.println(y[0] + " " + y[1] + " " + y[2]);
		
		Polygon p = new Polygon(x, y, 3);
		return p;
	}

}

