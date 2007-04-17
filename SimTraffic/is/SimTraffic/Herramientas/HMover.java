package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Utils.Tiempo;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Herramienta que permite mover los nodos seleccionados, y como consecuencia los tramos, a la 
 * ubicaci�n indicada por el usuario mediante el GUI.
 * @author Grupo ISTrafico
 */
public class HMover implements IHerramienta{

	/**
	 * Lista de nodos que se quieren desplazar.
	 */
	private List<Nodo> nodos;
	
	
	/**
	 * Lista de nodos que se quieren desplazar.
	 */
	private List<Nodo> nodosAnteriores;

	
	/**
	 * Cu�nto se ha movido con respecto a la posici�n inicial en el eje X.
	 */
	double diferenciaX;
	
	/**
	 * Cu�nto se ha movido con respecto a la posici�n inicial en el eje X.
	 */
	double diferenciaY;
	
	/**
	 * Constructora de la herramienta.
	 * Se inicializan las listas de nodos y tramos.
	 */
	public HMover(List<Nodo> nodos) {
		this.nodos = nodos;
		
		nodosAnteriores = new ArrayList<Nodo>();
		for (int i=0; i< nodos.size(); i++) {
			Nodo nodoClon = nodos.get(i).cloneParaMover();
			nodosAnteriores.add(nodoClon);
		}
		//No es necesario hacer lo mismo para tramos.		

	}
	
	/**
	 * M�todo que se encarga de calcular la diferencia entre los puntos de comienzo
	 * del movimiento y el punto de destino.
	 * @param puntoOrigen Punto desde el que se empieza a mover
	 * @param puntoDestino Punto en el que termina el movimiento
	 */
	public void estableceInicioYFin(Point2D puntoOrigen, Point2D puntoDestino){
		diferenciaX = puntoDestino.getX()-puntoOrigen.getX();
		diferenciaY = puntoDestino.getY()-puntoOrigen.getY();
	}
	
	/**
	 * M�todo hacer.
	 * Actualiza las posiciones de los nodos teniendo en cuenta el movimiento
	 * que han sufrido
	 */
	public int hacer(IModelo modelo) {
		for (int i=0; i< nodos.size(); i++) {
			Nodo nodoTemp = nodos.get(i);
			nodoTemp.setPos(nodosAnteriores.get(i).getPos());
			nodoTemp.setPos(new Posicion(nodoTemp.getPos().getLat()+diferenciaY,
						nodoTemp.getPos().getLon()+diferenciaX));
		}
		return 0;
	}
	
	/**
	 * M�todo deshacer.
	 * Restaura las posiciones anteriores al movimiento de todos los nodos
	 * afectados.
	 */
	public int deshacer(IModelo modelo) {
		for (int i=0; i< nodos.size(); i++ ) {
			Nodo nodoTemp = nodos.get(i);
			nodoTemp.setPos(nodosAnteriores.get(i).getPos());
		}

		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+ "Acci�n Mover";
	}
}
