package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.text.DecimalFormat;
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
	float zoom;

	/**
	 * Guarda la posicion en X del mapa que se esta representado en la esquina
	 * superior izquierda
	 */
	double Lon0;

	/**
	 * Guarda la posicion en Y del mapa que se esta representado en la esquina
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
	
	double consx;
	double consy;
	
	private ArrayList<Image> imagenes;
	private ArrayList<Posicion> posiciones;
	
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
	 * Método que recalula la posicion en X de un punto del mapa en la pantalla.
	 * <p>
	 * Esta funcion por ahora solo devuelve el punto como esta, pero tendra que
	 * tener en cuenta el zoom, la posicion de la ventana respecto al mapa y
	 * otra información pertinente.
	 * 
	 * @param posX
	 *            Entero que representa la posicion en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int x_MapaARep(double lon) {
		// falta implementar
		return (int) ((lon - Lon0)/consx);// (int) ((posX/zoom - posX0));
	}

	/**
	 * Método que recalula la posicion en Y de un punto del mapa en la pantalla.
	 * <p>
	 * Esta funcion por ahora solo devuelve el punto como esta, pero tendra que
	 * tener en cuenta el zoom, la posicion de la ventana respecto al mapa y
	 * otra información pertinente.
	 * 
	 * @param posX
	 *            Entero que representa la posicion en el mapa
	 * @return Entero que representa una posicion en la pantalla
	 */
	public int y_MapaARep(double lat) {
		return (int) ((Lat0 - lat)/consy);// (int) ((posY/zoom - posY0));
	}

	public double lon_RepAMapa(int posX) {
		return ((double)posX*consx + Lon0);// (int) ((posX + posX0)*zoom);
	}

	public double lat_RepAMapa(int posY) {
		return (Lat0 - (double)posY*consy);// (int) ((posY + posY0)*zoom);
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
	abstract public void pintar(Graphics2D g, Tramo tramo);

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

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public float getZoom() {
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
	
	public abstract void pintarSugerenciaSeleccion(Graphics2D g, ElementoMapa elemento);

	public void pintarCoordenadas(Graphics2D g) {
		float array[] = { 10, 5, 5, 5 };
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 1, array, 1));
		DecimalFormat cincoCifras = new DecimalFormat("0.00000");

		int lat, lon;
		for (int i = 0; i < 30; i++) {
			lat = y_MapaARep(Lat0 - i*0.001*zoom);
			lon = x_MapaARep(Lon0 + i*0.001*zoom);
			g.drawLine(lon, 13, lon, 3000);
			g.drawString(""+cincoCifras.format(Lon0 + i*0.008*zoom), lon - 5, 13);
			g.drawLine(0,lat,3000,lat);
			g.drawString(""+cincoCifras.format(Lat0 - i*0.008*zoom), 5, lat - 2);
		}
		g.setStroke(new BasicStroke(1));
		g.drawLine(25, 40, 25 + (int) (50*zoom), 40);
		g.drawLine(25, 35, 25, 45);
		g.drawLine(25 + (int) (50*zoom), 35, 25 + (int) (50*zoom), 45);
		g.drawString("50 m", 40, 35);
	}
	
	public void addImage(Image imagen, Posicion pos) {
		imagenes.add(imagen);
		posiciones.add(pos);
	}
	
	public void removeImage(Image imagen) {
		posiciones.remove(imagenes.indexOf(imagen));
		imagenes.remove(imagen);
	}

	public void ponerImagenes(Graphics2D g) {
		for (int i = 0; i < imagenes.size(); i++) {
			g.drawImage(imagenes.get(i),
					x_MapaARep(posiciones.get(i).getLon()),
					y_MapaARep(posiciones.get(i).getLat()), null);
		}
	}
	
	private void recalculaCons() {
		int zona = ConversorUTM.recalculaZona(Lon0);
		boolean hem = ConversorUTM.recalculaHem(Lat0);
		double xy[] = ConversorUTM.LatLonToUTMXY(Lat0, Lon0, zona);
		xy[0] = xy[0] + zoom;
		xy[1] = xy[1] + zoom;
		double latlon[] = ConversorUTM.UTMXYToLatLon(xy[0], xy[1], zona, hem);
		consx = Math.abs(latlon[0] - Lon0);
		consy = Math.abs(latlon[1] - Lat0);
		System.out.println(""+ consx+ " "+ consy);
	}
}
