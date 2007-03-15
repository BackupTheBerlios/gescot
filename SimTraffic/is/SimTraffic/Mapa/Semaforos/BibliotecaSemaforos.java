/**
 * 
 */
package is.SimTraffic.Mapa.Semaforos;

import is.SimTraffic.Mapa.Nodo;
/**
 * Clase que se encarga de proporcionar un estado siguiente al MasterSemaforo,
 * 	teniendo en cuenta el tipo de semáforo, el estado actual y el nodo donde está incluido
 * Existirán varias configuraciones de cambio de luces de los semáforos,
 *  motivadas por parámetros relativos a la circulación o bien determinadas por el usuario para simular
 * Adicionalmente se pueden incluir parametros tales como la intensidad de trafico, meteorologia, etc.
 * @author usuario_local
 * 2 usos:
 * MejorConfiguracion (Semaforo IdSem){ ...}
 * EstadoSiguiente (Semaforo IdSem){ ...}
 */
public class BibliotecaSemaforos {

	/**
	 * 	Metodo para seleccionar la mejor configuracion aplicable a un nodo 
	 * teniendo en cuenta el numero de carriles de los tramos adayacentes a un nodo
	 * 
	 * @param nodo
	 * @return Identificador de la configuracion del semaforo. El semaforo estandar tendra el Id=0. (Semaforo Circular)
	 */
	public int CalculaTipoDeSemaforoOptimo(Nodo nodo){
		//TODO implementar el metodo. 
		// calculo de las condiciones.. eleccion del mejor semaforo
		return 0;
		
	}
	
	
	public void GeneraSiguienteEstado(int tipoSemaforo, Nodo nodo){
		switch(tipoSemaforo){
		case 0: cambiarEstadoSemaforosTipo0(nodo);
			break;
		}

		
	}
	
	
	/**
	 * Metodo para actualizar todos los semaforos del cruze.
	 * Por ejemplo <pre>si estadoSemaforos==1 entonces <br>estadoSemaforos=2<br>y 
	 * ArraySemaforos se configura de tal manera que los vehiculos que estan en el tramo 2
	 * puedan ir al resto de tramos excluyendo el mismo.<pre>
	 * Esta es la configuracion estandar, Semaforo circular  
	 */
	
	// se hace otro dia
	
public void cambiarEstadoSemaforosTipo0(Nodo nodo){
		//nodo.getMasterSemaforo().getEstadoSemaforos()
		int tamaño= nodo.getTramos().size();
		
		int estadoSemaforos=nodo.getMasterSemaforo().getEstadoSemaforos();

		// contador cíclico para simular el comportamiento de los semáforos estandar
		estadoSemaforos = (estadoSemaforos+1) % tamaño;
		nodo.getMasterSemaforo().setEstadoSemaforos(estadoSemaforos );
		
		
		for (int i=0; i<tamaño;i++)
		{
			for (int j=0; j<tamaño;j++)
			{
				if (i==estadoSemaforos){	nodo.getMasterSemaforo().setMasterSemaforo(i,j,true); }//ArraySemaforos[i][j]=true;
				else	{		nodo.getMasterSemaforo().setMasterSemaforo(i,j,false);			}
			}
		}
		// Vehículos no pueden hacer cambio de sentido. 
		for (int k=0; k<tamaño;k++)
		{
			nodo.getMasterSemaforo().setMasterSemaforo(k,k,false);
		}
		
	
	}

	
	
	
}
