package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.ConversorUTM;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Vista.Sugerencias.Flecha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

/**
 * Clase abstracta para representaciones del mapa.
 * <p>
 * Esta clase define los m�todos necesarios (e implementa algunos de estos) para
 * poder represetnar correctamente el mapa de distintas maneras.
 * 
 * @author Grupo ISTrafico
 * 
 */
abstract public class Representacion {

	/**
	 * Alamcena el zoom que se esta haciendo sobre el mapa en la representacion
	 */
	double zoom;

	/**
	 * Guarda la longitud del mapa que se esta representada en la esquina
	 * superior izquierda
	 */
	double Lon0;

	/**
	 * Guarda la latitud del mapa que se esta representada en la esquina
	 * superior izquierda
	 */
	double Lat0;

	/**
	 * Almacena el tama�o actual en x del panel donde se hace la representacion
	 */
	int tamX;

	/**
	 * Almacena el tama�o actual en y del panel donde se hace la representacion
	 */
	int tamY;

	/**
	 * Constante de proporcionalidad, indica cuantas "longitudes" entran en un
	 * pixel de la pantalla dado un zoom y una posicion en el mapa. Esto es, si
	 * se multiplica una longitud por este valor obtentemos el desplazamiento en
	 * pixeles de esta desde 0.
	 */
	double consx;

	/**
	 * Constante de proporcionalidad, indica cuantas "latitudes" entran en un
	 * pixel de la pantalla dado un zoom y una posicion en el mapa. Esto es, si
	 * se multiplica una latitud por este valor obtentemos el desplazamiento en
	 * pixeles de esta desde 0.
	 */
	double consy;

	/**
	 * ArrayList que almacena las imagenes que se muestran de fondo al usuario
	 */
	private ArrayList<Image> imagenes;

	/**
	 * ArrayList que almacen las posiciones donde se encuentran las imagenes de
	 * fondo
	 */
	private ArrayList<Posicion> posiciones;

	/**
	 * Construcutra de la clase representacion, que toma los valores por
	 * defecto.
	 */
	Representacion() {
		zoom = 1.0f;
		Lon0 = 0;
		Lat0 = 0;
		recalculaCons();
		tamX = 200;
		tamY = 200;
		imagenes = new ArrayList<Image>();
		posiciones = new ArrayList<Posicion>();
	}

	/**
	 * Construcura de la clase representaci�n, que toma los valores de otra
	 * representacion dada como par�metro
	 * 
	 * @param rep
	 *            Representacion de la cual se toman los par�metros
	 */
	Representacion(Representacion rep) {
		this.zoom = rep.zoom;
		this.Lon0 = rep.Lon0;
		this.Lat0 = rep.Lat0;
		this.tamX = rep.tamX;
		this.tamY = rep.tamY;
		recalculaCons();
		this.imagenes = rep.imagenes;
		this.posiciones = rep.posiciones;
	}

	/**
	 * M�todo que recalula la posicion en X de un punto del mapa en la pantalla.
	 * <p>
	 * Este m�todo se vale de la consx para recalcular la posicion de un punto
	 * en el mapa en la representaci�n actual en pantalla.<br>
	 * La conversi�n inversa es realizada por lon_RepAMapa(int).
	 * 
	 * @param lon
	 *            Double que representa la longitud en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int x_MapaARep(double lon) {
		return tamX/2 + (int) ((lon - Lon0) / consx);
	}

	/**
	 * M�todo que recalula la posicion en Y de un punto del mapa en la pantalla.
	 * <p>
	 * Este m�todo se vale de la consy para recalcular la posicion de un punto
	 * en el mapa en la reprsentaci�n actual en pantalla.<br>
	 * La conversion inversa es la realizada por lat_RepAMapa(int).
	 * 
	 * @param lat
	 *            Double que representa la latitud en en mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int y_MapaARep(double lat) {
		return tamY/2 + (int) ((Lat0 - lat) / consy);// (int) ((posY/zoom - posY0));
	}

	/**
	 * M�todo que realiza la conversi�n de coordenadas de pantalla a coordeandas
	 * de mapa.
	 * <p>
	 * Este m�todo recalcula la longitud a la que se encuentra un punto a partir
	 * de su coordenada en x en la pantalla.<br>
	 * La conversi�n inversa es realizada por x_MapaARep(double).
	 * 
	 * @param posX
	 *            Coorenada en x de la pantalla
	 * @return Double que representa la longitud en el mapa
	 */
	public double lon_RepAMapa(int posX) {
		return ((double) (posX - tamX/2) * consx + Lon0);// (int) ((posX + posX0)*zoom);
	}

	/**
	 * M�todo que realiza la conversi�n de coordenadas de pantalla a coordeandas
	 * de mapa.
	 * <p>
	 * Este m�todo recalcula la latitud a la que se encuentra un punto a partir
	 * de su coordenada en y en la pantalla.<br>
	 * La conversi�n inversa es realizada por y_MapaARep(double).
	 * 
	 * @param posY
	 *            Coorenada en y de la pantalla
	 * @return Double que representa la longitud en el mapa
	 */
	public double lat_RepAMapa(int posY) {
		return (Lat0 - (double) (posY - tamY/2) * consy);// (int) ((posY + posY0)*zoom);
	}

	/**
	 * M�todo abstracto que se debe implementar para pintar un nodo.
	 * <p>
	 * Este m�todo recibe como par�metro un Graphics2D donde debe pintar el nodo
	 * que tambien recibe como par�metro. En este ultimo estar� toda la
	 * infromaci�n pertinente al tipo y la posici�n.
	 * 
	 * @param g
	 *            Graphics2D donde dibujar
	 * @param nodo
	 *            Nodo a dibujar
	 */
	abstract public void pintar(Graphics2D g, Nodo nodo);

	/**
	 * 
	 * @param g
	 * @param rectanguloSeleccion
	 */
	public void pintar(Graphics2D g, Rectangle rectanguloSeleccion) {
		g.draw(rectanguloSeleccion);
		Color colorTransparente = new Color((float) 0.8, (float) 0.1,
				(float) 0.05, (float) 0.2);
		g.setColor(colorTransparente);
		g.fill(rectanguloSeleccion);
	}

	/**
	 * M�todo abstracto que se debe implementar para pintar un tramo.
	 * <p>
	 * Este m�todo recibe como par�metro un Graphics2D donde debe pintar el
	 * tramo que tambien recibe como par�metro. En este ultimo estar� toda la
	 * infromaci�n pertinente al tipo, la posici�n inicial y final, etc.
	 * 
	 * @param g
	 *            Graphics2D donde dibujar
	 * @param tramo
	 *            Tramo a dibujar
	 */
	abstract public void pintar(Graphics2D g, Tramo tramo, String tipo);

	/**
	 * M�todo abstracto que se debe implementar para pintar una se�al.
	 * <p>
	 * Este m�todo recibe como par�metro un Graphics2D donde debe pintar la
	 * se�al que tambien recibe como par�metro. En esta ultima estar� toda la
	 * infromaci�n pertinente al tipo, estado, etc.
	 * 
	 * @param g
	 *            Graphics2D donde dibujar
	 * @param se�al
	 *            Se�al a dibujar
	 */
	abstract public void pintar(Graphics2D g, Se�al se�al);

	/**
	 * M�todo abstracto que se debe implementar para marcar un elemento del
	 * mapa.
	 * <p>
	 * Este m�todo dibuja en el mapa una sugerencia, esto es un elemento pinta
	 * de forma particular para que sea facilmente identificado por el usuario y
	 * facilitar la visualizaci�n.
	 * 
	 * @param g
	 *            Graphics2D donde se debe dibujar el elemento
	 * @param elemento
	 *            ElementoMapa que se desea resaltar
	 */
	
	public void pintarSugerencia(Graphics2D g, ElementoMapa elemento) {
		int tama�o = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				Color colorNodo = new Color((float) 0, (float) 0, (float) 1,
						(float) 0.9);
				g.setColor(colorNodo);
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
					Color colorTramo = new Color((float) 1, (float) 0.5,
							(float) 0, (float) 0.9);
					g.setColor(colorTramo);
					g.fillPolygon(p);
				} catch (ArithmeticException e) {
					
				}
			}
		}
	}

	abstract public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo, Tramo tramo);
	
	
	public void setLon0(double Lon0) {
		this.Lon0 = Lon0;
	}

	public void setLat0(double Lat0) {
		this.Lat0 = Lat0;
	}

	public void setTamX(int tamX) {
		this.tamX = tamX;
	}

	public void setTamY(int tamY) {
		this.tamY = tamY;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public double getZoom() {
		return zoom;
	}

	/**
	 * M�todo abstracto que se debe implementar para calcular el �rea ocupada
	 * por un tramo.
	 * <p>
	 * Este m�todo recibe como par�metro un tramoo. En �l ultimo estar� toda la
	 * infromaci�n pertinente al tipo, la posici�n inicial y final, etc.
	 * 
	 * @param tramo
	 *            Tramo del que se quiere calcular el �rea.
	 * @return El �rea ocupada por el tramo.
	 */
	public abstract Polygon generarAreaTramo(Tramo tramo);

	public abstract Polygon generarTrianguloFlechaSugerencia(Nodo nodo, Tramo destino);
	
	/**
	 * M�todo para pintar los elementos seleccionados en pantalla.
	 * <p>
	 * 
	 * @param g
	 *            Graphics2D donde se quiere dibujar
	 * @param elemento
	 *            Elemento a representar seleccionado
	 */
	
	public void pintarSugerenciaSeleccion(Graphics2D g, ElementoMapa elemento) {
		int tama�o = 14;
		if (elemento != null) {
			if (elemento.getClass() == Nodo.class) {
				// pintar un nodo sugerido
				Nodo nodo = (Nodo) elemento;
				Color colorNodo = new Color((float) 1, (float) 0, (float) 0,
						(float) 0.6);// 1,0.6,0
				g.setColor(colorNodo);
				g.fillOval(x_MapaARep(nodo.getPos().getLon()) - tama�o / 2,
						y_MapaARep(nodo.getPos().getLat()) - tama�o / 2,
						tama�o, tama�o);
			}
			if (elemento.getClass() == Tramo.class) {
				// pintar un tramo sugerido
				Tramo t = (Tramo) elemento;
				Polygon p = generarAreaTramo(t);
				Color colorTramo = new Color((float) 0.1, (float) 0.8,
						(float) 0.05, (float) 0.6);
				g.setColor(colorTramo);
				g.fillPolygon(p);
			}
		}
	}
	/**
	 * M�todo para mostrar los ejes de coordenadas en el mapa.
	 * <p>
	 * Este m�todo dibujara en linea punteada los ejes para ser representados en
	 * el fondo de un mapa y facilitar asi la representaci�n. Adem�s de los
	 * ejes, este m�todo muestra una referencia de la escala en la que se
	 * encuentra representado el mapa.
	 * 
	 * @param g
	 *            Graphics2D donde se deben representar los ejes de coordenadas
	 */
	public void pintarCoordenadas(Graphics2D g) {
		float array[] = { 10, 5, 5, 5 };
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 1, array, 1));

		int lat, lon;
		for (int i = 0; i < 30; i++) {
			lat = y_MapaARep(((int) (Lat0 / (100 * consy)) - i) * (100 * consy)) - tamY/2;

			lon = x_MapaARep(((int) (Lon0 / (100 * consx)) + i) * (100 * consx)) - tamX/2;

			g.drawLine(lon, 13, lon, 3000);
			g.drawString(Messages.getString("Representacion.0") + pasarAGrados(lon_RepAMapa(lon)), lon - 5, //$NON-NLS-1$
					13);
			g.drawLine(0, lat, 3000, lat);
			g
					.drawString(Messages.getString("Representacion.1") + pasarAGrados(lat_RepAMapa(lat)), 5, //$NON-NLS-1$
							lat - 2);
		}
		g.setStroke(new BasicStroke(1));
		int dist = 50;
		if (zoom <= 0.25) dist = 500;
		if (zoom > 4) dist = 5;
		g.drawLine(25, 40, 25 + (int) (dist * zoom), 40);
		g.drawLine(25, 35, 25, 45);
		g.drawLine(25 + (int) (dist * zoom), 35, 25 + (int) (dist * zoom), 45);
		g.drawString(Messages.getString("Representacion.2") + dist + Messages.getString("Representacion.3"), 40, 35); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * A�ade una imagen a la representacion en una posicion dada.
	 * <p>
	 * 
	 * @param imagen
	 *            Imagen que se quiere representar sobre el fondo.
	 * @param pos
	 *            Posicion donde se quiere colocar la imagen.
	 */
	public void addImage(Image imagen, Posicion pos) {
		if (imagen != null && pos != null) {
			imagenes.add(imagen);
			posiciones.add(pos);
		}
	}

	/**
	 * Quita una imagen de la representacion.
	 * <p>
	 * Este m�todo tambien se encarga de actualizar la lista de posiciones.
	 * 
	 * @param imagen
	 *            Imagen que se desea quitar de la representacion
	 */
	public void removeImage(Image imagen) {
		if (imagenes.indexOf(imagen)>=0){
			posiciones.remove(imagenes.indexOf(imagen));
			imagenes.remove(imagen);
		}
	}

	/**
	 * M�todo que vacia las listas de imagenes y sus posiciones relacionadas
	 */
	public void vaciarImage() {
		posiciones = new ArrayList<Posicion>();
		imagenes = new ArrayList<Image>();
	}

	/**
	 * Dibuja las imagenes de la representacion.
	 * 
	 * @param g
	 *            Graphics2D donde se deben representar las imagenes
	 */
	public void ponerImagenes(Graphics2D g) {
		for (int i = 0; i < imagenes.size(); i++) {
			g.drawImage(imagenes.get(i),
					x_MapaARep(posiciones.get(i).getLon()),
					y_MapaARep(posiciones.get(i).getLat()),
					(int)(imagenes.get(i).getWidth(null) * zoom),
					(int)(imagenes.get(i).getHeight(null) * zoom),
					null);
		}
	}

	/**
	 * Metodo que a partir de los par�metros de la representaci�n recalcula las
	 * constantes de proporcionalidad para la representaci�n.
	 */
	public void recalculaCons() {
		int zona = ConversorUTM.recalculaZona(Lon0);
		boolean hem = ConversorUTM.recalculaHem(Lat0);
		double xy[] = ConversorUTM.LatLonToUTMXY(Lat0, Lon0, zona);
		xy[0] = xy[0] + 100 / zoom;
		xy[1] = xy[1] + 100 / zoom;
		double latlon[] = ConversorUTM.UTMXYToLatLon(xy[0], xy[1], zona, hem);
		consx = Math.abs(latlon[1] - Lon0) / 100;
		consy = Math.abs(latlon[0] - Lat0) / 100;
	}
	
	/**
	 * Transforma un n�mero double en notaci�n de grados, minutos y segundos.<p>
	 * 
	 * @param lat
	 * Double que representa una latitud o longitud
	 * @return
	 * String con los grados, minutos y segundos
	 */
	static public String pasarAGrados(double lat) {
		String resultado = Messages.getString("Representacion.4"); //$NON-NLS-1$
		if (lat < 0) resultado += Messages.getString("Representacion.5"); //$NON-NLS-1$
		lat = Math.abs(lat);
		resultado += (int)(Math.floor(lat)) + Messages.getString("Representacion.6"); //$NON-NLS-1$
		lat = lat - Math.floor(lat);
		lat = lat * 60;
		resultado += (int)(Math.floor(lat)) + Messages.getString("Representacion.7"); //$NON-NLS-1$
		lat = lat - Math.floor(lat);
		lat = lat * 60;
		resultado += (int)(Math.floor(lat)) +Messages.getString("Representacion.8"); //$NON-NLS-1$
		return resultado;
	}

	public void pintar(Graphics2D g, Flecha flecha) 
	{
		if (flecha != null)
		{
			Tramo origen = flecha.getTramoOrigen();
			Tramo destino = flecha.getTramoDestino();
			g.setStroke(new BasicStroke(7));
			try 
			{
				Color colorTransparente = new Color((float)1.0,0,0,(float)0.75);
				if (flecha.getTipo() == 1){
					colorTransparente = new Color(0,(float)1.0,0,(float)0.75);
				}
				g.setColor(colorTransparente);
				Polygon p = generarAreaTramo(origen);
				g.fillPolygon(p);
				p = generarAreaTramo(destino);
				g.fillPolygon(p);
				p = generarTrianguloFlechaSugerencia(flecha.getNodo(), destino);
				g.fillPolygon(p);
			} 
			catch (ArithmeticException e) {}
			g.setStroke(new BasicStroke(1));
		}
	}
	public void pintar(Graphics2D g,LineaBus linea){
		Iterator<Nodo> paradas=linea.getParadas().iterator();
		BufferedImage estacion;
		estacion=cargarImagen(Messages.getString("Representacion.9")); //$NON-NLS-1$
		while(paradas.hasNext()){
			   Nodo parada = (Nodo)paradas.next();
			   g.drawImage(estacion, x_MapaARep(parada.getPos().getLon()) - 10,
						y_MapaARep(parada.getPos().getLat()) - 10, null);
			}
		Stroke stroke=g.getStroke();
		Iterator<Tramo> tramos=linea.getTramos().iterator();
		while (tramos.hasNext()){
			Tramo tramoAux =tramos.next();
			Posicion posnodo1 = tramoAux.getNodoInicial().getPos();
			Posicion posnodo2 = tramoAux.getNodoFinal().getPos();
			//g.setColor(linea.getColor());
			g.setColor(Color.RED);
			final float dash1[] = {5.0f};
			g.setStroke( new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    5.0f,dash1, 0.0f));
			g.drawLine(x_MapaARep(posnodo1.getLon())+3,
					y_MapaARep(posnodo1.getLat())+3, x_MapaARep(posnodo2.getLon())+3,
					y_MapaARep(posnodo2.getLat())+3);
		}
		g.setStroke(stroke);
	}

	public ArrayList<Image> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<Image> imagenes) {
		this.imagenes = imagenes;
	}

	public ArrayList<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(ArrayList<Posicion> posiciones) {
		this.posiciones = posiciones;
	}
	
	public abstract void seleccionarColoresSemaforos(Tramo tramo, Graphics2D g); 
	
	public void iniciarRepresentacion() {
	}
	
	public BufferedImage cargarImagen(String nombre) {
		BufferedImage imagen;
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			imagen = ImageIO
			.read(cl
					.getResource(Messages.getString("Representacion.10") //$NON-NLS-1$
							+ nombre));
			
		} catch (IOException e) {
			System.out.println(nombre);
			return null;
		} catch (NullPointerException e) {
			System.out.println(nombre);
			return null;
		}
		return imagen;
	}
}
