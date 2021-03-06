package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import is.SimTraffic.Messages;
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
		nombre = Messages.getString("Ambulancia.0"); //$NON-NLS-1$
		
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.PINK;
		this.figura = new Rectangle2D.Double(0, -RepresentacionSimple.tama�o_carril/2.0, 6,
				RepresentacionSimple.tama�o_carril/2.0);
		
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
		//System.out.println(hospitales.size());
		entrada = hospitales.get(random.nextInt(hospitales.size()));
		
		super.inicializar(entrada, salida);
		super.setNodoOrigen(entrada);
		cuentaTramos = 0;
		tramos = BuscaCamino.obtenerInstancia().buscar(entrada, salida);
		if (tramos == null || tramos.size() < 1) return false;

		//Incorrecto si no puede ir por el camino de vuelta (por ejemplo en v�as unidireccionales, como no).
		/*for (int i = tramos.size() -1; i >= 0; i--) {
			tramos.add(tramos.get(i));
		}*/
		ArrayList<Tramo> tramosVuelta = new ArrayList<Tramo>();
		tramosVuelta = BuscaCamino.obtenerInstancia().buscar(salida, entrada);
		if (tramosVuelta==null || tramos.size()<1) return false;
		
		for (int i = 0; i <= tramosVuelta.size() -1; i++) {
		tramos.add(tramosVuelta.get(i));
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
