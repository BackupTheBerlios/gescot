/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import Multithreads.simuladortrafico.is.multithreads.mapa.*;

/**
 * @author Grupo IS Tráfico
 * 
 * Clase que implementa un coche, del tipo Vehiculo, que se representa por medio de un cuadrado
 * y que actua de acuerdo a patrones de comportamientos adecuados.
 */
public class Coche implements Vehiculo {

	
	/**
	 * Tramo donde se encuentra el coche.
	 */
	private Tramo calle;

	/**
	 * Posición del coche a lo largo del tramo.
	 */
	private double pos;

	/**
	 * Carril del tramo donde se encuentra el coche.
	 */
	private int carril;

	/**
	 * Velocidad (absoluta) a la que circula el coche.
	 */
	private int velocidad;

	/**
	 * Aceleración (absoluta) del coche.
	 */
	private int aceleracion;

	/**
	 * Aceleración máxima que puede alcanzar este coche.
	 */
	private int acel_max;

	/**
	 * Velocidad máxima que puede alcanzar el coche.
	 */
	private int vel_max;

	/**
	 * Sentido en que esta ciculando el tramo. Si es <b>true</b> va
	 * desde el comienzo al fin, y es <b>false</b> en caso contrario.
	 */
	private boolean sentidoCorrecto;

	/**
	 * Número de iteraciónes (frames) que permanece parado.
	 */
	private int frameParo;

	/**
	 * Número de la iteración que se está realizando.
	 */
	private int frame;

	/**
	 * Controlador del sistema.
	 */
	private Controlador control;

	/**
	 * Variables que mantien la última posición donde se
	 * dibujo el coche.
	 */
	private int viejaPosX, viejaPosY;

	/**
	 * Generador de números aleatorios.
	 */
	Random gen = new Random();

	/**
	 * Constructora de la clase coche.
	 * En esta constructora se debe indicar el nodo donde debe comenzar el coche
	 * y el controlador, que se encargara de sincronizarlo.
	 * 
	 * @param comienzo
	 * Nodo donde comienza el recorrido del coche.
	 * @param control
	 * Controlador que sincronizará los coches.
	 */
	Coche(Nodo comienzo, Controlador control) {
		calle = comienzo.getRandomCalle();
		if (calle != null) {
			calle.addVehiculo((Vehiculo) this);
			if (calle.getComienzo() == comienzo) {
				sentidoCorrecto = true;
				carril = gen.nextInt(calle.getCarrilesSentidoCorreto()) + 1;
			} else {
				sentidoCorrecto = false;
				carril = gen.nextInt(calle.getCarrilesSentidoContrario()) + 1;
			}
			pos = gen.nextDouble();
		}
		aceleracion = 0;
		acel_max = gen.nextInt(23) + 4;
		vel_max = gen.nextInt(100) + 30;
		frame = 0;
		this.control = control;
		this.frameParo = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#Dibujar(int, int, float)
	 */
	public void dibujar(Graphics g) {
		// TODO Auto-generated method stub
		if (calle != null) {
			//if (viejaPosX != 0) {
			//	g.setColor(Color.LIGHT_GRAY);
			//	g.drawRect(viejaPosX, viejaPosY, 4, 4);
			//}

			g.setColor(Color.BLACK);
			if (sentidoCorrecto) {
				viejaPosX = (int) (calle.getComienzo().getPosX()
						+ (calle.getFin().getPosX() - calle.getComienzo()
								.getPosX()) * pos + 5 * (carril)
						* calle.getDespX());
				viejaPosY = (int) (calle.getComienzo().getPosY()
						+ (calle.getFin().getPosY() - calle.getComienzo()
								.getPosY()) * pos - 5 * (carril)
						* calle.getDespY());
				g.drawRect(viejaPosX, viejaPosY, 3, 3);
			} else {
				viejaPosX = (int) (calle.getComienzo().getPosX()
						+ (calle.getFin().getPosX() - calle.getComienzo()
								.getPosX()) * (1 - pos) - 5 * (carril - 1)
						* calle.getDespX());
				viejaPosY = (int) (calle.getComienzo().getPosY()
						+ (calle.getFin().getPosY() - calle.getComienzo()
								.getPosY()) * (1 - pos) + 5 * (carril - 1)
						* calle.getDespY());
				g.drawRect(viejaPosX, viejaPosY, 3, 3);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#Dibujar(int, int, float)
	 */
	public void dibujar2d(Graphics2D g) {
		// TODO Auto-generated method stub
		if (calle != null) {
			if (sentidoCorrecto) {
				viejaPosX = (int) (calle.getComienzo().getPosX()
						+ (calle.getFin().getPosX() - calle.getComienzo()
								.getPosX()) * pos + 5 * carril
						* calle.getDespX());
				viejaPosY = (int) (calle.getComienzo().getPosY()
						+ (calle.getFin().getPosY() - calle.getComienzo()
								.getPosY()) * pos - 5 * carril
						* calle.getDespY());
				g.drawRect(viejaPosX, viejaPosY, 3, 3);
			} else {
				viejaPosX = (int) (calle.getComienzo().getPosX()
						+ (calle.getFin().getPosX() - calle.getComienzo()
								.getPosX()) * (1 - pos) - 5 * carril
						* calle.getDespX()) - 5;
				viejaPosY = (int) (calle.getComienzo().getPosY()
						+ (calle.getFin().getPosY() - calle.getComienzo()
								.getPosY()) * (1 - pos) + 5 * carril
						* calle.getDespY()) - 5;
				g.drawRect(viejaPosX, viejaPosY, 3, 3);
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getCalle()
	 */
	public Tramo getCalle() {
		// TODO Auto-generated method stub
		return calle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			if (calle != null) {

				controlarCochesDelante();

				// actualizar velocidad y posición, si las señales lo permiten
				if (controlarSenales()) {
					velocidad += aceleracion;
					if (velocidad < 0) {
						velocidad = 0;
						aceleracion = 0;
					}
					else if(velocidad > vel_max) {
						velocidad = vel_max;
						aceleracion = 0;
					}

					pos = pos + (double) velocidad / (calle.getLargo() * 80);
				}

				controlarFinTramo();

			}
			control.incCuentaChoches();
			// controlFrame = control.getFrame();

			while (control.getBlock(frame)) {
				try {
					Thread.sleep(0, 100);
				} catch (InterruptedException e) {
				}
			}
			;
			frame++;
		}
	}

	/**
	 * Función que verifica que tan cerca esta el coche siguiente, y actua de
	 * acuerdo a esta información.
	 */
	private void controlarCochesDelante() {
		if (pos + 15.0 / calle.getLargo() < calle.posSiguiente(this)
				|| pos < 0.08) {
			if (aceleracion < acel_max)
				aceleracion++;
		} else {
			aceleracion = -10;
			frameParo++;
			//velocidad = 0;
			//aceleracion = 0;
			int cambio = calle.cambioCarril(this);
			if (cambio > 0)
				carril = cambio;

			if (frameParo > 200000) {
				frameParo = 0;
				pos = gen.nextDouble();
			}
		}
	}

	/**
	 * Función verifica que se haya alcanzado el fin del Tramo y actua de
	 * acuerdo a esto. En particular, quita el coche del tramo actual y le pide
	 * al nodo final que le de una via a seguir (esto hace que el comportamiento
	 * sea aleatorio, habra que mejorarlo).
	 */
	private void controlarFinTramo() {

		if (pos > 0.98) {
			calle.removeVehiculo((Vehiculo) this);
			Nodo nodo;
			if (sentidoCorrecto) {
				nodo = calle.getFin();
				calle = calle.getFin().getRandomCalle();
			} else {
				nodo = calle.getComienzo();
				calle = calle.getComienzo().getRandomCalle();
			}
			pos = 0;
			if (calle != null) {
				calle.addVehiculo((Vehiculo) this);
				// velocidad = velocidad / 2;
				if (calle.getComienzo() == nodo) {
					sentidoCorrecto = true;
					carril = gen.nextInt(calle.getCarrilesSentidoCorreto()) + 1;
				} else {
					sentidoCorrecto = false;
					carril = gen.nextInt(calle.getCarrilesSentidoContrario()) + 1;
				}
				// if (!calle.puedeEntrar(10, sentidoCorrecto, carril))
				// velocidad = 0;
			}
		}
	}

	/**
	 * Función que verifica el estado de las señales en el siguiente nodo, y
	 * actua sobre la velocidad y la aceleración de forma acorde.
	 * 
	 * @return
	 * boolean que indica si se tomo alguna accion o no.
	 */
	private boolean controlarSenales() {
		if (pos > 1 - 15.0 / calle.getLargo() && pos <= 0.98) {
			if (sentidoCorrecto) {
				if (!calle.getFin().puedePasar(calle)) {
					velocidad = 0;
					aceleracion = 0;
					return false;
				}
			} else {
				if (!calle.getComienzo().puedePasar(calle)) {
					velocidad = 0;
					aceleracion = 0;
					return false;
				}
			}
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getPosMin()
	 */
	public synchronized double getPosMin() {
		return pos - 4.0 / calle.getLargo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getPosMax()
	 */
	public synchronized double getPosMax() {
		return pos + 4.0 / calle.getLargo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getCarril()
	 */
	public synchronized int getCarril() {
		return carril;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getVelocidad()
	 */
	public synchronized int getVelocidad() {
		return velocidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simuladortrafico.is.multithreads.Vehiculo#getSentidoCorrecto()
	 */
	public synchronized boolean getSentidoCorrecto() {
		return sentidoCorrecto;
	}

	/* (non-Javadoc)
	 * @see simuladortrafico.is.multithreads.Vehiculo#getPos()
	 */
	public synchronized double getPos() {
		return pos;
	}
}