package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.Mapa.Posicion;

public class HeuristicaDistanciaLineaRecta implements IHeuristica {

	EstadoDistanciaNodos estadoObjetivo;
	
	public HeuristicaDistanciaLineaRecta(EstadoDistanciaNodos objetivo) {
		super();
		estadoObjetivo = objetivo;
	}

	public float darValorHeuristico(IEstado estado) {
		Posicion nodoActual = ((EstadoDistanciaNodos)estado).getNodoPosicion().getPos();
		Posicion nodoDestino = estadoObjetivo.getNodoPosicion().getPos();
		
		int distanciaMinima = (int) ((Math.abs(nodoActual.getLat() - nodoDestino.getLat()) +
							  Math.abs(nodoActual.getLon() - nodoDestino.getLon())) * 100000 );
		return distanciaMinima;	
	}

	
}
