package is.SimTraffic.Herramientas;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Utils.Tiempo;

/**
 * Herramienta que se encarga de pegar una selección de elementos
 * que esté en el portapapeles
 * @author GrupoISTrafico
 *
 */

public class HPegar implements IHerramienta {

	
	/**
	 * Lista de nodos seleccionados
	 */
	private List<Nodo> nodos;
	/**
	 * Lista de tramos seleccionados
	 */
	private List<Tramo> tramos;
	
	/**
	 * Punto del mapa en el que se va a pegar
	 */	
	private Point2D puntoPegar;	
	
	/**
	 * Constructora de la herramienta.
	 * Se le pasa el punto en el que se va a pegar
	 * @param puntoPegar
	 */
	public HPegar (Point2D puntoPegar) {
		this.nodos = new ArrayList<Nodo>();
		this.tramos = new ArrayList<Tramo>();
		this.puntoPegar = puntoPegar;
	}
	/**
	 * Método deshacer que se encarga de eliminar del mapa 
	 * los tramos y nodos que se han pegado
	 */
	public int deshacer(IModelo modelo) {
		for (int i=0; i<tramos.size(); i++)
			modelo.getMapa().eliminar(tramos.get(i));
		for (int i=0; i<nodos.size(); i++)
			modelo.getMapa().eliminar(nodos.get(i));			
		return 0;
	}

	/**
	 * Método hacer.
	 * Si el portapapeles no está vacío, calcula la diferencia de la posición del
	 * nodo de referencia almacenado al punto en el que se pega.
	 * Se comienza introduciendo en el mapa los tramos que están en el portapapeles.
	 * Si los nodos origen o destino de ese tramo no están en el mapa, se introducen.
	 * Finalmente, se introducen los nodos del portapapeles que no estuvieran unidos
	 * a ningún tramo cortado
	 */
	public int hacer(IModelo modelo) {
		if (!modelo.getMapa().getPortapapeles().getNodosSeleccionados().isEmpty()) {
			//nodos.clear();
			//tramos.clear();
			modelo.getMapa().setSeleccion(new Seleccion());
			double difX = puntoPegar.getX() - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLon();
			double difY = puntoPegar.getY() - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLat();
			for (int i=0; i<modelo.getMapa().getPortapapeles().getTramosSeleccionados().size(); i++) {
				Tramo tramoTemp = modelo.getMapa().getPortapapeles().getTramosSeleccionados().get(i);			
				Nodo nodoInicial = tramoTemp.getNodoInicial();
				Nodo nodoFinal = tramoTemp.getNodoFinal();
				//Nodo nodoInicialTemp = new Nodo (nodoInicial.getPos().clone());
				Nodo nodoInicialTemp = nodoInicial.pseudoClone();//
				nodoInicialTemp.setPos(new Posicion(nodoInicialTemp.getPos().getLat()+difY,nodoInicialTemp.getPos().getLon()+difX));
				Nodo nodoInicialMapa = modelo.getMapa().existeNodo(nodoInicialTemp);
				if (nodoInicialMapa==null) {
					nodoInicialMapa = nodoInicialTemp;
					modelo.getMapa().insertar(nodoInicialMapa);
					modelo.getMapa().getSeleccion().añadirNodo(nodoInicialMapa);
					//Nodo nodoInicialHerramienta = nodoInicialMapa.pseudoClone();
					nodos.add(nodoInicialMapa);///
				}
				
				Nodo nodoFinalTemp = nodoFinal.pseudoClone();//
				//Nodo nodoFinalTemp = new Nodo (nodoFinal.getPos().clone());
				nodoFinalTemp.setPos(new Posicion(nodoFinalTemp.getPos().getLat()+difY,nodoFinalTemp.getPos().getLon()+difX));
				Nodo nodoFinalMapa = modelo.getMapa().existeNodo(nodoFinalTemp);
				if (nodoFinalMapa==null) {
					nodoFinalMapa = nodoFinalTemp;
					modelo.getMapa().insertar(nodoFinalMapa);
					modelo.getMapa().getSeleccion().añadirNodo(nodoFinalMapa);
					//Nodo nodoFinalHerramienta = nodoFinalMapa.pseudoClone();
					nodos.add(nodoFinalMapa);///
				}
				
				
				Tramo tramoMapa = tramoTemp.pseudoClone(nodoInicialMapa,nodoFinalMapa);
				nodoInicialMapa.añadirTramo(tramoMapa);
				nodoFinalMapa.añadirTramo(tramoMapa);
				modelo.getMapa().insertar(tramoMapa);
				modelo.getMapa().getSeleccion().añadirTramo(tramoMapa);
				tramos.add(tramoMapa);///
			}
			
			for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
				Nodo nodoMapa = nodoTemp.pseudoClone();
				nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
				if (modelo.getMapa().existeNodo(nodoMapa)==null) {
					//Nodo nodoMapa = nodoTemp.pseudoClone();
					modelo.getMapa().insertar(nodoMapa);
					modelo.getMapa().getSeleccion().añadirNodo(nodoMapa);
					nodos.add(nodoMapa);///
					//nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
				}
					
			}
		}
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+"Acción pegar";
	}
	/**
	 * @return Returns the nodos.
	 */
	public List<Nodo> getNodos() {
		return nodos;
	}
	/**
	 * @return Returns the puntoPegar.
	 */
	public Point2D getPuntoPegar() {
		return puntoPegar;
	}
	/**
	 * @return Returns the tramos.
	 */
	public List<Tramo> getTramos() {
		return tramos;
	}
}
