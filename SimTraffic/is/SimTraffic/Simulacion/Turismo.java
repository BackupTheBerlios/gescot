package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Tramo;

import java.util.Random;



/**
 * @author Grupo ISTrafico
 *
 */
public class Turismo extends Vehiculo {

	Random random;
	
	/**
	 * 
	 */
	public Turismo () {
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un vehiculo
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
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o exponencial o algo asi
		this.aceleracion += (double) cuanto / 200;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}

	@Override
	public Tramo siguienteTramo() {
		// TODO por ahora hace un recorrido aleatorio
		return nodoDestino.getTramos().get(random.nextInt(nodoDestino.getTramos().size()));
	}


}
