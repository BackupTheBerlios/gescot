/**
 * 
 */
package is.SimTraffic.Mapa.Semaforos;

import is.SimTraffic.Mapa.Tramo;

import java.util.List;

import is.SimTraffic.Mapa.Nodo;

/**
 * @author grupo IS "Tráfico"
 * clase que se encarga de controlar los semáforos de un nodo
 */
public class MasterSemaforo {

	/**
	 * Variable del tipo lista que mantiene todos los tramos que llegan a este nodo
	 */
	private List<Tramo> tramos;
	
	/**
	 * Controlador de los semaforos del nodo
	 * Contendra una matriz cuadrada que indica si se puede entrar en algunos de los tramos adyacentes
	 * <br>Ejemplo: <br>
	 * <pre>Si ArraySemaforos[A][B]== true <br>entonces un vehiculo en el tramo <b>A</b> llegado al nodo actual puede entrar en el tramo <b>B</b></pre>
	 */
	private boolean[][] ArraySemaforos;
	
	/**
	 * Variable que contendra el valor para indicar el estado de los semafotos del cruze.
	 * El valor empezará en 1 y llegara hasta el numMax de tramos adyacentes al nodo
	 * <br>Ejemplo: <br>
	 * <pre>Si estadoSemaforos == 1 <br> entonces todos los vehiculos pueden entrar en el resto de tramos adyacentes al nodo.</pre>
	 */
	private int estadoSemaforos;

	/**
	 * Contructor de la Clase MasterSemaforo
	 * @param nodo : Nodo cuyos semaforos se controlaran
	 */
	public MasterSemaforo(Nodo nodo){
		tramos=nodo.getTramos();
	}
	
	
	/**
	 * Metodo auxiliar para inicializar los semaforos del cruze. 
	 */
		public void inicializarSemaforos(){
			estadoSemaforos = tramos.size();
			ArraySemaforos=new boolean [tramos.size()][tramos.size()];
			cambiarEstadoSemaforos();
							
		}
		
		
		/**
		 * Metodo para actualizar todos los semaforos del cruze.
		 * Por ejemplo <pre>si estadoSemaforos==1 entonces <br>estadoSemaforos=2<br>y ArraySemaforos se configura de tal manera que los vehiculos que estan en el tramo 2 puedan ir al resto de tramos excluyendo el mismo.<pre>
		 */
		public void cambiarEstadoSemaforos(){
			estadoSemaforos = (estadoSemaforos+1) % tramos.size();
			for (int i=0; i<tramos.size();i++)
			{
				for (int j=0; j<tramos.size();j++)
				{
					if (i==estadoSemaforos){
						ArraySemaforos[i][j]=true;
					}
					else
					{
						ArraySemaforos[i][j]=false;
					}
				}
			}
			// Vehículos no pueden hacer cambio de sentido. 
			for (int k=0; k<tramos.size();k++)
			{
				ArraySemaforos[k][k]=false;
			}
			
			
		}
}
