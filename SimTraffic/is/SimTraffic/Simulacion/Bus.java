package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

public class Bus extends Vehiculo {

	private Random random = new Random();
	
	public Bus() {
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un vehiculo
		nombre = "Bus";
		
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.RED;
		this.figura = new Rectangle2D.Double(-6, -RepresentacionSimple.tamaño_carril, 6,
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
	@Override
	public Tramo siguienteTramo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO Auto-generated method stub

	}

}
