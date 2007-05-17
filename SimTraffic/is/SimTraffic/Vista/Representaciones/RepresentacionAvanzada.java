package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Bus;
import is.SimTraffic.Simulacion.Camion;
import is.SimTraffic.Simulacion.Taxi;
import is.SimTraffic.Simulacion.Vehiculo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class RepresentacionAvanzada extends Representacion {
	/**
	 * Parámetro que establece el ancho de cada uno de los carriles a dibujar
	 */
	private double tamaño_carril = 2.5;
	
	Hashtable<Integer, Tramo> tabla;
	
	/**
	 * Almacena las imagenes de los coches para dibujarlos de manera más
	 * eficiente.
	 */
	private BufferedImage coches[];
	
	/**
	 * Almacena las imagenes de los camiones para dibujarlos de manera más
	 * eficiente.
	 */
	private BufferedImage camiones[];

	private BufferedImage autobus;
	
	/**
	 * Constructor por defecto de la RepresentacionSimple
	 */
	public RepresentacionAvanzada() {
		super();
		tabla = new Hashtable<Integer, Tramo>();
		inicializarImagenes();
	}
	
	public void iniciarRepresentacion() {
		tabla.clear();
	}
	
	/**
	 * Constructur de la clase que toma los parámetros de otra clase
	 * representacion.
	 * 
	 * @param rep
	 */
	public RepresentacionAvanzada(Representacion rep) {
		super(rep);
		inicializarImagenes();
	}
	
	/** Inicializa las imagenes de los coches. */
	private void inicializarImagenes() {
		coches = new BufferedImage[12];
		/*coches[0] = cargarImagen("Coche1.png");
		coches[1] = cargarImagen("Coche2.png");
		coches[2] = cargarImagen("Coche3.png");
		coches[3] = cargarImagen("Coche4.png");
		coches[4] = cargarImagen("Coche5.png");
		coches[5] = cargarImagen("Coche6.png");
		coches[6] = cargarImagen("Coche7.png");*/

		coches[0] = cargarImagen(Messages.getString("RepresentacionAvanzada.0")); //$NON-NLS-1$
		coches[1] = cargarImagen(Messages.getString("RepresentacionAvanzada.1")); //$NON-NLS-1$
		coches[2] = cargarImagen(Messages.getString("RepresentacionAvanzada.2")); //$NON-NLS-1$
		coches[3] = cargarImagen(Messages.getString("RepresentacionAvanzada.3")); //$NON-NLS-1$
		coches[4] = cargarImagen(Messages.getString("RepresentacionAvanzada.4")); //$NON-NLS-1$
		coches[5] = cargarImagen(Messages.getString("RepresentacionAvanzada.5")); //$NON-NLS-1$
		coches[6] = cargarImagen(Messages.getString("RepresentacionAvanzada.6")); //$NON-NLS-1$
		coches[7] = cargarImagen(Messages.getString("RepresentacionAvanzada.7")); //$NON-NLS-1$
		coches[8] = cargarImagen(Messages.getString("RepresentacionAvanzada.8")); //$NON-NLS-1$
		coches[9] = cargarImagen(Messages.getString("RepresentacionAvanzada.9")); //$NON-NLS-1$
		coches[10] = cargarImagen(Messages.getString("RepresentacionAvanzada.10")); //$NON-NLS-1$
		coches[11] = cargarImagen(Messages.getString("RepresentacionAvanzada.11")); //$NON-NLS-1$
		
		camiones = new BufferedImage[5];
		camiones[0] = cargarImagen(Messages.getString("RepresentacionAvanzada.12")); //$NON-NLS-1$
		camiones[1] = cargarImagen(Messages.getString("RepresentacionAvanzada.13")); //$NON-NLS-1$
		camiones[2] = cargarImagen(Messages.getString("RepresentacionAvanzada.14")); //$NON-NLS-1$
		camiones[3] = cargarImagen(Messages.getString("RepresentacionAvanzada.15")); //$NON-NLS-1$
		camiones[4] = cargarImagen(Messages.getString("RepresentacionAvanzada.16")); //$NON-NLS-1$
		
		autobus = cargarImagen(Messages.getString("RepresentacionAvanzada.17")); //$NON-NLS-1$
		
	}
	
	public void pintar(Graphics2D g, Nodo nodo) {
		g.setColor(Color.RED);
		int x = x_MapaARep(nodo.getPos().getLon());
		int y = y_MapaARep(nodo.getPos().getLat());
		
		if ((x > -20 && x < 1500) || (y > -20 && y < 1500)) {
			for (int i = 0; i < nodo.getTramos().size(); i++) {
				tabla.put(nodo.getTramos().get(i).hashCode(), nodo.getTramos()
						.get(i));
			}
		}
		
		pintarAreaNodo(g, nodo);
		
		if (nodo.getTipo() != null && zoom >= 1) {
			String aux = nodo.getTipo().getTipoCastellano();
			BufferedImage buffer = null;
			
			if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.18"))) { //$NON-NLS-1$
				aux = nodo.getTipo().getValorTipoCastellano();
				
				if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.19"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.20")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.21"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.22")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.23"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.24")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.25"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.26")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.27"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.28")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.29"))) { //$NON-NLS-1$
					 buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.30")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.31"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.32")); //$NON-NLS-1$
				}
			} else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.33"))) { //$NON-NLS-1$
				aux = nodo.getTipo().getValorTipoCastellano();
				
				if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.34"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.35")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.36"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.37")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.38"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.39")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.40"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.41")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.42"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.43")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.44"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.45")); //$NON-NLS-1$
				} 
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.46"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.47")); //$NON-NLS-1$
				}
			} else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.48"))) { //$NON-NLS-1$
				aux = nodo.getTipo().getValorTipoCastellano();
				
				if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.49"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.50")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.51"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.52")); //$NON-NLS-1$
				} 
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.53"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.54")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.55"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.56")); //$NON-NLS-1$
				} 
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.57"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.58")); //$NON-NLS-1$
				}
			} else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.59"))) { //$NON-NLS-1$
				aux = nodo.getTipo().getValorTipoCastellano();
				
				if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.60"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.61")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.62"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.63")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.64"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.65")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.66"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.67")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.68"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.69")); //$NON-NLS-1$
				} 
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.70"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.71")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.72"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.73")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.74"))) { //$NON-NLS-1$
					 buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.75")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.76"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.77")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.78"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.79")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.80"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.81")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.82"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.83")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.84"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.85")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.86"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.87")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.88"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.89")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.90"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.91")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.92"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.93")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.94"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.95")); //$NON-NLS-1$
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.96"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.97")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.98"))) { //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.99")); //$NON-NLS-1$
				}
				else if (aux.equalsIgnoreCase(Messages.getString("RepresentacionAvanzada.100"))) //$NON-NLS-1$
					buffer = cargarImagen(Messages.getString("RepresentacionAvanzada.101")); //$NON-NLS-1$
			}
			g.drawImage(buffer, x_MapaARep(nodo.getPos().getLon()) - 10,
					y_MapaARep(nodo.getPos().getLat()) - 10, null);
		}
	}
	
	private void pintarAreaNodo(Graphics2D g, Nodo nodo) {
		int numtramos = nodo.getTramos().size();
		int npuntos_X[] = new int[numtramos * 2];
		int npuntos_Y[] = new int[numtramos * 2];
		Iterator<Tramo> iter = nodo.getTramos().iterator();
		int ipunto = 0;
		while (iter.hasNext()) {
			Tramo temp = iter.next();
			if (nodo.equals(temp.getNodoInicial())) {
				npuntos_X[ipunto] = (int) (x_MapaARep(temp.getNodoInicial()
						.getPos().getLon()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir1()
						* Math.sin(temp.getAngulo()));
				npuntos_X[ipunto + 1] = (int) (x_MapaARep(temp.getNodoInicial()
						.getPos().getLon()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir2()
						* -(Math.sin(temp.getAngulo())));
				npuntos_Y[ipunto] = (int) (y_MapaARep(temp.getNodoInicial()
						.getPos().getLat()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir1()
						* (Math.cos(temp.getAngulo())));
				npuntos_Y[ipunto + 1] = (int) (y_MapaARep(temp.getNodoInicial()
						.getPos().getLat()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir2()
						* -Math.cos(temp.getAngulo()));
			} else {
				npuntos_X[ipunto + 1] = (int) (x_MapaARep(temp.getNodoFinal()
						.getPos().getLon()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir1()
						* Math.sin(temp.getAngulo()));
				npuntos_X[ipunto] = (int) (x_MapaARep(temp.getNodoFinal()
						.getPos().getLon()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir2()
						* -(Math.sin(temp.getAngulo())));
				npuntos_Y[ipunto + 1] = (int) (y_MapaARep(temp.getNodoFinal()
						.getPos().getLat()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir1()
						* (Math.cos(temp.getAngulo())));
				npuntos_Y[ipunto] = (int) (y_MapaARep(temp.getNodoFinal()
						.getPos().getLat()) + tamaño_carril * zoom
						* temp.getNumCarrilesDir2()
						* -Math.cos(temp.getAngulo()));
			}
			ipunto = ipunto + 2;
		}
		Polygon poligon = new Polygon(npuntos_X, npuntos_Y, numtramos * 2);
		g.setColor(Color.LIGHT_GRAY);// DARK_GRAY);
		g.fill(poligon);
		g.setColor(Color.RED);
		g.drawRect(x_MapaARep(nodo.getPos().getLon()) - 2, y_MapaARep(nodo
				.getPos().getLat()) - 2, 4, 4);
	}
	
	
	public void pintar(Graphics2D g, Tramo tramo, String tipo) {
		// almacena posiciones de los nodos
		if (tabla.get(tramo.hashCode()) == null) {
			return;
		}
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		if (tipo == null) {
			dibujarCarretera(g, tramo);
		} else if (tipo.compareTo(Messages.getString("RepresentacionAvanzada.102")) == 0) { //$NON-NLS-1$
			g.setColor(Color.green);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo(Messages.getString("RepresentacionAvanzada.103")) == 0) { //$NON-NLS-1$
			g.setColor(Color.gray);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo(Messages.getString("RepresentacionAvanzada.104")) == 0) { //$NON-NLS-1$
			dibujarCarretera(g, tramo);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else if (tipo.compareTo(Messages.getString("RepresentacionAvanzada.105")) == 0) { //$NON-NLS-1$
			g.setColor(Color.pink);
			g.drawLine(x_MapaARep(posnodo1.getLon()), y_MapaARep(posnodo1
					.getLat()), x_MapaARep(posnodo2.getLon()),
					y_MapaARep(posnodo2.getLat()));
		} else {
			dibujarCarretera(g, tramo);
		}
		
	}
	
	private void dibujarCarretera(Graphics2D g, Tramo tramo) {
		
		// almacena posiciones de los nodos
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// almacena numero de carriles en cada sentido
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		
		double angulo = tramo.getAngulo();
		
		// genera los puntos (x,y) de las esquinas del poligono
		int x[] = {
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril * zoom
						* carriles_ida * Math.sin(angulo)),
						(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril * zoom
								* carriles_ida * Math.sin(angulo)),
								(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril * zoom
										* carriles_vuelta * -(Math.sin(angulo))),
										(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril * zoom
												* carriles_vuelta * -(Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril * zoom
						* carriles_ida * (Math.cos(angulo))),
						(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril * zoom
								* carriles_ida * (Math.cos(angulo))),
								(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril * zoom
										* carriles_vuelta * -Math.cos(angulo)),
										(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril * zoom
												* carriles_vuelta * -Math.cos(angulo)) };
		// establece el color y dibuja el poligono
		
		// System.out.println("x->" + x[0] + " " + x[1]+ " " + x[2]+ " " +
		// x[3]);
		// System.out.println("y->" + y[0] + " " + y[1]+ " " + y[2]+ " " +
		// y[3]);
		
		g.setColor(Color.LIGHT_GRAY);// DARK_GRAY);
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
			g.drawLine((int) (posx1 + tamaño_carril * zoom * (i + 1)
					* Math.sin(angulo)), (int) (posy1 + tamaño_carril * zoom
							* (i + 1) * (Math.cos(angulo))),
							(int) (posx2 + tamaño_carril * zoom * (i + 1)
									* Math.sin(angulo)), (int) (posy2 + tamaño_carril
											* zoom * (i + 1) * (Math.cos(angulo))));
		}
		// luego una linea punteada por cada carril en el otro y una normal para
		// el ultimo
		for (int i = 0; i < carriles_vuelta; i++) {
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine((int) (posx1 + tamaño_carril * zoom * -(i + 1)
					* Math.sin(angulo)), (int) (posy1 + tamaño_carril * zoom
							* -(i + 1) * (Math.cos(angulo))),
							(int) (posx2 + tamaño_carril * zoom * -(i + 1)
									* Math.sin(angulo)), (int) (posy2 + tamaño_carril
											* zoom * -(i + 1) * (Math.cos(angulo))));
		}
		g.drawLine((int) (posx1 + tamaño_carril * zoom * -(carriles_vuelta)
				* Math.sin(angulo)), (int) (posy1 + tamaño_carril * zoom
						* -(carriles_vuelta) * (Math.cos(angulo))),
						(int) (posx1 + tamaño_carril * zoom * (carriles_ida)
								* Math.sin(angulo)), (int) (posy1 + tamaño_carril
										* zoom * (carriles_ida) * (Math.cos(angulo))));
		g.drawLine((int) (posx2 + tamaño_carril * zoom * -(carriles_vuelta)
				* Math.sin(angulo)), (int) (posy2 + tamaño_carril * zoom
						* -(carriles_vuelta) * (Math.cos(angulo))),
						(int) (posx2 + tamaño_carril * zoom * (carriles_ida)
								* Math.sin(angulo)), (int) (posy2 + tamaño_carril
										* zoom * (carriles_ida) * (Math.cos(angulo))));
		// seleccionarColoresSemaforos(tramo, g);
	}
	
	public void seleccionarColoresSemaforos(Tramo tramo, Graphics2D g) {
		Color color;
		Nodo ninicial = tramo.getNodoInicial();
		int c1 = ninicial.comprobarAlgunVerde(tramo);
		if (c1 == 0)
			color = null;
		else if (c1 == 1)
			color = Color.GREEN;
		else
			color = Color.RED;
		Color color2 = Color.RED;
		Nodo nfinal = tramo.getNodoFinal();
		int c2 = nfinal.comprobarAlgunVerde(tramo);
		if (c2 == 0)
			color2 = null;
		else if (c2 == 1)
			color2 = Color.GREEN;
		else
			color2 = Color.RED;
		pintarSemaforosTramo(g, color, color2, tramo);
	}
	
	private void pintarSemaforosTramo(Graphics2D g, Color color, Color color2,
			Tramo tramo) {
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// almacena numero de carriles en cada sentido
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		
		double angulo = tramo.getAngulo();
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
		
		g.setStroke(new BasicStroke(5));
		if (color != null) {
			g.setColor(color);
			g.drawLine((int) (posx1 + tamaño_carril * zoom * -(carriles_vuelta)
					* Math.sin(angulo)), (int) (posy1 + tamaño_carril * zoom
							* -(carriles_vuelta) * (Math.cos(angulo))), (int) (posx1),
							(int) (posy1));
		}
		if (color2 != null) {
			g.setColor(color2);
			g.drawLine((int) (posx2), (int) (posy2),
					(int) (posx2 + tamaño_carril * zoom * (carriles_ida)
							* Math.sin(angulo)), (int) (posy2 + tamaño_carril
									* zoom * (carriles_ida) * (Math.cos(angulo))));
		}
		g.setStroke(new BasicStroke(1));
	}
	
	@Override
	public void pintar(Graphics2D g, Señal señal) {
		// TODO Auto-generated method stub
		
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
				(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril * zoom
						* carriles_ida * Math.sin(angulo)),
						(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril * zoom
								* carriles_ida * Math.sin(angulo)),
								(int) (x_MapaARep(posnodo2.getLon()) + tamaño_carril * zoom
										* carriles_vuelta * -(Math.sin(angulo))),
										(int) (x_MapaARep(posnodo1.getLon()) + tamaño_carril * zoom
												* carriles_vuelta * -(Math.sin(angulo))) };
		int y[] = {
				(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril * zoom
						* carriles_ida * (Math.cos(angulo))),
						(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril * zoom
								* carriles_ida * (Math.cos(angulo))),
								(int) (y_MapaARep(posnodo2.getLat()) + tamaño_carril * zoom
										* carriles_vuelta * -Math.cos(angulo)),
										(int) (y_MapaARep(posnodo1.getLat()) + tamaño_carril * zoom
												* carriles_vuelta * -Math.cos(angulo)) };
		Polygon p = new Polygon(x, y, 4);
		return p;
	}
	
	public void pintarVehiculo(Graphics2D g, Vehiculo vehiculo, Tramo tramo2) {
		Tramo tramo = vehiculo.getTramo();
		if (tramo == null || !tramo2.equals(tramo))
			return;
		
		// Shape rect = new Rectangle2D.Double(-5, -2, 5,2);
		BufferedImage rect = coches[vehiculo.getId() % 11 + 1];
		
		//Cambiar cuando se cambie la forma de pintar los coches
		if (vehiculo instanceof Taxi){
			rect = coches[0];
		}
		if (vehiculo instanceof Camion){
			rect = camiones[vehiculo.getId() % 5];
		}
		if (vehiculo instanceof Bus){
			rect = autobus;
		}
		
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		// TODO si el tramo no se dibuja, puede ser bueno que ya no sigua
		g.setColor(Color.PINK);
		int posX1, posY1, posX2, posY2, posX, posY;
		
		posX1 = x_MapaARep(posnodo1.getLon());
		posX2 = x_MapaARep(posnodo2.getLon());
		posY1 = y_MapaARep(posnodo1.getLat());
		posY2 = y_MapaARep(posnodo2.getLat());
		
		// rotacion de la imagen
		AffineTransform rot = new AffineTransform();
		
		// translacion en el mapa
		AffineTransform trans = new AffineTransform();
		
		// zoom y desplazamiento por carril
		AffineTransform zoom = new AffineTransform();
		if (vehiculo.getTramo().getNodoFinal() == vehiculo.getNodoDestino()) {
			rot.rotate(Math.PI - vehiculo.getTramo().getAngulo() + 0.02);
			posX = posX1 + (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY1 + (int) ((posY2 - posY1) * vehiculo.getPosicion());
		} else {
			rot.rotate(-vehiculo.getTramo().getAngulo() + 0.02);
			posX = posX2 - (int) ((posX2 - posX1) * vehiculo.getPosicion());
			posY = posY2 - (int) ((posY2 - posY1) * vehiculo.getPosicion());
		}
		zoom.scale(this.zoom, this.zoom);
		zoom.translate(0, -tamaño_carril * (vehiculo.getCarril() - 1));
		rot.concatenate(zoom);
		trans.translate(posX, posY);
		trans.concatenate(rot);
		AffineTransform temp = g.getTransform();
		g.transform(trans);
		// g.draw(rect);
		if (vehiculo instanceof Camion || vehiculo instanceof Bus)
			g.drawImage(rect, -5, -2, 6, 2, null);
		else if (vehiculo instanceof Taxi)
			g.drawImage(rect, -5, -2, 4, 2, null);
		else
			g.drawImage(rect, -5, -2, 4, 2, null);
		g.setTransform(temp);
		
		// g.drawRect(posX - 1, posY - 1, 2, 2);
		
	}
	
	
	@Override
	public Polygon generarTrianguloFlechaSugerencia(Nodo nodo, Tramo destino) {
		Posicion pos;
		double angulo;
		if (destino.getNodoFinal().equals(nodo)) {
			pos = destino.getNodoInicial().getPos();
			angulo = Math.PI - destino.getAngulo();
		} else {
			pos = destino.getNodoFinal().getPos();
			angulo = Math.PI * 2 - destino.getAngulo();
		}
		
		int carriles = Math.max(destino.getNumCarrilesDir1(), destino
				.getNumCarrilesDir2());
		
		int desplazamiento = 15;
		int temp3 = (int) (desplazamiento * Math.cos(Math.PI + angulo) * zoom);
		int temp4 = (int) (-desplazamiento * Math.sin(Math.PI + angulo) * zoom);
		
		angulo = Math.PI / 2 + angulo;
		int temp1 = (int) (2 * tamaño_carril * zoom * carriles * Math
				.cos(angulo));
		
		int x[] = { x_MapaARep(pos.getLon()) - temp1 + temp3,
				x_MapaARep(pos.getLon()) + temp1 + temp3,
				x_MapaARep(pos.getLon()) - temp3 };
		
		temp1 = (int) (2 * tamaño_carril * zoom * carriles * Math.sin(angulo));
		int y[] = { y_MapaARep(pos.getLat()) - temp1 - temp4,
				y_MapaARep(pos.getLat()) + temp1 - temp4,
				y_MapaARep(pos.getLat()) + temp4 };
		
		System.out.println(x[0] + Messages.getString("RepresentacionAvanzada.106") + x[1] + Messages.getString("RepresentacionAvanzada.107") + x[2]); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println(y[0] + Messages.getString("RepresentacionAvanzada.108") + y[1] + Messages.getString("RepresentacionAvanzada.109") + y[2]); //$NON-NLS-1$ //$NON-NLS-2$
		
		Polygon p = new Polygon(x, y, 3);
		return p;
	}
	
}