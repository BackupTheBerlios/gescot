package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.*;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Vista.Sugerencias.Flecha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Clase abstracta para representaciones del mapa.
 * <p>
 * Esta clase define los métodos necesarios (e implementa algunos de estos) para
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
	 * Almacena el tamaño actual en x del panel donde se hace la representacion
	 */
	int tamX;

	/**
	 * Almacena el tamaño actual en y del panel donde se hace la representacion
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
	 * Construcura de la clase representación, que toma los valores de otra
	 * representacion dada como parámetro
	 * 
	 * @param rep
	 *            Representacion de la cual se toman los parámetros
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
	 * Método que recalula la posicion en X de un punto del mapa en la pantalla.
	 * <p>
	 * Este método se vale de la consx para recalcular la posicion de un punto
	 * en el mapa en la representación actual en pantalla.<br>
	 * La conversión inversa es realizada por lon_RepAMapa(int).
	 * 
	 * @param lon
	 *            Double que representa la longitud en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int x_MapaARep(double lon) {
		return tamX/2 + (int) ((lon - Lon0) / consx);
	}

	/**
	 * Método que recalula la posicion en Y de un punto del mapa en la pantalla.
	 * <p>
	 * Este método se vale de la consy para recalcular la posicion de un punto
	 * en el mapa en la reprsentación actual en pantalla.<br>
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
	 * Método que realiza la conversión de coordenadas de pantalla a coordeandas
	 * de mapa.
	 * <p>
	 * Este método recalcula la longitud a la que se encuentra un punto a partir
	 * de su coordenada en x en la pantalla.<br>
	 * La conversión inversa es realizada por x_MapaARep(double).
	 * 
	 * @param posX
	 *            Coorenada en x de la pantalla
	 * @return Double que representa la longitud en el mapa
	 */
	public double lon_RepAMapa(int posX) {
		return ((double) (posX - tamX/2) * consx + Lon0);// (int) ((posX + posX0)*zoom);
	}

	/**
	 * Método que realiza la conversión de coordenadas de pantalla a coordeandas
	 * de mapa.
	 * <p>
	 * Este método recalcula la latitud a la que se encuentra un punto a partir
	 * de su coordenada en y en la pantalla.<br>
	 * La conversión inversa es realizada por y_MapaARep(double).
	 * 
	 * @param posY
	 *            Coorenada en y de la pantalla
	 * @return Double que representa la longitud en el mapa
	 */
	public double lat_RepAMapa(int posY) {
		return (Lat0 - (double) (posY - tamY/2) * consy);// (int) ((posY + posY0)*zoom);
	}

	/**
	 * Método abstracto que se debe implementar para pintar un nodo.
	 * <p>
	 * Este método recibe como parámetro un Graphics2D donde debe pintar el nodo
	 * que tambien recibe como parámetro. En este ultimo estará toda la
	 * infromación pertinente al tipo y la posición.
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
	abstract public void pintar(Graphics2D g, Rectangle rectanguloSeleccion);

	/**
	 * Método abstracto que se debe implementar para pintar un tramo.
	 * <p>
	 * Este método recibe como parámetro un Graphics2D donde debe pintar el
	 * tramo que tambien recibe como parámetro. En este ultimo estará toda la
	 * infromación pertinente al tipo, la posición inicial y final, etc.
	 * 
	 * @param g
	 *            Graphics2D donde dibujar
	 * @param tramo
	 *            Tramo a dibujar
	 */
	abstract public void pintar(Graphics2D g, Tramo tramo, String tipo);

	/**
	 * Método abstracto que se debe implementar para pintar una señal.
	 * <p>
	 * Este método recibe como parámetro un Graphics2D donde debe pintar la
	 * señal que tambien recibe como parámetro. En esta ultima estará toda la
	 * infromación pertinente al tipo, estado, etc.
	 * 
	 * @param g
	 *            Graphics2D donde dibujar
	 * @param señal
	 *            Señal a dibujar
	 */
	abstract public void pintar(Graphics2D g, Señal señal);

	/**
	 * Método abstracto que se debe implementar para marcar un elemento del
	 * mapa.
	 * <p>
	 * Este método dibuja en el mapa una sugerencia, esto es un elemento pinta
	 * de forma particular para que sea facilmente identificado por el usuario y
	 * facilitar la visualización.
	 * 
	 * @param g
	 *            Graphics2D donde se debe dibujar el elemento
	 * @param elemento
	 *            ElementoMapa que se desea resaltar
	 */
	abstract public void pintarSugerencia(Graphics2D g, ElementoMapa elemento);

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
	 * Método abstracto que se debe implementar para calcular el área ocupada
	 * por un tramo.
	 * <p>
	 * Este método recibe como parámetro un tramoo. En él ultimo estará toda la
	 * infromación pertinente al tipo, la posición inicial y final, etc.
	 * 
	 * @param tramo
	 *            Tramo del que se quiere calcular el área.
	 * @return El área ocupada por el tramo.
	 */
	public abstract Polygon generarAreaTramo(Tramo tramo);

	public abstract Polygon generarTrianguloFlechaSugerencia(Nodo nodo, Tramo destino);
	
	/**
	 * Método para pintar los elementos seleccionados en pantalla.
	 * <p>
	 * 
	 * @param g
	 *            Graphics2D donde se quiere dibujar
	 * @param elemento
	 *            Elemento a representar seleccionado
	 */
	public abstract void pintarSugerenciaSeleccion(Graphics2D g,
			ElementoMapa elemento);

	/**
	 * Método para mostrar los ejes de coordenadas en el mapa.
	 * <p>
	 * Este método dibujara en linea punteada los ejes para ser representados en
	 * el fondo de un mapa y facilitar asi la representación. Además de los
	 * ejes, este método muestra una referencia de la escala en la que se
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
			g.drawString("" + pasarAGrados(lon_RepAMapa(lon)), lon - 5,
					13);
			g.drawLine(0, lat, 3000, lat);
			g
					.drawString("" + pasarAGrados(lat_RepAMapa(lat)), 5,
							lat - 2);
		}
		g.setStroke(new BasicStroke(1));
		int dist = 50;
		
		if (zoom <= 0.25) dist = 500;
		g.drawLine(25, 40, 25 + (int) (dist * zoom), 40);
		g.drawLine(25, 35, 25, 45);
		g.drawLine(25 + (int) (dist * zoom), 35, 25 + (int) (dist * zoom), 45);
		g.drawString("" + dist + " m", 40, 35);
	}

	/**
	 * Añade una imagen a la representacion en una posicion dada.
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
		;
	}

	/**
	 * Quita una imagen de la representacion.
	 * <p>
	 * Este método tambien se encarga de actualizar la lista de posiciones.
	 * 
	 * @param imagen
	 *            Imagen que se desea quitar de la representacion
	 */
	public void removeImage(Image imagen) {
		posiciones.remove(imagenes.indexOf(imagen));
		imagenes.remove(imagen);
	}

	/**
	 * Método que vacia las listas de imagenes y sus posiciones relacionadas
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
	 * Metodo que a partir de los parámetros de la representación recalcula las
	 * constantes de proporcionalidad para la representación.
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
	 * Transforma un número double en notación de grados, minutos y segundos.<p>
	 * 
	 * @param lat
	 * Double que representa una latitud o longitud
	 * @return
	 * String con los grados, minutos y segundos
	 */
	static public String pasarAGrados(double lat) {
		String resultado = "";
		if (lat < 0) resultado += "-";
		lat = Math.abs(lat);
		resultado += (int)(Math.floor(lat)) + "º ";
		lat = lat - Math.floor(lat);
		lat = lat * 60;
		resultado += (int)(Math.floor(lat)) + "' ";
		lat = lat - Math.floor(lat);
		lat = lat * 60;
		resultado += (int)(Math.floor(lat)) +"\"";
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
				Color colorTransparente = new Color(0,0,(float)1.0,(float)0.75);
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
}
