package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

public class Taxi extends Vehiculo {

	private Random random = new Random();

	private Tramo siguiente;

	public Taxi() {
		// TODO este constructor deberia dar valores a todos
		// los atributos de un vehiculo
		nombre = "Taxi";

		// Se genera un color aleatorio
		// generarColorAleatorio();
		this.color = Color.YELLOW;
		this.figura = new Rectangle2D.Double(-4,
				-RepresentacionSimple.tamaño_carril, 4,
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
		this.setNodoOrigen(entrada);
		if (entrada.getTramos().size() == 0)
			return false;
		this.setNodoDestino(entrada);
		return true;
	}

	public void setTramo(Tramo tramo) {
		super.setTramo(tramo);
		if (tramo != null && this.nodoOrigen != null) {
			if (tramo.getNodoInicial() == this.nodoDestino) {
				for (int j = 0; j < 5; j++) {
					int i = random.nextInt(tramo.getNodoFinal().getTramos().size());
					siguiente = tramo.getNodoFinal().getTramos().get(i);
					if (siguiente != tramo)
						break;
				}
			} else {
				for (int j = 0; j < 5; j++) {
					int i = random.nextInt(tramo.getNodoInicial().getTramos().size());
					siguiente = tramo.getNodoInicial().getTramos().get(i);
					if (siguiente != tramo)
						break;
				}
			}
		}
	}

	@Override
	public synchronized Tramo siguienteTramo() {
		if (siguiente == null) {
			if (this.nodoDestino != null && nodoDestino.getTramos().size() > 0) {
				int i = random.nextInt(this.nodoDestino.getTramos().size());
				return this.nodoDestino.getTramos().get(i);
			}
		}
		return siguiente;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o
		// exponencial o algo asi
		this.aceleracion += (double) cuanto / 200;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}

}
