package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

public class Ambulancia extends Vehiculo {
	private Random random = new Random();
	
	List<Nodo> hospitales;
	
	private ArrayList<Tramo> tramos = new ArrayList<Tramo>();

	private int cuentaTramos = 0;
	
	public Ambulancia(List<Nodo> hospitales) {
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un vehiculo
		nombre = "Ambulancia";
		
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.PINK;
		this.figura = new Rectangle2D.Double(-5, -RepresentacionSimple.tamaño_carril, 5,
				RepresentacionSimple.tamaño_carril);
		
		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(30) / 100 + 0.4;
		this.distanciaSeguridad = 4;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(30) / 100 + 1.3;
		this.id = ncochesglobal;
		this.hospitales = hospitales;
		ncochesglobal++;

	}
	
	public synchronized void setTramo(Tramo tramo) {
		cuentaTramos++;

		this.tramo = tramo;
	}
	
	public boolean inicializar(Nodo entrada, Nodo salida) {
		Random random = new Random();
		System.out.println(hospitales.size());
		entrada = hospitales.get(random.nextInt(hospitales.size()));
		
		super.inicializar(entrada, salida);
		super.setNodoOrigen(entrada);
		cuentaTramos = 0;
		tramos = BuscaCamino.obtenerInstancia().buscar(entrada, salida);
		if (tramos == null || tramos.size() < 1) return false;

		for (int i = tramos.size() -1; i >= 0; i--) {
			tramos.add(tramos.get(i));
			}
		return true;

	}
	
	@Override
	public synchronized Tramo siguienteTramo() {
		if (tramos != null&& cuentaTramos < tramos.size())
				return tramos.get(cuentaTramos);
		return null;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		if (cuanto > 0)
			this.aceleracion += (double) cuanto * cuanto / 40000;
		else
			this.aceleracion -= (double) cuanto * cuanto / 40000;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}

}
