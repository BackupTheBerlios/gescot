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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class RepresentacionAvanzada extends Representacion {
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
	public RepresentacionAvanzada() {
		super();
		inicializarImagenes();
	}

	/**
	 * Constructur de la clase que toma los parámetros de otra clase
	 * representacion.
	 * 
	 * @param rep
	 */
	public RepresentacionAvanzada(Representacion rep) {
		super(rep);
		inicializarImagenes();
	}

	/** Inicializa las imagenes de los coches. */
	private void inicializarImagenes() {
		coches = new BufferedImage[7];
		coches[0] = cargarImagen("Coche1.png");
		coches[1] = cargarImagen("Coche2.png");
		coches[2] = cargarImagen("Coche3.png");
		coches[3] = cargarImagen("Coche4.png");
		coches[4] = cargarImagen("Coche5.png");
		coches[5] = cargarImagen("Coche6.png");
		coches[6] = cargarImagen("Coche7.png");

	}

	public void pintar(Graphics2D g, Nodo nodo) {
		g.setColor(Color.RED);
		pintarAreaNodo(g, nodo);


		if (nodo.getTipo() != null) {
			String aux = nodo.getTipo().getTipoCastellano();
			BufferedImage buffer = null;
			if (aux.equalsIgnoreCase("Carretera")) {
				aux = nodo.getTipo().getValorTipoCastellano();
				if (aux.equalsIgnoreCase("Mini-rotonda")) {
					buffer = cargarImagen("Rotonda.PNG");
				} else if (aux.equalsIgnoreCase("Stop")) {
					buffer = cargarImagen("Stop.PNG");
				} else if (aux.equalsIgnoreCase("Cruce")) {

				} else if (aux.equalsIgnoreCase("Portón para vehículos")) {

				} else if (aux.equalsIgnoreCase("Cambio De Rasante")) {

				} else if (aux.equalsIgnoreCase("Puente")) {

				} else if (aux.equalsIgnoreCase("Viaducto")) {

				}
			} else if (aux.equalsIgnoreCase("Tiempo Libre")) {
				aux = nodo.getTipo().getValorTipoCastellano();
				if (aux.equalsIgnoreCase("Campo de golf")) {
					buffer = cargarImagen("Campo_Golf.PNG");
				}

				else if (aux.equalsIgnoreCase("Estadio")) {
					buffer = cargarImagen("Estadio.png");
				} else if (aux.equalsIgnoreCase("Pista de carreras")) {

				} else if (aux.equalsIgnoreCase("Campo de deporte")) {

				} else if (aux.equalsIgnoreCase("Parque acuático")) {

				} else if (aux.equalsIgnoreCase("Parque")) {

				} else if (aux.equalsIgnoreCase("Jardín")) {

				}
			} else if (aux.equalsIgnoreCase("Construcción")) {
				aux = nodo.getTipo().getValorTipoCastellano();
				if (aux.equalsIgnoreCase("Planta eólica")) {
					buffer = cargarImagen("Torre_de_electricidad.PNG");
				} else if (aux.equalsIgnoreCase("Planta Hidroeléctrica")) {

				} else if (aux.equalsIgnoreCase("Central Hidroeléctrica")) {

				} else if (aux.equalsIgnoreCase("Central nuclear")) {

				} else if (aux.equalsIgnoreCase("Faro")) {

				}
			} else if (aux.equalsIgnoreCase("Infraestructura")) {
				aux = nodo.getTipo().getValorTipoCastellano();
				if (aux.equalsIgnoreCase("Pub")) {
					buffer = cargarImagen("Puff.PNG");
				} else if (aux.equalsIgnoreCase("Parking")) {
					buffer = cargarImagen("Parking.PNG");
				} else if (aux.equalsIgnoreCase("Gasolinera")) {
					buffer = cargarImagen("Gasolinera.PNG");
				} else if (aux.equalsIgnoreCase("Cabina de telefono")) {
					// buffer = ImageIO.read(new
					// File("is\\SimTraffic\\Vista\\Imagenes\\Representacion
					// Avanzada\\Cabinadetelefono.png"));
				} else if (aux.equalsIgnoreCase("Aseos Publicos")) {

				} else if (aux.equalsIgnoreCase("Edificio Publico")) {

				} else if (aux.equalsIgnoreCase("Iglesia")) {
					buffer = cargarImagen("Iglesia.PNG");
				} else if (aux.equalsIgnoreCase("Cementerio")) {

				} else if (aux.equalsIgnoreCase("Oficina de Correos")) {
					buffer = cargarImagen("Correos.PNG");
				} else if (aux.equalsIgnoreCase("Buzón de Correos")) {
					buffer = cargarImagen("Correos2.PNG");
				} else if (aux.equalsIgnoreCase("Colegio")) {
					buffer = cargarImagen("Colegio.PNG");
				} else if (aux.equalsIgnoreCase("Supermercado")) {

				} else if (aux.equalsIgnoreCase("Hospital")) {
					buffer = cargarImagen("Hospital.PNG");
				} else if (aux.equalsIgnoreCase("Librería")) {
					buffer = cargarImagen("Libreria.PNG");
				} else if (aux.equalsIgnoreCase("Comisaria")) {

				} else if (aux.equalsIgnoreCase("Parque de bomberos")) {

				} else if (aux.equalsIgnoreCase("Terrazas")) {

				} else if (aux.equalsIgnoreCase("Restaurante")) {
					buffer = cargarImagen("Restaurante.PNG");
				} else if (aux.equalsIgnoreCase("Cadena de comida rapida")) {

				} else if (aux.equalsIgnoreCase("Estacion de autobus")) {

				} else if (aux.equalsIgnoreCase("Teatro")) {
					buffer = cargarImagen("Teatro.PNG");
				}
			}
			g.drawImage(buffer, x_MapaARep(nodo.getPos().getLon()),
					y_MapaARep(nodo.getPos().getLat()), null);
		}

	}

	private void pintarAreaNodo(Graphics2D g, Nodo nodo) 
	{
		int numtramos = nodo.getTramos().size();
		int npuntos_X[] = new int[numtramos*2];
		int npuntos_Y[] = new int[numtramos*2];
		Iterator<Tramo> iter = nodo.getTramos().iterator();
		int ipunto = 0;
		while (iter.hasNext())
		{
			Tramo temp = iter.next();
			if (nodo.equals(temp.getNodoInicial()))
			{
				npuntos_X[ipunto] = (int) (x_MapaARep(temp.getNodoInicial().getPos().getLon()) + tamaño_carril / zoom 
						* temp.getNumCarrilesDir1() * Math.sin(temp.getAngulo()));
				npuntos_X[ipunto  + 1] = (int) (x_MapaARep(temp.getNodoInicial().getPos().getLon()) + tamaño_carril / zoom
						* temp.getNumCarrilesDir2() * -(Math.sin(temp.getAngulo())));
				npuntos_Y[ipunto] = (int) (y_MapaARep(temp.getNodoInicial().getPos().getLat()) + tamaño_carril / zoom
						* temp.getNumCarrilesDir1() * (Math.cos(temp.getAngulo())));
				npuntos_Y[ipunto + 1] =	(int) (y_MapaARep(temp.getNodoInicial().getPos().getLat()) + tamaño_carril / zoom
						* temp.getNumCarrilesDir2() * -Math.cos(temp.getAngulo()));
			}
			else
			{			
				npuntos_X[ipunto + 1] = (int) (x_MapaARep(temp.getNodoFinal().getPos().getLon()) + tamaño_carril / zoom
										* temp.getNumCarrilesDir1() * Math.sin(temp.getAngulo()));
				npuntos_X[ipunto] = (int) (x_MapaARep(temp.getNodoFinal().getPos().getLon()) + tamaño_carril / zoom
										* temp.getNumCarrilesDir2() * -(Math.sin(temp.getAngulo())));
				npuntos_Y[ipunto + 1] = (int) (y_MapaARep(temp.getNodoFinal().getPos().getLat()) + tamaño_carril / zoom
										* temp.getNumCarrilesDir1() * (Math.cos(temp.getAngulo())));
				npuntos_Y[ipunto] =	(int) (y_MapaARep(temp.getNodoFinal().getPos().getLat()) + tamaño_carril / zoom
										* temp.getNumCarrilesDir2() * -Math.cos(temp.getAngulo()));
			}
			ipunto = ipunto + 2;
		}
		Polygon poligon = new Polygon(npuntos_X, npuntos_Y, numtramos*2);
		g.setColor(Color.DARK_GRAY);
		g.fill(poligon);
		g.setColor(Color.RED);
		g.drawRect(x_MapaARep(nodo.getPos().getLon()) - 2, y_MapaARep(nodo.getPos().getLat()) - 2, 4, 4);
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

		if (tipo == null) {
			dibujarCarretera(g, tramo);
		} else if (tipo.compareTo("leisure") == 0) {
			g.setColor(Color.green);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo("amenity") == 0) {
			g.setColor(Color.gray);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo("highway") == 0) {
			dibujarCarretera(g, tramo);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo("manmade") == 0) {
			g.setColor(Color.pink);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else {
			dibujarCarretera(g, tramo);
		}

	}

	private void dibujarCarretera(Graphics2D g, Tramo tramo) {

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
										* carriles_vuelta * -(Math.sin(angulo))),
										(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
												* carriles_vuelta * -(Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_ida * (Math.cos(angulo))),
						(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
								* carriles_ida * (Math.cos(angulo))),
								(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
										* carriles_vuelta * -Math.cos(angulo)),
										(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
												* carriles_vuelta * -Math.cos(angulo)) };
		// establece el color y dibuja el poligono
		System.out.println("x->" + x[0] + " " + x[1]+ " " + x[2]+ " " + x[3]);
		System.out.println("y->" + y[0] + " " + y[1]+ " " + y[2]+ " " + y[3]); 
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
			g.drawLine((int) (posx1 + tamaño_carril / zoom * (i + 1) * Math.sin(angulo)), 
					(int) (posy1 + tamaño_carril / zoom * (i + 1) * (Math.cos(angulo))),
					(int) (posx2 + tamaño_carril / zoom * (i + 1) * Math.sin(angulo)),
					(int) (posy2 + tamaño_carril / zoom * (i + 1) * (Math.cos(angulo))));
		}
		// luego una linea punteada por cada carril en el otro y una normal para
		// el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (posx1 + tamaño_carril / zoom * -(i + 1) * Math.sin(angulo)), 
					(int) (posy1 + tamaño_carril / zoom * -(i + 1) * (Math.cos(angulo))),
					(int) (posx2 + tamaño_carril / zoom * -(i + 1) * Math.sin(angulo)), 
					(int) (posy2 + tamaño_carril	/ zoom * -(i + 1) * (Math.cos(angulo))));
		}
		g.drawLine((int) (posx1 + tamaño_carril / zoom * -(carriles_vuelta) * Math.sin(angulo)), 
				(int) (posy1 + tamaño_carril / zoom * -(carriles_vuelta) * (Math.cos(angulo))),
				(int) (posx1 + tamaño_carril / zoom * (carriles_ida) * Math.sin(angulo)),
				(int) (posy1 + tamaño_carril / zoom * (carriles_ida) * (Math.cos(angulo))));
		g.drawLine((int) (posx2 + tamaño_carril / zoom * -(carriles_vuelta) * Math.sin(angulo)), 
				(int) (posy2 + tamaño_carril / zoom * -(carriles_vuelta) * (Math.cos(angulo))),
				(int) (posx2 + tamaño_carril / zoom * (carriles_ida) * Math.sin(angulo)), 
				(int) (posy2 + tamaño_carril / zoom * (carriles_ida) * (Math.cos(angulo))));
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

//		genera los puntos (x,y) de las esquinas del poligono
		int x[] = {
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
						* carriles_ida * Math.sin(angulo)),
						(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
								* carriles_ida * Math.sin(angulo)),
								(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril / zoom
										* carriles_vuelta * -(Math.sin(angulo))),
										(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril / zoom
												* carriles_vuelta * -(Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
						* carriles_ida * (Math.cos(angulo))),
						(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
								* carriles_ida * (Math.cos(angulo))),
								(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril / zoom
										* carriles_vuelta * -Math.cos(angulo)),
										(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril / zoom
												* carriles_vuelta * -Math.cos(angulo)) };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}

	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo, Tramo tramo2) {
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null || !tramo2.equals(tramo))
			return;
		AffineTransform rot = new AffineTransform();

		// Shape rect = new Rectangle2D.Double(-5, -2, 5,2);
		BufferedImage rect = coches[vehiculo.getId() % 7];

		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// TODO si el tramo no se dibuja, puede ser bueno que ya no sigua
		g.setColor(Color.PINK);
		int posX1, posY1, posX2, posY2, posX, posY;
		if (tramo.getNodoFinal() == vehiculo.getNodoDestino()) {
			if ((tramo.getNodoInicial().getPos().getLon() - tramo
					.getNodoFinal().getPos().getLon()) < 0) {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* (-Math.sin(tramo.getAngulo())));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* Math.cos(tramo.getAngulo()));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* (-Math.sin(tramo.getAngulo())));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* Math.cos(tramo.getAngulo()));
				rot.rotate(vehiculo.getTramo().getAngulo());
			} else {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* Math.sin(tramo.getAngulo()));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* (-Math.cos(tramo.getAngulo())));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* Math.sin(tramo.getAngulo()));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* (-Math.cos(tramo.getAngulo())));
				rot.rotate(Math.PI + vehiculo.getTramo().getAngulo());
			}
			posX = posX1 + (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY1 + (int) ((posY2 - posY1) * vehiculo.getPosicion());
		} else {
			if ((tramo.getNodoInicial().getPos().getLon() - tramo
					.getNodoFinal().getPos().getLon()) > 0) {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* (-Math.sin(tramo.getAngulo())));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* Math.cos(tramo.getAngulo()));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* (-Math.sin(tramo.getAngulo())));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril())
						* Math.cos(tramo.getAngulo()));
				rot.rotate(vehiculo.getTramo().getAngulo());
			} else {
				posX1 = (int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* Math.sin(tramo.getAngulo()));
				posY1 = (int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* (-Math.cos(tramo.getAngulo())));
				posX2 = (int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* Math.sin(tramo.getAngulo()));
				posY2 = (int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril
						/ zoom * (vehiculo.getCarril() - 1)
						* (-Math.cos(tramo.getAngulo())));
				rot.rotate(Math.PI + vehiculo.getTramo().getAngulo());
			}
			posX = posX2 + (int) ((posX1 - posX2) * vehiculo.getPosicion());
			posY = posY2 + (int) ((posY1 - posY2) * vehiculo.getPosicion());
		}
		AffineTransform trans = new AffineTransform();
		AffineTransform zoom = new AffineTransform();
		zoom.scale(1 / this.zoom, 1 / this.zoom);
		rot.rotate(vehiculo.getTramo().getAngulo());
		rot.concatenate(zoom);
		trans.translate(posX, posY);
		trans.concatenate(rot);
		AffineTransform temp = g.getTransform();
		g.transform(trans);
		// g.draw(rect);
		g.drawImage(rect, -5, -2, 4, 2, null);
		g.setTransform(temp);

		// g.drawRect(posX - 1, posY - 1, 2, 2);

	}

	private BufferedImage cargarImagen(String nombre) {
		BufferedImage imagen;
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			imagen = ImageIO.read(new File((cl.getResource("is/SimTraffic/Vista/Imagenes/RepresentacionAvanzada/" + nombre)).getFile()));

		} catch (IOException e) {
			System.out.println(nombre);
			return null;
		}catch (NullPointerException e) {
			System.out.println(nombre);
			return null;
		}
		return imagen;

	}

}