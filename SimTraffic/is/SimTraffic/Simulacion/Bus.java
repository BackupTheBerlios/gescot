package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

public class Bus extends Vehiculo {

	private Random random = new Random();

	private ArrayList<LineaBus> lineas;

	private int cuentaTramos;

	private boolean sentido = false;

	private LineaBus linea;

	public Bus(ArrayList<LineaBus> lineas) {
		// TODO este constructor deberia dar valores a todos
		// los atributos de un vehiculo
		nombre = "Bus";
		this.lineas = lineas;
		cuentaTramos = 0;
		// Se genera un color aleatorio
		// generarColorAleatorio();
		this.color = Color.RED;
		this.figura = new Rectangle2D.Double(-6,
				-RepresentacionSimple.tamaño_carril, 6,
				RepresentacionSimple.tamaño_carril);

		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(30) / 100 + 0.2;
		this.distanciaSeguridad = 4;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(60) / 100 + 0.6;
		this.id = ncochesglobal;
		ncochesglobal++;

	}

	public boolean inicializar(Nodo entrada, Nodo salida) {
		super.inicializar(entrada, salida);
		linea = lineas.get(random.nextInt(lineas.size()));
		cuentaTramos = random.nextInt(linea.getTramos().size());
		this.tramo = linea.getTramos().get(
				random.nextInt(linea.getTramos().size()));
		Tramo sig;
		if (cuentaTramos < linea.getTramos().size() - 1)
			sig = linea.getTramos().get(cuentaTramos + 1);
		else
			sig = linea.getTramos().get(0);

		if (random.nextBoolean()) {
			this.nodoOrigen = tramo.getNodoInicial();
			this.nodoDestino = tramo.getNodoFinal();
		} else {
			this.nodoDestino = tramo.getNodoInicial();
			this.nodoOrigen = tramo.getNodoFinal();
		}
		if (nodoDestino.getTramos().contains(sig))
			sentido = true;
		else
			sentido = false;

		return true;
	}

	public synchronized void setTramo(Tramo tramo) {
		if (sentido) cuentaTramos++;
		else {
			cuentaTramos--;
			if (cuentaTramos < 0) cuentaTramos = linea.getTramos().size() -1;
		}
		cuentaTramos = cuentaTramos % linea.getTramos().size();
	}
	
	@Override
	public synchronized Tramo siguienteTramo() {
		int temp;
		if (linea != null) {
			if (sentido) {

				temp = (cuentaTramos + 1) % linea.getTramos().size();
				return linea.getTramos().get(temp);
			} else {
				temp = (cuentaTramos - 1);
				if (temp < 0)
					temp = linea.getTramos().size() - 1;
				return linea.getTramos().get(temp);
			}
		}

		return null;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o
		// exponencial o algo asi
		if (cuanto > 0)
			this.aceleracion += (double) cuanto * cuanto / 60000;
		else
			this.aceleracion -= (double) cuanto * cuanto / 40000;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}

}
