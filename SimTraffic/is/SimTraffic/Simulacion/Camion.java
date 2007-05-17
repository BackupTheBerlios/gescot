package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;



/**
 * @author Grupo ISTrafico
 *
 */
public class Camion extends Vehiculo {
	
	private Random random;
	
	private ArrayList<Tramo> tramos = new ArrayList<Tramo>();
	
	private int cuentaTramos = 0;
	
	public Camion() {
		nombre="Camion";
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.GREEN;
		this.figura = new Rectangle2D.Double(-6, -RepresentacionSimple.tamaño_carril, 6,
				RepresentacionSimple.tamaño_carril);
		
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un camión
		
		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(10) / 100 + 0.15;
		this.distanciaSeguridad = 5;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(20) / 100 + 0.5;
		this.id = ncochesglobal;
		ncochesglobal++;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o exponencial o algo asi
		if (cuanto > 0)
		this.aceleracion += (double) cuanto*cuanto / 50000;
		else
			this.aceleracion -= (double) cuanto*cuanto / 50000;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
		
	}

	public synchronized void setTramo(Tramo tramo){
		super.setTramo(tramo);
		cuentaTramos ++;
	}
	
	@Override
	public synchronized Tramo siguienteTramo() {
		// TODO da el tramo siguiente
		if (tramos.size() <= cuentaTramos) {
			return null;
		}
		return tramos.get(cuentaTramos);
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
