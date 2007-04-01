package is.SimTraffic.Herramientas;

import java.awt.geom.Point2D;
import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

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
	
	public HPegar (List<Nodo> nodos, List<Tramo> tramos, Point2D puntoPegar) {
		this.nodos = nodos;
		this.tramos = tramos;
		this.puntoPegar = puntoPegar;
	}
	
	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		for (int i=0; i<tramos.size(); i++)
			modelo.getMapa().eliminar(tramos.get(i));
		for (int i=0; i<nodos.size(); i++)
			modelo.getMapa().eliminar(nodos.get(i));			
		return 0;
	}

	public int hacer(IModelo modelo) {
		if (!modelo.getMapa().getPortapapeles().getNodosSeleccionados().isEmpty()) {
			nodos.clear();
			tramos.clear();
			double difX = puntoPegar.getX() - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLon();
			double difY = puntoPegar.getY() - modelo.getMapa().getNodoReferenciaPortapapeles().getPos().getLat();
			for (int i=0; i<modelo.getMapa().getPortapapeles().getTramosSeleccionados().size(); i++) {
				Tramo tramoTemp = modelo.getMapa().getPortapapeles().getTramosSeleccionados().get(i);			
				Nodo nodoInicial = tramoTemp.getNodoInicial();
				Nodo nodoFinal = tramoTemp.getNodoFinal();
				Nodo nodoInicialTemp = new Nodo (nodoInicial.getPos().clone());
				nodoInicialTemp.setPos(new Posicion(nodoInicialTemp.getPos().getLat()+difY,nodoInicialTemp.getPos().getLon()+difX));
				Nodo nodoInicialMapa = modelo.getMapa().existeNodo(nodoInicialTemp);
				if (nodoInicialMapa==null) {
					nodoInicialMapa = nodoInicialTemp;
					modelo.getMapa().insertar(nodoInicialMapa);
					nodos.add(nodoInicialMapa);///
				}
				

				Nodo nodoFinalTemp = new Nodo (nodoFinal.getPos().clone());
				nodoFinalTemp.setPos(new Posicion(nodoFinalTemp.getPos().getLat()+difY,nodoFinalTemp.getPos().getLon()+difX));
				Nodo nodoFinalMapa = modelo.getMapa().existeNodo(nodoFinalTemp);
				if (nodoFinalMapa==null) {
					nodoFinalMapa = nodoFinalTemp;
					modelo.getMapa().insertar(nodoFinalMapa);
					nodos.add(nodoFinalMapa);///
				}
				
				
				Tramo tramoMapa = tramoTemp.pseudoClone(nodoInicialMapa,nodoFinalMapa);
				nodoInicialMapa.añadirTramo(tramoMapa);
				nodoFinalMapa.añadirTramo(tramoMapa);
				modelo.getMapa().insertar(tramoMapa);
				tramos.add(tramoMapa);///
			}
			
			for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
				Nodo nodoMapa = nodoTemp.pseudoClone();
				nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
				if (modelo.getMapa().existeNodo(nodoMapa)==null) {
					//Nodo nodoMapa = nodoTemp.pseudoClone();
					modelo.getMapa().insertar(nodoMapa);
					nodos.add(nodoMapa);///
					//nodoMapa.setPos(new Posicion(nodoMapa.getPos().getLat()+difY,nodoMapa.getPos().getLon()+difX));
				}
					
			}
		}
		return 0;
	}

}
