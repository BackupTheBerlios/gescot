package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

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
	int posX0;

	/**
	 * Guarda la posicion en Y del mapa que se esta representado en la esquina
	 * superior izquierda
	 */
	int posY0;

	/**
	 * Almacena el tamaño actual en x del panel donde se hace la representacion
	 */
	int tamX;

	/**
	 * Almacena el tamaño actual en y del panel donde se hace la representacion
	 */
	int tamY;

	Representacion() {
		zoom = 1.0f;
		posX0 = 0;
		posY0 = 0;
		tamX = 200;
		tamY = 200;
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
	public int x_MapaARep(int posX) {
		// falta implementar
		return (int) ((posX - posX0)*zoom);
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
	public int y_MapaARep(int posY) {
		// falta implementar
		return (int) ((posY - posY0)*zoom);
	}

	public int x_RepAMapa(int posX) {
		// falta implementar
		return (int) ((posX + posX0)*zoom);
	}

	public int y_RepAMapa(int posY) {
		// falta implementar
		return (int) ((posY + posY0)*zoom);
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

	public void setPosX0(int posX0) {
		this.posX0 = posX0;
	}

	public void setPosY0(int posY0) {
		this.posY0 = posY0;
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
	 * Método abstracto que se debe implementar para calcular el área ocupada por un tramo.
	 * <p>
	 * Este método recibe como parámetro un tramoo. En él ultimo estará toda la
	 * infromación pertinente al tipo, la posición inicial y final, etc.
	 * 
	 * @param tramo
	 *            Tramo del que se quiere calcular el área.
	 * @return 
	 * 			  El área ocupada por el tramo.
	 */
	public abstract Polygon generarAreaTramo(Tramo tramo); 
	
	public abstract void pintarCoordenadas(Graphics2D g);
}
