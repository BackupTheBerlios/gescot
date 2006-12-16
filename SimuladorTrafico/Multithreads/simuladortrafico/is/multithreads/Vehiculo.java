/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Multithreads.simuladortrafico.is.multithreads.mapa.Tramo;

/**
 * @author Grupo IS Tr�fico
 *
 *	Interfaz Vehiculo
 *	Esta interfaz esta creada para permitir el uso de diferentes tipos de vehiculos, que
 * 	pueden representarse de diferentes maneras o reaccionar de formas diferentes a los
 *  par�metros de la simulaci�n.
 */

public interface Vehiculo extends Runnable {

	/**
	 * Devuelve el Tramo (calle) en el que esta el vehiculo.
	 * @return
	 * Tramo en el que se encuentra el vehiculo.
	 */
	Tramo getCalle();
	
	/**
	 * Toma como par�metro un elemento de la clase Graphics, en el cual representa
	 * el vehiculo seg�n la informaci�n que �ste tiene de si mismo.
	 * @param g
	 * Graphics donde se dibujan los elementos que aparecer�n en la ventana.
	 */
	void dibujar(Graphics g);
	
	/**
	 * Toma como par�metro un elemento de la clase Graphics2D, en el cual representa
	 * el vehiculo seg�n la informaci�n que �ste tiene de si mismo.
	 * @param g
	 * Graphics2D donde se dibujan los elementos que aparecer�n en la ventana.
	 */
	void dibujar2d(Graphics2D g);
	
	/**
	 * Devuelve la posici�n m�nima, esto es, la posici�n de la parte trasera del vehiculo.
	 * @return
	 * double, que representa la posci�n m�nima entre 0 y 1
	 */
	double getPosMin();
	
	/**
	 * Devuelve la posici�n m�xima, esto es, la posici�n de la parte delantera del vehiculo.
	 * @return
	 * double, que representa la posci�n m�xima entre 0 y 1
	 */
	double getPosMax();
	
	/**
	 * Devuelve el carril que ocupa el vehiculo en el tramo en el que esta
	 * @return
	 * Entero entre 1 y n, siendo n el n�mero de carriles del tramo.
	 */
	int getCarril();
	
	/**
	 * Devuelve la velocidad, que terminos absolutos, del coche.
	 * @return
	 * Entero, entre 0 y x, siendo x la velocidad m�xima establecida.
	 */
	int getVelocidad();
	
	double getPos();
	
	/**
	 * Devulve un booleano que determina si el coche esta circulando desde el nodo inicial
	 * del Tramo o desde el nodo final.
	 * @return
	 * Verdadero si esta yendo del nodo inicial al nodo final.
	 * Falso si esta yendo en sentido contrario.
	 */
	boolean getSentidoCorrecto();
	
}
