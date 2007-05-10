package is.SimTraffic.Simulacion;

import java.util.ArrayList;

import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class BuscaCamino {
	
	static int maxSimultaneos = 8;
	
	static BuscaCamino[] instancia = new BuscaCamino[maxSimultaneos];
	
	static int cont = 0;
	
	static synchronized BuscaCamino obtenerInstancia() {
		cont = (cont + 1) % maxSimultaneos;
		if (instancia[cont] == null) {
			instancia[cont] = new BuscaCamino();
		}
		return instancia[cont];
	}
	
	public BuscaCamino() {
		
	}
	
	public ArrayList<Tramo> buscar(Nodo entrada, Nodo salida) {
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos(entrada,
				salida);
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			// no ha sido posible encontrar un camino entre los nodos
			return null;
		} else {
			// Mostrar solución en el mapa
			for (int i = (algoritmoAEstrella.getSolucion().size()); i > 0; i--) {
				if (algoritmoAEstrella.getSolucion().elementAt(i - 1)
						.getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i - 1).getOperador()))
							.getTramoElegido();
					tramos.add(tramoAux);
				}
			}
			return tramos;
		}
	}
}
