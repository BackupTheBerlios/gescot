/**
 * 
 */
package is.SimTraffic.Mapa.Semaforos;

import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;

import java.util.List;

import is.SimTraffic.Mapa.Nodo;

/**
 * @author grupo IS "Tráfico"
 * clase que se encarga de controlar los semáforos de un nodo
 */
public class MasterSemaforo extends Señal{

	/**
	 * Variable del tipo lista que mantiene todos los tramos que llegan a este nodo
	 */
	private List<Tramo> tramos;
	
	/**
	 * Atributo que almacena el nodo actual
	 */
	private Nodo nodo;
	
 	 /**
	 * Atributo  para contener la clase BiliotecaSemaforos
	 * Su Uso es discutible
	 * TODO Uso discutible, ¿Hace falta usarlo? 
	 */
 
	private BibliotecaSemaforos SemControl = new BibliotecaSemaforos();
	
	/**
	 * Controlador de los semaforos del nodo
	 * Contendra una matriz cuadrada que indica si se puede entrar en algunos de los tramos adyacentes
	 * 	<br>Ejemplo: <br>
	 * 	<pre>Si ArraySemaforos[A][B]== true <br>entonces un vehiculo en el tramo <b>A</b> llegado al nodo actual
	 * puede entrar en el tramo <b>B</b></pre>
	 */
	private boolean[][] ArraySemaforos;
	
	/**
	 * Variable que contendra el valor para indicar el estado de los semafotos del cruze.
	 * El valor empezará en 1 y llegara hasta el numMax de tramos adyacentes al nodo
	 * 	<br>Ejemplo: <br>
	 * 	<pre>Si estadoSemaforos == 1 <br> entonces todos los vehiculos pueden entrar en el resto de tramos adyacentes al nodo.</pre>
	 */
	private int estadoSemaforos;
	
	
	/**
	 * Atributo que contiene el tipo de semaforo.<br>
	 * Tipos de semaforos:
	 * <ul>
	 * 	<li> Tipo 0 = Semaforo "Circular" </li>
	 * 	<li> Tipo 1 = Por determinar. Posible semaforo aplicable a este tipo: Semaforo "perpendicular"</li>
	 * 	<li>...</li>
	 * </ul>
	 * 	<HR> TODO Seguir añadiendo y concretando semaforos, podra servir para el manual avanzado
	 */
	private int tipoSemaforo;
	
	
	/**
	 * Accedente para establecer el tipo de semaforo.
	 * @param tipoSemaforo
	 */
	public void setTipoSemaforo(int tipoSemaforo) {
		this.tipoSemaforo = tipoSemaforo;
	}


	/**
	 * Contructor de la Clase MasterSemaforo
	 * @param nodo : Nodo cuyos semaforos se controlaran
	 */
	public MasterSemaforo(Nodo n){
		super("Semaforo");
		this.tramos=n.getTramos();
		this.nodo=n;
		nodo.setControladorSemaforo(this);
		this.inicializarSemaforos();
		
	}
	
	
	/**
	 * Metodo auxiliar para inicializar los semaforos del cruze. 
	 */
		public void inicializarSemaforos(){
			estadoSemaforos = tramos.size();
			ArraySemaforos=new boolean [tramos.size()][tramos.size()];
			this.tipoSemaforo=SemControl.CalculaTipoDeSemaforoOptimo(nodo);
				
			SemControl.GeneraSiguienteEstado(tipoSemaforo,nodo);
							
		}
		
		
		
public int getEstadoSemaforos(){
	return this.estadoSemaforos;
}

public void setEstadoSemaforos(int Estado){
	this.estadoSemaforos=Estado;
}

public void setMasterSemaforo(int x, int y, boolean value){
	this.ArraySemaforos[x][y]=value;
	
}


}
