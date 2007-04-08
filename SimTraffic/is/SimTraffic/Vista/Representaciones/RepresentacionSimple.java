package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Vehiculo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase que extiende Representacion.
 * <p>
 * Esta clase implementa la representaci�n de forma simple, pero con todas las
 * funciones necesarias para mostrar todos las opciones del osm y de la
 * simulaci�n.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class RepresentacionSimple extends Representacion {
	/**
	 * Par�metro que establece el ancho de cada uno de los carriles a dibujar
	 */
	private double tama�o_carril = 2.5;
	
	/** 
	 * Almacena las imagenes de los coches para dibujarlos de manera m�s eficiente.
	 * */
	private BufferedImage coches[];

	/**
	 * Constructor por defecto de la RepresentacionSimple
	 */
	public RepresentacionSimple() 
	{	
		super();
		inicializarImagenes();
	}

	/**
	 * Constructur de la clase que toma los par�metros de otra clase
	 * representacion.
	 * 
	 * @param rep
	 */
	public RepresentacionSimple(Representacion rep) 
	{
		super(rep);
		inicializarImagenes();
	}

	/** Inicializa las imagenes de los coches.*/
	private void inicializarImagenes() 
	{
		coches = new BufferedImage[7];
		try {
			coches[0] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche1.png"));
			coches[1] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche2.png"));
			coches[2] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche3.png"));
			coches[3] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche4.png"));
			coches[4] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche5.png"));
			coches[5] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche6.png"));
			coches[6] = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Coche7.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void pintar(Graphics2D g, Nodo nodo) {
		/*
		 * g.setColor(Color.GRAY); g.fillOval(x_MapaARep(nodo.getPos().getLon()) -
		 * 5, y_MapaARep(nodo .getPos().getLat()) - 5, 10, 10);
		 * g.setColor(Color.RED); g.drawOval(x_MapaARep(nodo.getPos().getLon()) -
		 * 5, y_MapaARep(nodo .getPos().getLat()) - 5, 10, 10);
		 */
		g.setColor(Color.RED);
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
				(int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))),
				(int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril / zoom
						* carriles_vuelta * Math.cos(angulo)),
				(int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril / zoom
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
		int posx1 = x_MapaARep(posnodo1.getLon());
		int posx2 = x_MapaARep(posnodo2.getLon());
		int dist = posx2 - posx1;
		int delta = (int) 5 * dist / tramo.getLargo();
		posx2 = (int) (posx1 + dist - delta);
		posx1 = (int) (posx1 + delta);
		int posy1 = y_MapaARep(posnodo1.getLat());
		int posy2 = y_MapaARep(posnodo2.getLat());
		dist = posy2 - posy1;
		delta = (int) 5 * dist / tramo.getLargo();
		posy2 = (int) (posy1 + dist - delta);
		posy1 = (int) (posy1 + delta);

		for (int i = 0; i < carriles_ida; i++) {
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (posx1 + tama�o_carril / zoom * (i + 1)
					* Math.sin(angulo)), (int) (posy1 + tama�o_carril / zoom
					* (i + 1) * (-Math.cos(angulo))),
					(int) (posx2 + tama�o_carril / zoom * (i + 1)
							* Math.sin(angulo)), (int) (posy2 + tama�o_carril
							/ zoom * (i + 1) * (-Math.cos(angulo))));
		}
		// luego una linea punteada por cada carril en el otro y una normal para
		// el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (posx1 + tama�o_carril / zoom * -(i + 1)
					* Math.sin(angulo)), (int) (posy1 + tama�o_carril / zoom
					* -(i + 1) * (-Math.cos(angulo))),
					(int) (posx2 + tama�o_carril / zoom * -(i + 1)
							* Math.sin(angulo)), (int) (posy2 + tama�o_carril
							/ zoom * -(i + 1) * (-Math.cos(angulo))));
		}
		g.drawLine((int) (posx1 + tama�o_carril / zoom * -(carriles_vuelta)
				* Math.sin(angulo)), (int) (posy1 + tama�o_carril / zoom
				* -(carriles_vuelta ) * (-Math.cos(angulo))),
				(int) (posx1 + tama�o_carril / zoom * (carriles_ida )
						* Math.sin(angulo)), (int) (posy1 + tama�o_carril
						/ zoom * (carriles_ida ) * (-Math.cos(angulo))));
		g.drawLine((int) (posx2 + tama�o_carril / zoom * -(carriles_vuelta )
				* Math.sin(angulo)), (int) (posy2 + tama�o_carril / zoom
				* -(carriles_vuelta ) * (-Math.cos(angulo))),
				(int) (posx2 + tama�o_carril / zoom * (carriles_ida )
						* Math.sin(angulo)), (int) (posy2 + tama�o_carril
						/ zoom * (carriles_ida ) * (-Math.cos(angulo))));
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
				g.setColor(Color.yellow);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
				Color colorTransparente = new Color((float) 1, (float) 0.6,
						(float) 0, (float) 0.7);
				g.setColor(colorTransparente);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
				g.fillOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);

			}
			if (elemento.getClass() == Tramo.class) {
				// pintar un tramo sugerido
				Tramo tramo = (Tramo) elemento;
				try {
					this.pintar(g, tramo);
					Polygon p = generarAreaTramo(tramo);
					Color colorTransparente = new Color((float) 1, (float) 0.5,
						(float) 0, (float) 0.7);
					g.setColor(colorTransparente);
					g.fillPolygon(p);
				}
				catch(ArithmeticException e) {
					
				}
			}
		}
	}

	/**
	 * Pinta una sugerencia de nodos y tramos para los elementos seleccionados
	 */
	public void pintarSugerenciaSeleccion(Graphics2D g, ElementoMapa elemento) {
		int tama�o = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				g.setColor(Color.red);// yellow
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
				Color colorTransparente = new Color((float) 1, (float) 0,
						(float) 0, (float) 0.6);// 1,0.6,0
				g.setColor(colorTransparente);
				g.drawOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
				g.fillOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
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
				(int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril / zoom
						* carriles_ida * Math.sin(angulo)),
				(int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))),
				(int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril / zoom
						* carriles_vuelta * (-Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril / zoom
						* carriles_ida * (-Math.cos(angulo))),
				(int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril / zoom
						* carriles_vuelta * Math.cos(angulo)),
				(int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril / zoom
						* carriles_vuelta * Math.cos(angulo)) };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}

	@Override
	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo) {
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null)
			return;

		//Shape rect = new Rectangle2D.Double(-5, -2, 5,2);
		BufferedImage rect = coches[vehiculo.getId() % 7];

		
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// TODO si el tramo no se dibuja, puede ser bueno que ya no sigua
		g.setColor(Color.PINK);
		int posX1, posY1, posX2, posY2, posX, posY;
		if (tramo.getNodoFinal() == vehiculo.getNodoDestino()) {
			if ((tramo.getNodoInicial().getPos().getLon() - tramo
					.getNodoFinal().getPos().getLon()) < 0) {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* (-Math.sin(tramo.getAngulo())));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* Math.cos(tramo.getAngulo()));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* (-Math.sin(tramo.getAngulo())));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* Math.cos(tramo.getAngulo()));
			} else {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() - 1 )
						* Math.sin(tramo.getAngulo()));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* (-Math.cos(tramo.getAngulo())));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* Math.sin(tramo.getAngulo()));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* (-Math.cos(tramo.getAngulo())));
			}
			posX = posX1 + (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY1 + (int) ((posY2 - posY1) * vehiculo.getPosicion());
		} else {
			if ((tramo.getNodoInicial().getPos().getLon() - tramo
					.getNodoFinal().getPos().getLon()) > 0) {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* (-Math.sin(tramo.getAngulo())));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  )
						* Math.cos(tramo.getAngulo()));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* (-Math.sin(tramo.getAngulo())));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() )
						* Math.cos(tramo.getAngulo()));
			} else {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* Math.sin(tramo.getAngulo()));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* (-Math.cos(tramo.getAngulo())));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tama�o_carril
						/ zoom * (vehiculo.getCarril()  - 1)
						* Math.sin(tramo.getAngulo()));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tama�o_carril
						/ zoom * (vehiculo.getCarril() - 1 )
						* (-Math.cos(tramo.getAngulo())));
			}
			posX = posX2 + (int) ((posX1 - posX2) * vehiculo.getPosicion());
			posY = posY2 + (int) ((posY1 - posY2) * vehiculo.getPosicion());
		}
		AffineTransform rot = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		AffineTransform zoom = new AffineTransform();
		zoom.scale(1/this.zoom, 1/this.zoom);
		rot.rotate(vehiculo.getTramo().getAngulo());
		rot.concatenate(zoom);
		trans.translate(posX, posY);
		trans.concatenate(rot);
		AffineTransform temp = g.getTransform();
		g.transform(trans);
		//g.draw(rect);
		g.drawImage(rect, -5, -2, 4, 2, null);
		g.setTransform(temp);

		//g.drawRect(posX - 1, posY - 1, 2, 2);

	}

}
