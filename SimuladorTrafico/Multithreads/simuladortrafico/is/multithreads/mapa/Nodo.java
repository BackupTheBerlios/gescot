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
 * @author Grupo IS Tr�fico
 * 
 * Clase que mantiene la informaci�n de un nodo, como son las calles que llegan
 * y eventualmente las se�ales de tr�fico. Posee distintos m�todos para acceder
 * a la informaci�n de distintas formas, para facilitar las distintas etapas de
 * la simulaci�n.
 */
public class Nodo {

	/**
	 * Lista de los Tramos que tiene direcci�n entrante al nodo
	 */
	private List<Tramo> callesEntran;

	/**
	 * Lista de los Tramos que tienen direcci�n saliente del nodo.
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
	 * Constructor del nodo, que inicializa la posici�n y las listas.
	 * 
	 * @param posX
	 *            Double que representa la posici�n del nodo a lo largo del eje
	 *            x.
	 * @param posY
	 *            Double que representa la posici�n del nodo a lo largo del eje
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
	 * M�todo para a�adir un nuevo tramo que une este nodo con otro.
	 * 
	 * @param calle
	 *            Tramo que representa el pedazo de v�a que une este nodo con
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
	 * simulaci�n para simplificar la decisi�n entre las calles para continuar
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
	 * luego para poder representar la posici�n de los vehiculos
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
	 * Devuevle la posici�n del nodo a lo largo del eje x.
	 * 
	 * @return Double que representa dicha posici�n.
	 */
	public synchronized double getPosX() {
		return posX;
	}

	/**
	 * Devuevle la posici�n del nodo a lo largo del eje y.
	 * 
	 * @return Double que representa dicha posici�n.
	 */
	public synchronized double getPosY() {
		return posY;
	}

	/**
	 * M�todo que crea una representaci�n del nodo sobre un elemento Graphics.
	 * 
	 * @param g
	 *            Graphics donde se debe hacer la representaci�n del nodo.
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
