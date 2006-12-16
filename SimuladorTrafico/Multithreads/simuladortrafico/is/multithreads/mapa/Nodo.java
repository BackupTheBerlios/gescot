/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads.mapa;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Grupo IS Tráfico
 * 
 * Clase que mantiene la información de un nodo, como son las calles que llegan
 * y eventualmente las señales de tráfico. Posee distintos métodos para acceder
 * a la información de distintas formas, para facilitar las distintas etapas de
 * la simulación.
 */
public class Nodo {

	/**
	 * Lista de los Tramos que tiene dirección entrante al nodo
	 */
	private List<Tramo> callesEntran;

	/**
	 * Lista de los Tramos que tienen dirección saliente del nodo.
	 */
	private List<Tramo> callesSalen;

	/**
	 * Posiciones en el eje x y el eje y del nodo, respecto a un punto del
	 * origen del mapa
	 */
	private double posX, posY;

	private Semaforo paso;

	Random generador = new Random();

	/**
	 * Constructor del nodo, que inicializa la posición y las listas.
	 * 
	 * @param posX
	 *            Double que representa la posición del nodo a lo largo del eje
	 *            x.
	 * @param posY
	 *            Double que representa la posición del nodo a lo largo del eje
	 *            y.
	 */
	public Nodo(double posX, double posY, Semaforo paso) {
		this.posX = posX;
		this.posY = posY;
		callesEntran = new ArrayList<Tramo>();
		callesSalen = new ArrayList<Tramo>();
		this.paso = paso;
	}

	/**
	 * Método para añadir un nuevo tramo que une este nodo con otro.
	 * 
	 * @param calle
	 *            Tramo que representa el pedazo de vía que une este nodo con
	 *            otro.
	 * @param entra
	 *            Booleano que indica si el Tramo tiene carriles que entran
	 *            hacia el nodo.
	 * @param sale
	 *            Booleano que indica si el Tramo tiene carriles que salen del
	 *            nodo.
	 */
	void agregar(Tramo calle, boolean entra, boolean sale) {
		if (entra)
			callesEntran.add(calle);
		if (sale)
			callesSalen.add(calle);
	}

	/**
	 * Devuelve una calle saliente aleatoriamente. Utilizado en el prototipo del
	 * simulación para simplificar la decisión entre las calles para continuar
	 * el recorrido.
	 * 
	 * @return Tramo que indica una posible calle a tomar estando en el nodo.
	 */
	public synchronized Tramo getRandomCalle() {
		if (callesSalen.size() > 0) {
			int n = generador.nextInt(callesSalen.size());
			return callesSalen.get(n);
		} else
			return null;
	}

	/**
	 * Devuelve un entero que representa la distanacia entre dos nodos, utlizada
	 * luego para poder representar la posición de los vehiculos
	 * proporcionalmente y la velocidad en terminos "absolutos".
	 * 
	 * @param nodo
	 *            Nodo hasta el que se desea medir la distancia.
	 * @return Entero que representa la distancia entre dos nodos.
	 */
	public int distancia(Nodo nodo) {
		double distX2 = (this.posX - nodo.posX) * (this.posX - nodo.posX);
		double distY2 = (this.posY - nodo.posY) * (this.posY - nodo.posY);
		return (int) java.lang.Math.sqrt(distX2 + distY2);
	}

	/**
	 * Devuevle la posición del nodo a lo largo del eje x.
	 * 
	 * @return Double que representa dicha posición.
	 */
	public synchronized double getPosX() {
		return posX;
	}

	/**
	 * Devuevle la posición del nodo a lo largo del eje y.
	 * 
	 * @return Double que representa dicha posición.
	 */
	public synchronized double getPosY() {
		return posY;
	}

	/**
	 * Método que crea una representación del nodo sobre un elemento Graphics.
	 * 
	 * @param g
	 *            Graphics donde se debe hacer la representación del nodo.
	 */
	public void dibujar(Graphics g) {
		g.drawOval((int) posX - 15, (int) posY - 15, 30, 30);
	}

	public void dibujar(Graphics2D g) {
		g.drawOval((int) posX - 15, (int) posY - 15, 30, 30);
	}
	
	public synchronized boolean puedePasar(Tramo calle) {
		if (calle.isVertical())
			return paso.getValor();
		else
			return !paso.getValor();

	}

}
