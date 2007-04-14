package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Vehiculo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RepresentacionAvanzada extends Representacion 
{
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
	public RepresentacionAvanzada() 
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
	public RepresentacionAvanzada(Representacion rep) 
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
		g.setColor(Color.RED);
		g.drawRect(x_MapaARep(nodo.getPos().getLon()) - 2, y_MapaARep(nodo
				.getPos().getLat()) - 2, 4, 4);

		try
		{
			if (nodo.getTipo() != null)
			{	
				String aux = nodo.getTipo().getTipoCastellano();
				BufferedImage buffer = null;
				if (aux.equalsIgnoreCase("Carretera"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Mini-rotonda"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Rotonda.png"));
					}
					else if (aux.equalsIgnoreCase("Stop"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Stop.png"));
					}
					else if (aux.equalsIgnoreCase("Cruce"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Port�n para veh�culos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Cambio De Rasante"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Puente"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Viaducto"))
					{
						
					}
				}
				else if (aux.equalsIgnoreCase("Tiempo Libre"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Campo de golf"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Campo_Golf.png"));
					}
					
					
					else if (aux.equalsIgnoreCase("Estadio"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Estadio.png"));
					}
					else if (aux.equalsIgnoreCase("Pista de carreras"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Campo de deporte"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Parque acu�tico"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Parque"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Jard�n"))
					{
						
					}
				} 
				else if (aux.equalsIgnoreCase("Construcci�n"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Planta e�lica"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Torre_de_electricidad.PNG"));
					}
					else if (aux.equalsIgnoreCase("Planta Hidroel�ctrica"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Central Hidroel�ctrica"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Central nuclear"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Faro"))
					{
						
					}
				} else if (aux.equalsIgnoreCase("Infraestructura"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Pub"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\puff.png"));
					}
					else if (aux.equalsIgnoreCase("Parking"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Parking.png"));
					}
					else if (aux.equalsIgnoreCase("Gasolinera"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Gasolinera.png"));
					}
					else if (aux.equalsIgnoreCase("Cabina de telefono"))
					{
						//buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Cabinadetelefono.png"));
					}
					else if (aux.equalsIgnoreCase("Aseos Publicos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Edificio Publico"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Iglesia"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Iglesia.png"));
					}
					else if (aux.equalsIgnoreCase("Cementerio"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Oficina de Correos"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Correos.png"));
					}
					else if (aux.equalsIgnoreCase("Buz�n de Correos"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Correos2.png"));
					}
					else if (aux.equalsIgnoreCase("Colegio"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Colegio.png"));
					}
					else if (aux.equalsIgnoreCase("Supermercado"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Hospital"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Hospital.png"));
					}
					else if (aux.equalsIgnoreCase("Librer�a"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Libreria.png"));
					}
					else if (aux.equalsIgnoreCase("Comisaria"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Parque de bomberos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Terrazas"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Restaurante"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Restaurante.png"));
					}
					else if (aux.equalsIgnoreCase("Cadena de comida rapida"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Estacion de autobus"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Teatro"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Teatro.png"));
					}
				}
				g.drawImage(buffer, x_MapaARep(nodo.getPos().getLon()), y_MapaARep(nodo.getPos().getLat()), null);
			}
		}
		catch (IOException e) {e.printStackTrace();}
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
					this.pintar(g, tramo, null);
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

	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo, Tramo tramo2) 
	{
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null || !tramo2.equals(tramo))
			return;
		AffineTransform rot = new AffineTransform();

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
				rot.rotate(vehiculo.getTramo().getAngulo());
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
				rot.rotate(Math.PI + vehiculo.getTramo().getAngulo());
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
				rot.rotate(vehiculo.getTramo().getAngulo());
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
				rot.rotate(Math.PI + vehiculo.getTramo().getAngulo());
			}
			posX = posX2 + (int) ((posX1 - posX2) * vehiculo.getPosicion());
			posY = posY2 + (int) ((posY1 - posY2) * vehiculo.getPosicion());
		}
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