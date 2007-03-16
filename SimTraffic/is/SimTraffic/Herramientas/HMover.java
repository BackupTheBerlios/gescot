package is.SimTraffic.Herramientas;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

public class HMover implements IHerramienta{

	/**
	 * Lista de nodos que se quieren desplazar.
	 */
	private List<Nodo> nodos;
	/**
	 * Lista de tramos que se quieren desplazar.
	 */
	private List<Tramo> tramos;
	
	/**
	 * Lista de nodos que se quieren desplazar.
	 */
	private List<Nodo> nodosAnteriores;
	/**
	 * Lista de tramos que se quieren desplazar.
	 */
	private List<Tramo> tramosAnteriores;
	
	
	private Point2D puntoOrigen;
	
	private Point2D puntoDestino;
	
	double diferenciaX;
	
	double diferenciaY;
	
	
	/**
	 * Constructora de la herramienta
	 * Se inicializan las listas de nodos y tramos
	 *
	 */
	public HMover(List<Nodo> nodos, List<Tramo> tramos, Point2D puntoOrigen, Point2D puntoDestino) {
		this.nodos = nodos;
		this.tramos = tramos;
		this.puntoOrigen = puntoOrigen;
		this.puntoDestino = puntoDestino;
		diferenciaX = puntoDestino.getX()-puntoOrigen.getX();
		diferenciaY = puntoDestino.getY()-puntoOrigen.getY();
		nodosAnteriores = new ArrayList<Nodo>();
		for (int i=0; i< nodos.size(); i++) {
			Nodo nodoClon = nodos.get(i).cloneParaMover();
			nodosAnteriores.add(nodoClon);
		}
		//No hay que hacer lo mismo para tramos		

	}
	
	public int hacer(IModelo modelo) {
		for (int i=0; i< nodos.size(); i++) {
			Nodo nodoTemp = nodos.get(i);
			nodoTemp.setPos(new Posicion(nodoTemp.getPos().getLat()+diferenciaY,
						nodoTemp.getPos().getLon()+diferenciaX));
		}
		return 0;
	}

	public int deshacer(IModelo modelo) {
		for (int i=0; i< nodos.size(); i++ ) {
			
		}
		return 0;
	}

}
