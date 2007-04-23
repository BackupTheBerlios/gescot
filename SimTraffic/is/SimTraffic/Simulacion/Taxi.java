package is.SimTraffic.Simulacion;

import java.util.Random;

import is.SimTraffic.Mapa.Tramo;

public class Taxi extends Vehiculo {

	Random random = new Random();
	
	@Override
	public Tramo siguienteTramo() {
		if (this.nodoDestino!=null) {
			int i = random.nextInt(this.nodoDestino.getTramos().size());
			return this.nodoDestino.getTramos().get(i);
		}
		return null;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		
	}

}
