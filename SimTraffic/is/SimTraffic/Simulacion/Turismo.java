package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Grupo ISTrafico
 * 
 */
public class Turismo extends Vehiculo {

	private Random random;

	private ArrayList<Tramo> tramos = new ArrayList<Tramo>();

	private int cuentaTramos = 0;

	/**
	 * 
	 */
	public Turismo() {
		// TODO este constructor deberia dar valores a todos
		// los atributos de un vehiculo
		nombre = "Turismo";

		// Se genera un color aleatorio
		// generarColorAleatorio();
		this.color = Color.BLUE;
		this.figura = new Rectangle2D.Double(0,
				-RepresentacionSimple.tamaño_carril/2, 4,
				RepresentacionSimple.tamaño_carril/2);

		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(30) / 100 + 0.2;
		this.distanciaSeguridad = 3;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(60) / 100 + 0.6;
		this.id = ncochesglobal;
		ncochesglobal++;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o
		// exponencial o algo asi
		if (cuanto > 0)
			this.aceleracion += (double) cuanto * cuanto / 40000;
		else
			this.aceleracion -= (double) cuanto * cuanto / 40000;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}

	@Override
	public synchronized Tramo siguienteTramo() {
		// TODO da el tramo siguiente
		if (tramos.size() <= cuentaTramos) {
			return null;
		}
		return tramos.get(cuentaTramos);
	}

	public synchronized void setTramo(Tramo tramo) {
		cuentaTramos++;
		this.tramo = tramo;
	}

	public boolean inicializar(Nodo entrada, Nodo salida) {
		super.inicializar(entrada, salida);

	    ArrayList<Tramo> tramosTemp = BuscaCamino.obtenerInstancia().buscar(entrada, salida);
	    if (tramosTemp == null) return false;
	    tramos = tramosTemp;
	    cuentaTramos = 0;
	    return true;

	}

}
