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
		nombre = "Bus";
		this.lineas = lineas;
		cuentaTramos = 0;
		this.color = Color.RED;
		this.figura = new Rectangle2D.Double(0,
				-RepresentacionSimple.tamaño_carril/2.0, 8,
				RepresentacionSimple.tamaño_carril/2.0);

		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(20) / 100 + 0.1;
		this.distanciaSeguridad = 4;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(40) / 100 + 0.5;
		this.id = ncochesglobal;
		ncochesglobal++;

	}

	public boolean inicializar(Nodo entrada, Nodo salida) {
		super.inicializar(entrada, salida);
		linea = lineas.get(random.nextInt(lineas.size()));
		cuentaTramos = random.nextInt(linea.getTramos().size());
		tramo = linea.getTramos().get(cuentaTramos);

		if (random.nextBoolean()) {
			this.nodoOrigen = tramo.getNodoInicial();
			this.nodoDestino = tramo.getNodoFinal();
		} else {
			this.nodoDestino = tramo.getNodoInicial();
			this.nodoOrigen = tramo.getNodoFinal();
		}
		this.nodoEntrada = nodoOrigen;
		
		
		Tramo sig = null;
		int cuentaTemp = cuentaTramos - 1;
		if (cuentaTemp < 0)
			cuentaTemp = linea.getTramos().size() - 1;
		sig = linea.getTramos().get(cuentaTemp);

		if (nodoDestino.getTramos().contains(sig)) {
			sentido = true;
			cuentaTramos++;
			if (cuentaTramos >= linea.getTramos().size())
				cuentaTramos = 0;
		} else {
			sentido = false;
			cuentaTramos--;
			if (cuentaTramos < 0)
				cuentaTramos = linea.getTramos().size() - 1;
		}


		return true;
	}

	public synchronized void setTramo(Tramo tramo) {
		if (sentido)
			cuentaTramos--;
		else {
			cuentaTramos++;
		}
		if (cuentaTramos < 0)
			cuentaTramos = linea.getTramos().size() - 1;
		if (cuentaTramos >= linea.getTramos().size())
			cuentaTramos = 0;
		this.tramo = tramo;
	}

	public synchronized void setCarril(int carril) {
		if (this.tramo != null && this.nodoDestino == this.tramo.getNodoFinal())
			this.carril = this.tramo.getNumCarrilesDir1();
		else if (this.tramo != null
				&& this.nodoDestino == this.tramo.getNodoInicial())
			this.carril = this.tramo.getNumCarrilesDir2();
		else
			this.carril = carril;
	}

	@Override
	public synchronized Tramo siguienteTramo() {
		int temp;
		if (linea != null) {
			if (sentido) {
				temp = (cuentaTramos - 1);
				if (temp < 0)
					temp = linea.getTramos().size() - 1;
			} else {
				temp = (cuentaTramos + 1);
				if (temp >= linea.getTramos().size())
					temp = 0;
			}
			return linea.getTramos().get(temp);
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
