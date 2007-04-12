package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Herramienta que permite mover los nodos seleccionados, y como consecuencia los tramos, a la 
 * ubicación indicada por el usuario mediante el GUI.
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
	 * Cuánto se ha movido con respecto a la posición inicial en el eje X.
	 */
	double diferenciaX;
	
	/**
	 * Cuánto se ha movido con respecto a la posición inicial en el eje X.
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
	
	public void estableceInicioYFin(Point2D puntoOrigen, Point2D puntoDestino){
		diferenciaX = puntoDestino.getX()-puntoOrigen.getX();
		diferenciaY = puntoDestino.getY()-puntoOrigen.getY();
	}
	
	/**
	 * 
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
	 * 
	 */
	public int deshacer(IModelo modelo) {
		for (int i=0; i< nodos.size(); i++ ) {
			Nodo nodoTemp = nodos.get(i);
			nodoTemp.setPos(nodosAnteriores.get(i).getPos());
		}

		return 0;
	}
	public String toString(){
		return "Evento-Solo es temporal";
	}
}
