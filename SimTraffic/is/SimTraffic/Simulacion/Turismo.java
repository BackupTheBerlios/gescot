package is.SimTraffic.Simulacion;

import java.util.Random;



/**
 * @author Grupo ISTrafico
 *
 */
public class Turismo extends Vehiculo {
	/**
	 * 
	 */
	public Turismo () {
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un vehiculo
		Random random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(30) / 100 + 0.2;
		this.distanciaSeguridad = 10;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(30) / 100 + 0.2;
		
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO Auto-generated method stub
		this.aceleracion += (double) cuanto / 200;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
	}


}
