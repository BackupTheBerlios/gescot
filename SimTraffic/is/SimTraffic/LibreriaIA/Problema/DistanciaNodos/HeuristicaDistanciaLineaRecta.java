package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class HeuristicaDistanciaLineaRecta implements IHeuristica {

	EstadoDistanciaNodos estadoObjetivo;
	
	public HeuristicaDistanciaLineaRecta(EstadoDistanciaNodos objetivo) {
		super();
		estadoObjetivo = objetivo;
	}

	public float darValorHeuristico(IEstado estado) {
		Nodo nodoActual = ((EstadoDistanciaNodos)estado).getNodoPosicion();
		Nodo nodoDestinoFinal = estadoObjetivo.getNodoPosicion();
		Tramo tramoauxiliar = new Tramo(nodoActual,nodoDestinoFinal);
		int distanciaMinima = tramoauxiliar.getLargo();
		((EstadoDistanciaNodos)estado).setValorHeuristico(distanciaMinima);
		return distanciaMinima;	
	}

	
}
