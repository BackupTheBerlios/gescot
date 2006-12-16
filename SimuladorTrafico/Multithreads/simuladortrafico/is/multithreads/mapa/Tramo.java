/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads.mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Multithreads.simuladortrafico.is.multithreads.Vehiculo;

/**
 * @author Grupo IS Tráfico
 * 
 * Clase que representa un tramo en el mapa. Esta clase mantiene información
 * sobre los vehiculos que estan circulando por el tramo, así como permite
 * realizar operaciónes sobre esta lista para poder tomar decisiones en la
 * simulación. También almacena información sobre el tramo en si, como es el
 * número de carriles, los nodos de comienzo y fin, y, más adelante, podría
 * tener información como la velocidad máxima, distintas reglas de circulación,
 * etc.
 */
public class Tramo {
	Nodo comienzo;

	Nodo fin;

	int largo;

	int carrilesSentidoCorreto;

	int carrilesSentidoContrario;

	List<Vehiculo> vehiculos;
	
	boolean vertical;

	double angulo;

	double despX;

	double despY;

	public Tramo(Nodo comienzo, Nodo fin, int largo,
			int carrilesSentidoCorrecto, int carrilesSentidoContrario,
			boolean vertical) {
		this.comienzo = comienzo;
		this.fin = fin;
		this.largo = largo;
		this.carrilesSentidoContrario = carrilesSentidoContrario;
		this.carrilesSentidoCorreto = carrilesSentidoCorrecto;
		boolean entra = (carrilesSentidoContrario > 0);
		boolean sale = (carrilesSentidoCorrecto > 0);
		comienzo.agregar(this, entra, sale);
		fin.agregar(this, sale, entra);
		vehiculos = new ArrayList<Vehiculo>();
		angulo = java.lang.Math.atan((comienzo.getPosY() - fin.getPosY())
				/ (comienzo.getPosX() - fin.getPosX()));
		despX = java.lang.Math.sin(angulo);
		despY = java.lang.Math.cos(angulo);
		this.vertical = vertical;
	}

	public synchronized Nodo getComienzo() {
		return comienzo;
	}

	public synchronized Nodo getFin() {
		return fin;
	}

	public synchronized int getLargo() {
		return largo;
	}

	public synchronized double posSiguiente(Vehiculo este) {
		double posV = este.getPos();
		boolean sentido = este.getSentidoCorrecto();
		int carril = este.getCarril();
		Vehiculo vehiculo;

		double pos = 2;
		Iterator<Vehiculo> iterador = vehiculos.iterator();
		while (iterador.hasNext()) {
			vehiculo = iterador.next();
			if (vehiculo != este) {
				if (vehiculo.getSentidoCorrecto() == sentido
						&& vehiculo.getCarril() == carril) {
					if (vehiculo.getPos() >= posV
							&& vehiculo.getPos() < pos) {
						pos = vehiculo.getPos();
					}
				}
			}
		}
		return pos;
	}

	public synchronized void addVehiculo(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
	}

	public synchronized void removeVehiculo(Vehiculo vehiculo) {
		vehiculos.remove(vehiculo);
	}

	public void dibujar(Graphics g) {
		g.setColor(Color.red);
		g.drawLine((int) comienzo.getPosX(), (int) comienzo.getPosY(),
				(int) fin.getPosX(), (int) fin.getPosY());
		g.setColor(Color.black);

		for (int n = 1; n <= this.carrilesSentidoCorreto; n++) {
			g.drawLine((int) (comienzo.getPosX() + 5.0 * n * despX),
					(int) (comienzo.getPosY() - 5.0 * n * despY), (int) (fin
							.getPosX() + 5.0 * n * despX),
					(int) (fin.getPosY() - 5.0 * n * despY));
		}
		for (int n = 1; n <= this.carrilesSentidoContrario; n++) {
			g.drawLine((int) (comienzo.getPosX() - 5.0 * n * despX),
					(int) (comienzo.getPosY() + 5.0 * n * despY), (int) (fin
							.getPosX() - 5.0 * n * despX),
					(int) (fin.getPosY() + 5.0 * n * despY));
		}

	}
	
	public void dibujar(Graphics2D g) {
		g.setColor(Color.red);
		g.drawLine((int) comienzo.getPosX(), (int) comienzo.getPosY(),
				(int) fin.getPosX(), (int) fin.getPosY());
		g.setColor(Color.black);

		for (int n = 1; n <= this.carrilesSentidoCorreto; n++) {
			g.drawLine((int) (comienzo.getPosX() + 5 * n * despX),
					(int) (comienzo.getPosY() + 5 * n * despY), (int) (fin
							.getPosX() + 5 * n * despX),
					(int) (fin.getPosY() + 5 * n * despY));
		}
		for (int n = 1; n <= this.carrilesSentidoContrario; n++) {
			g.drawLine((int) (comienzo.getPosX() - 5 * n * despX),
					(int) (comienzo.getPosY() - 5 * n * despY), (int) (fin
							.getPosX() - 5 * n * despX),
					(int) (fin.getPosY() - 5 * n * despY));
		}

	}

	public synchronized boolean puedeEntrar(int largo, boolean sentido,
			int carril) {
		Vehiculo vehiculo;
		Iterator<Vehiculo> iterador = vehiculos.iterator();
		while (iterador.hasNext()) {
			vehiculo = iterador.next();
			synchronized (vehiculo) {
				if (vehiculo.getSentidoCorrecto() == sentido) {
					if (vehiculo.getCarril() == carril) {
						if (vehiculo.getPosMin() > 0
								&& vehiculo.getPosMin() < (largo / this.largo)) {
							return false;
						}
					}
				}
			}
		}
		Random gen = new Random();
		return gen.nextBoolean();
	}

	public double getDespX() {
		return despX;
	}

	public double getDespY() {
		return despY;
	}

	public synchronized int getCarrilesSentidoContrario() {
		return carrilesSentidoContrario;
	}

	public synchronized int getCarrilesSentidoCorreto() {
		return carrilesSentidoCorreto;
	}
	
	public synchronized int cambioCarril(Vehiculo este) {
		if (este.getSentidoCorrecto() && this.carrilesSentidoCorreto == 1)
			return 0;
		if (!este.getSentidoCorrecto() && this.carrilesSentidoContrario == 1)
			return 0;
		
		int carrilPrueba = este.getCarril() + 1;
		if ((este.getSentidoCorrecto() && carrilPrueba <= carrilesSentidoCorreto) ||
				(!este.getSentidoCorrecto() && carrilPrueba <= carrilesSentidoContrario)){
			double posSiguiente = 2;
			double posAnterior = -1;
			Vehiculo vehiculo;

			Iterator<Vehiculo> iterador = vehiculos.iterator();
			while (iterador.hasNext()) {
				vehiculo = iterador.next();
					if (vehiculo.getSentidoCorrecto() == este.getSentidoCorrecto()
							&& vehiculo.getCarril() == carrilPrueba) {
						if (vehiculo.getPos() >= este.getPos()
								&& vehiculo.getPos() < posSiguiente) {
							posSiguiente = vehiculo.getPos();
						}
						if (vehiculo.getPos() <= este.getPos() && vehiculo.getPos() > posAnterior) {
							posAnterior = vehiculo.getPos();
						}
					}
			}
			if (posAnterior < este.getPosMin() && posSiguiente > este.getPosMax()) {
				return carrilPrueba;
			}
		}
		carrilPrueba = este.getCarril() - 1;
		if (carrilPrueba > 0){
			double posSiguiente = 2;
			double posAnterior = -1;
			Vehiculo vehiculo;

			Iterator<Vehiculo> iterador = vehiculos.iterator();
			while (iterador.hasNext()) {
				vehiculo = iterador.next();
					if (vehiculo.getSentidoCorrecto() == este.getSentidoCorrecto()
							&& vehiculo.getCarril() == carrilPrueba) {
						if (vehiculo.getPos() >= este.getPos()
								&& vehiculo.getPos() < posSiguiente) {
							posSiguiente = vehiculo.getPos();
						}
						if (vehiculo.getPos() <= este.getPos() && vehiculo.getPos() > posAnterior) {
							posAnterior = vehiculo.getPos();
						}
					}
			}
			if (posAnterior < este.getPosMin() && posSiguiente > este.getPosMax()) {
				return carrilPrueba;
			}
		}
		
		return 0;
	}

	public synchronized boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

}