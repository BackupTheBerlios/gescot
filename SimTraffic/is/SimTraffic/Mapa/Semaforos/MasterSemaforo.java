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
	 * Variable del tipo lista que mantiene todos los tramos que llegan a este nodo
	 */
	private BibliotecaSemaforos SemControl;
	
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

			// se hace otro dia
			//		SemControl.GeneraSiguienteEstado(id_tipo_sem,nodo);
							
		}
		
		
		
public int getEstadoSemaforos(){
	return this.estadoSemaforos;
}

public void setEstadoSemaforos(int Estado){
	this.estadoSemaforos=Estado;
}


}
